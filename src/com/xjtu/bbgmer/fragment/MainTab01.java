package com.xjtu.bbgmer.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.duohuo.dhroid.Const;
import net.duohuo.dhroid.ioc.InjectUtil;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.view.PullToRefreshBase.OnRefreshListener;
import net.duohuo.dhroid.view.PullToRefreshListView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xjtu.bbgmer.R;
import com.xjtu.bbgmer.activity.OrderDetailsActivity;
import com.xjtu.bbgmer.adapter.OrderListViewAdapter;
import com.xjtu.bbgmer.adapter.OrderPagerViewAdapter;
import com.xjtu.bbgmer.app.AppContext;
import com.xjtu.bbgmer.bean.OrderBean;
import com.xjtu.bbgmer.utils.MyProperUtil;

/**
 * 订单页
 * @author Ming
 *
 */
public class MainTab01 extends Fragment {

	public static final int MODE_PULL_DOWN_TO_REFRESH = 0x1; // 下拉
	public static final int MODE_DEFAULT_LOAD = 0x2; // 上拉

	public static final int LISTENER_TYPE_NEW_ORDER = 0;
	public static final int LISTENER_TYPE_CANCELED_ORDER = 1;
	public static final int LISTENER_TYPE_ONSHIP_ORDER = 2;
	public static final int LISTENER_TYPE_SUCCESS_ORDER = 3;
	
	public static final int ORDER_TYPE_NEW = 0;
	public static final int ORDER_TYPE_CANCELED = 7;
	public static final int ORDER_TYPE_ONSHIP = 2;
	public static final int ORDER_TYPE_SUCCESS = 5;
	
	private PullToRefreshListView newOrderListView;
	private PullToRefreshListView canceledOrderListView;
	private PullToRefreshListView onshipOrderListView;
	private PullToRefreshListView successOrderListView;
	
	private PullRefreshListViewOnRefreshListener onNewOderRefreshListener = new PullRefreshListViewOnRefreshListener(LISTENER_TYPE_NEW_ORDER);
	private PullRefreshListViewOnRefreshListener onCanceledOderRefreshListener = new PullRefreshListViewOnRefreshListener(LISTENER_TYPE_CANCELED_ORDER);
	private PullRefreshListViewOnRefreshListener onOnshipOderRefreshListener = new PullRefreshListViewOnRefreshListener(LISTENER_TYPE_ONSHIP_ORDER);
	private PullRefreshListViewOnRefreshListener onSuccessOderRefreshListener = new PullRefreshListViewOnRefreshListener(LISTENER_TYPE_SUCCESS_ORDER);
	
	private OrderListViewAdapter newOrderListViewAdapter;
	private OrderListViewAdapter canceledOrderListViewAdapter;
	private OrderListViewAdapter onshipOrderListViewAdapter;
	private OrderListViewAdapter successOrderListViewAdapter;
	
	private List<OrderBean> newOrderData;
	private List<OrderBean> canceledOrderData;
	private List<OrderBean> onshipOrderData;
	private List<OrderBean> successOrderData;
	
	private ListView newOrderList;
	private ListView canceledOrderList;
	private ListView onshipOrderList;
	private ListView successOrderList;
	
	private OnItemClickListener listOnClickerListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
//			intent.putExtra("", value);
			startActivity(intent);
		}
	};
	
	
	@InjectView( id = R.id.id_order_tabs)
	private TabLayout mOrderTab;
	

	
	
	@InjectView(id = R.id.id_order_viewpager_main )
	private ViewPager mViewPager;                  
	
	private List<View> viewList; //添加在viewpager里的view
	private OrderPagerViewAdapter mOrderPageViewAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.main_tab_01, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		InjectUtil.inject(this) ; 
		initPullRefreshListView();
		initTabLayout();
	}
	
	/**
	 * 初始化TAB
	 */
	private void initTabLayout(){
		
		viewList = new ArrayList<View>();
		
		viewList.add(newOrderListView);
		viewList.add(canceledOrderListView);
		viewList.add(onshipOrderListView);
		viewList.add(successOrderListView);
		
		mOrderPageViewAdapter = new OrderPagerViewAdapter(viewList);
		
		//设置Tab的标题来自PagerAdapter.getPageTitle()
		mOrderTab.setTabsFromPagerAdapter(mOrderPageViewAdapter);
		
		mViewPager.setAdapter(mOrderPageViewAdapter);
		
		mOrderTab.setTabGravity(TabLayout.GRAVITY_FILL);
		
		mOrderTab.setupWithViewPager(mViewPager);
		
		
		
		
	}
	
	/**
	 * 初始化PullRefreshListView
	 */
	@SuppressLint("ResourceAsColor")
	private void initPullRefreshListView(){
		newOrderListView = new PullToRefreshListView(getActivity(),PullToRefreshListView.MODE_BOTH);
		canceledOrderListView = new PullToRefreshListView(getActivity());
		onshipOrderListView = new PullToRefreshListView(getActivity());
		successOrderListView = new PullToRefreshListView(getActivity());
		
		newOrderData = new ArrayList<OrderBean>();
		canceledOrderData = new ArrayList<OrderBean>();
		onshipOrderData = new ArrayList<OrderBean>();
		successOrderData = new ArrayList<OrderBean>();
		
		/**
		 * 测试数据
		 */
		newOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_NEW, "2015-12-11 12:30:21", "2"));
		canceledOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_CANCELED, "2015-12-11 12:30:21", "2"));
		onshipOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_ONSHIP ,"2015-12-11 12:30:21", "2"));
		successOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_SUCCESS, "2015-12-11 12:30:21", "2"));
		
		newOrderListViewAdapter = new OrderListViewAdapter(getActivity(), newOrderData);
		canceledOrderListViewAdapter = new OrderListViewAdapter(getActivity(), canceledOrderData);
		onshipOrderListViewAdapter = new OrderListViewAdapter(getActivity(), onshipOrderData);
		successOrderListViewAdapter = new OrderListViewAdapter(getActivity(), successOrderData);
		
		newOrderList = newOrderListView.getRefreshableView();
		newOrderList.setTag(ORDER_TYPE_NEW);
		newOrderList.setOnItemClickListener(listOnClickerListener);
		
		canceledOrderList = canceledOrderListView.getRefreshableView();
		onshipOrderList = onshipOrderListView.getRefreshableView();
		successOrderList = successOrderListView.getRefreshableView();
		
		newOrderList.setAdapter(newOrderListViewAdapter);
		canceledOrderList.setAdapter(canceledOrderListViewAdapter);
		onshipOrderList.setAdapter(onshipOrderListViewAdapter);
		successOrderList.setAdapter(successOrderListViewAdapter);
		
		
		newOrderListView.setOnRefreshListener(onNewOderRefreshListener);
		canceledOrderListView.setOnRefreshListener(onCanceledOderRefreshListener);
		onshipOrderListView.setOnRefreshListener(onOnshipOderRefreshListener);
		successOrderListView.setOnRefreshListener(onSuccessOderRefreshListener);
		
	}
	
	
	class PullRefreshListViewOnRefreshListener implements OnRefreshListener{

		private int type;
		
		public PullRefreshListViewOnRefreshListener(int type){
			this.type = type;
		}
		
		@Override
		public void onRefresh(int pullDirection) {
			// TODO Auto-generated method stub
			switch (pullDirection) {
			case MODE_PULL_DOWN_TO_REFRESH:
				updateData(type);
				break;
			case MODE_DEFAULT_LOAD:
				appendData(type);
				break;	
			default:

				break;
			}
		}
		
		/**
		 * 获取分店列表
		 */
		public void getShopBranchList(){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", AppContext.sid);
			String url = MyProperUtil.getProperties(getContext(),
					"appConfigDebugHost.properties").getProperty("Host")
					+ MyProperUtil.getProperties(getContext(), "appConfigDebug.properties")
							.getProperty("get_shop_branch_list");
			DhNet request = new DhNet(url, map);
			request.doGet(new NetTask(getContext()) {
				
				@Override
				public void doInUI(Response response, Integer transfer) {
					System.out.println(response.jSON());
					if(116 == JSONUtil.getInt(response.jSON(), "code")){
						//获得成功
						
						
					}else if(117 == JSONUtil.getInt(response.jSON(), "code")){
						//获取失败
						
					}
				}
			});
		}
		
		/**
		 * 下拉更新数据
		 * @param type
		 */
		public void updateData(int type){
			switch (type) {
			case ORDER_TYPE_NEW:
				newOrderData.clear();
				newOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_NEW, "2015-12-11 12:30:21", "2"));
				newOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_NEW, "2015-12-11 12:30:21", "2"));
				newOrderListViewAdapter.setData(newOrderData);
				newOrderListViewAdapter.notifyDataSetChanged();
				newOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_CANCELED:
				
				canceledOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_ONSHIP:
				onshipOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_SUCCESS:
				successOrderListView.onRefreshComplete();
				break;
			}
			
		}
		
		/**
		 * 上拉追加数据
		 * @param type
		 */
		public void appendData(int type){
			switch (type) {
			case ORDER_TYPE_NEW:
				newOrderData.add(new OrderBean("仲英书院东4楼402", "张三", "115790","0", "17791947787", ORDER_TYPE_NEW, "2015-12-11 12:30:21", "2"));
				newOrderListViewAdapter.setData(newOrderData);
				newOrderListViewAdapter.notifyDataSetChanged();
				newOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_CANCELED:
				
				canceledOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_ONSHIP:
				onshipOrderListView.onRefreshComplete();
				break;
			case ORDER_TYPE_SUCCESS:
				successOrderListView.onRefreshComplete();
				break;
			}
		}
		
		/**
		 * 
		 * @param sbid     分店id
		 * @param status  订单状态，0-新订单，7-取消订单，2-已发货，5-已收货
		 * @param offset  分页请求时的起始位置
		 * @param pageSize 分页时每页的大小
		 */
		public void getOrderList(final String sbid, final int status,
				final String offset, final String pageSize){
				
		}
		
		
	}
}	
