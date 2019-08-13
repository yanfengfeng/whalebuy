package com.android.whalebuy.adapter;

import java.util.List;

import com.android.whalebuy.R;
import com.android.whalebuy.been.PopWindow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WindowListAdapter extends BaseAdapter {
	private List<PopWindow> pops;
	private LayoutInflater inflater;
	private Context context;
	
	public WindowListAdapter(List<PopWindow> pops, Context context) {
		super();
		if (pops != null){
			this.pops = pops;
			this.context = context;
			this.inflater = LayoutInflater.from(context);
		}
	}
	
	public void setList(List<PopWindow> pops) {
		this.pops = pops;
	}

	@Override
	public int getCount() {
		if (pops == null){
			return 0;
		}
		return pops.size();
	}

	@Override
	public Object getItem(int position) {
		return pops.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder vh;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_pop, null);
			vh = new ViewHolder();
			vh.value = (TextView) convertView.findViewById(R.id.base_value);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		final PopWindow pop = pops.get(position);
		vh.value.setText(pop.getPhone());
		return convertView;
	}
	
	static class ViewHolder{
		TextView value;
	}

}
