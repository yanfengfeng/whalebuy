package com.android.whalebuy.listener;


import android.view.View;
import android.view.ViewGroup;
import com.android.whalebuy.common.ObjectAdapterList;

public interface IAdapterObjectList
{
	View onItemChanged(int position, View convertView, ViewGroup parent, ObjectAdapterList adapter);
	int onAdapterItemViewType (int position, ObjectAdapterList adapter);
	//int onAdapterViewTypeCount (ObjectAdapterList adapter);
	//long onAdapterItemId(int position, ObjectAdapterList adapter);
}
