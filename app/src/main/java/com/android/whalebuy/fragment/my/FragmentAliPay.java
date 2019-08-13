package com.android.whalebuy.fragment.my;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.IConstants;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;

public class FragmentAliPay extends BaseFragment implements OnDismissListener
{
	public static interface IAliPayReturn
	{
		void onPayReturn();
	}
	
	private class LocalWebClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			mRootView.findViewById(R.id.ID_TXT_INFO).setVisibility(View.GONE);
			view.loadUrl(url);
			return true;
		}
		
		@Override
		public void onPageFinished(WebView view, String url)
		{
			mProgressDag.dismiss();
		}
		
		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
		{
			handler.proceed();
		}
		
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
		{
			Log.i(IConstants.TAG, "FragmentAliPay.onReceivedError.errorCode:" + errorCode
					+ ", description:" + description + ", failingUrl:" + failingUrl);
			try
			{
				mProgressDag.dismiss();
				if(failingUrl.startsWith("alipays://"))
				{
					// 调用支付宝
					Intent intent = new Intent();
					intent.setAction("android.intent.action.VIEW");
					Uri content_url = Uri.parse(failingUrl);
					intent.setData(content_url);
					startActivityForResult(intent, 1001);
					
					TextView txtView = (TextView)(mRootView.findViewById(R.id.ID_TXT_INFO));
					txtView.setVisibility(View.VISIBLE);
					txtView.setText("调用支付宝App");
					
					mRootView.findViewById(R.id.ID_VIEW_WEB).setVisibility(View.GONE);
				}
				else
				{
					Toast.makeText(getActivity(), description, Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Toast.makeText(getActivity(), "支付宝调用失败，请确认您的设备是否安装了支付宝app", Toast.LENGTH_SHORT).show();
				mRootView.findViewById(R.id.ID_VIEW_WEB).setVisibility(View.GONE);
				getFragmentManager().popBackStack();
			}
		}
	}
	
	private class LocalChromeClient extends WebChromeClient
	{
		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			/**
			View rootView = getView();
			TextView txtTitle = (TextView)rootView.findViewById(R.id.ID_TXT_TITLE);
			txtTitle.setText(title);
			**/
		}
	}
	
	private class AndroidJavaScript
	{
		/**
		 * 打开登录界面
		 */
		@JavascriptInterface
		public void paySuccess()
		{
			Log.i(IConstants.TAG, "paySuccess()");
			
			mRootView.postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					getFragmentManager().popBackStack();
				}
			}, 200);
		}
	}
	
	private ProgressDialog mProgressDag;
	private String mUrl;
	private IAliPayReturn mPayReturn;
	private View mRootView;
	
	public static FragmentAliPay create(String url, IAliPayReturn payReturn)
	{
		FragmentAliPay fragment = new FragmentAliPay();
		fragment.mUrl = url;
		fragment.mPayReturn = payReturn;
		
		return fragment;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(IConstants.TAG, "阿里支付.requestCode:" + requestCode + ", resultCode:" + resultCode);
		
		/**
		mPayReturn.onPayReturn();
		getFragmentManager().popBackStack();
		**/
	}
	
	@SuppressLint("SetJavaScriptEnabled") @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
	    // TODO Auto-generated method stub
		mRootView = inflater.inflate(R.layout.fragment_webview, container, false);
		
		TextView txtTitle = (TextView)mRootView.findViewById(R.id.ID_TXT_TITLE);
		txtTitle.setText("支付宝支付");
		
		mProgressDag = new ProgressDialog(getActivity());
		mProgressDag.setMessage(getString(R.string.loading));
		mProgressDag.setOnDismissListener(this);
		mProgressDag.setCancelable(true);
		mProgressDag.show();
		
		//打开链接
		WebView webView = (WebView)(mRootView.findViewById(R.id.ID_VIEW_WEB));
		WebSettings settings = webView.getSettings();
		settings.setBuiltInZoomControls(true);
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		webView.setWebViewClient(new LocalWebClient());
		webView.setWebChromeClient(new LocalChromeClient());
		webView.addJavascriptInterface(new AndroidJavaScript(), "native");
		
		webView.loadUrl(mUrl);
		
	    return mRootView;
    }

	@Override
    public void onDestroyView()
    {
	    // TODO Auto-generated method stub
	    super.onDestroyView();
	    View rootView = getView();
	    WebView webView = (WebView)(rootView.findViewById(R.id.ID_VIEW_WEB));
	    webView.stopLoading();
	    
	    mPayReturn.onPayReturn();
    }

	@Override
    public void onDismiss(DialogInterface arg0)
    {
	    // TODO Auto-generated method stub
    }
}
