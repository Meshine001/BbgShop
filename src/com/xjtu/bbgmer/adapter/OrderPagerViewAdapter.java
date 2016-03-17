package com.xjtu.bbgmer.adapter;

import java.util.List;
import java.util.Map;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class OrderPagerViewAdapter extends PagerAdapter{

	private List<View> viewList;
	private String[] titleList =  {"新订单","已取消","已发货","已完成"};
	
	public OrderPagerViewAdapter(List<View> viewList) {
		super();
		this.viewList = viewList;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titleList[position];
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//super.destroyItem(container, position, object);
		container.removeView(viewList.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		//return super.instantiateItem(container, position);
		container.addView(viewList.get(position));
		
		return viewList.get(position);
	}

}
