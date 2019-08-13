package com.android.whalebuy.common;
import java.util.ArrayList;
import java.util.List;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.android.whalebuy.listener.IAdapterObjectList;

public class ObjectAdapterList extends BaseAdapter
{
	private List<Object> mDatas = null;
	private IAdapterObjectList mAdapterListener = null;
	public final AdapterView<?> adapterView;

	public ObjectAdapterList(IAdapterObjectList hAdapterListener, AdapterView<?> adapterView)
	{
		// TODO Auto-generated constructor stub
		mDatas = new ArrayList<Object>();
		mAdapterListener = hAdapterListener;
		this.adapterView = adapterView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return mAdapterListener.onItemChanged(position, convertView, parent, this);
	}
	
	@Override
	public int getItemViewType(int position)
	{
		return mAdapterListener.onAdapterItemViewType(position, this);
	}

	@Override
	public int getViewTypeCount()
	{
		return 100;
	}	

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	/**
	 * 添加一个节点
	 * 
	 * @param json
	 */
	public void addItem(Object item)
	{
		mDatas.add(item);
		// notifyDataSetChanged();
	}

	public void setDataList(List<Object> dataList)
	{
		mDatas = dataList;
	}

	public void addItem(int nIndex, Object item)
	{
		mDatas.add(nIndex, item);
	}
	
	public void addList(List<Object> list)
	{
		mDatas.addAll(list);
	}

	/**
	 * 清空整个列表
	 */
	public void removeAll()
	{
		mDatas.clear();
	}

	/**
	 * 删除某个节点
	 * 
	 * @param nIndex
	 */
	public void removeItem(int nIndex)
	{
		mDatas.remove(nIndex);
	}

	public void removeItem(Object item)
	{
		mDatas.remove(item);
	}

	public final List<Object> getDataList()
	{
		return mDatas;
	}

	@Override
    public long getItemId(int arg0)
    {
	    // TODO Auto-generated method stub
	    return 0;
    }
}
