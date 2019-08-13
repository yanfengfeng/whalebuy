package com.android.whalebuy.fragment.publics;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import com.android.whalebuy.R;
import com.android.whalebuy.common.BaseFragment;

public class FragmentWebView extends BaseFragment implements OnDismissListener
{
	private class LocalWebClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			return true;
		}
		
		@Override
		public void onPageFinished(WebView view, String url)
		{
			mProgressDag.dismiss();
		}
		
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
		{
			mProgressDag.dismiss();
			Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
		}
	}
	
	private class LocalChromeClient extends WebChromeClient
	{
		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			View rootView = getView();
			TextView txtTitle = (TextView)rootView.findViewById(R.id.ID_TXT_TITLE);
			txtTitle.setText(title);
		}
	}
	
	private ProgressDialog mProgressDag;
	private String mUrl;
	
	public static FragmentWebView create(String url)
	{
		FragmentWebView fragment = new FragmentWebView();
		fragment.mUrl = url;
		
		return fragment;
	}
	
	@SuppressLint("SetJavaScriptEnabled") @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
	    // TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
		
		//TextView txtTitle = (TextView)rootView.findViewById(R.id.ID_TXT_TITLE);
		
		mProgressDag = new ProgressDialog(getActivity());
		mProgressDag.setMessage(getString(R.string.loading));
		mProgressDag.setOnDismissListener(this);
		mProgressDag.setCancelable(false);
		mProgressDag.show();
		
		//打开链接
		WebView webView = (WebView)(rootView.findViewById(R.id.ID_VIEW_WEB));
		WebSettings settings = webView.getSettings();
		settings.setBuiltInZoomControls(true);
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true);
		webView.setWebViewClient(new LocalWebClient());
		webView.setWebChromeClient(new LocalChromeClient());
		
		webView.loadUrl(mUrl);
		
	    return rootView;
    }

	@Override
    public void onDestroyView()
    {
	    // TODO Auto-generated method stub
	    super.onDestroyView();
	    View rootView = getView();
	    WebView webView = (WebView)(rootView.findViewById(R.id.ID_VIEW_WEB));
	    webView.stopLoading();
    }

	@Override
    public void onDismiss(DialogInterface arg0)
    {
	    // TODO Auto-generated method stub
    }
}
