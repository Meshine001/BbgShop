package com.xjtu.bbgmer.adapter;

import java.util.List;

import net.duohuo.dhroid.adapter.BeanAdapter.ViewHolder;

import com.xjtu.bbgmer.R;
import com.xjtu.bbgmer.bean.OrderBean;
import com.xjtu.bbgmer.utils.MapUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderListViewAdapter extends BaseAdapter{
	private Context mContext;
	private List<OrderBean> mData;
	private LayoutInflater mInflater;
	
	
	
	public OrderListViewAdapter(Context context, List<OrderBean> data) {
		super();
		this.mContext = context;
		this.mData = data;
		if(context != null){
			this.mInflater = LayoutInflater.from(context);
		}
	}
	
	public void setData(List<OrderBean> data){
		mData = data;
	}
	

	@Override
	public int getCount() {
		if (mData == null) {
			return 0;
		}
		return mData.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		OrderBean bean = mData.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.item_order_list, null);
			viewHolder.contactNameTextView = (TextView) convertView.findViewById(R.id.id_order_item_tv_client_name);
			viewHolder.contactNumTextView = (TextView) convertView.findViewById(R.id.id_order_item_tv_client_phone);
			viewHolder.addrTextView = (TextView) convertView.findViewById(R.id.id_order_item_tv_order_addr);
			viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.id_order_item_tv_date);
			viewHolder.statusTextView = (TextView) convertView.findViewById(R.id.id_order_item_tv_status);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.contactNameTextView.setText(bean.getContactName());
		viewHolder.contactNumTextView.setText(bean.getPhoneNum());
		viewHolder.addrTextView.setText(bean.getAddress());		
		viewHolder.timeTextView.setText(bean.getTime());
		//viewHolder.timeRangeTextView.setText(bean.getTimeRange());
		viewHolder.statusTextView.setText(MapUtil.getOrderStatus(bean.getStatus()));
		return convertView;
	}
	
	public class ViewHolder {
		private TextView contactNameTextView, contactNumTextView, addrTextView, timeTextView, 
				statusTextView;

	}

}
