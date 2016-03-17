package com.xjtu.bbgmer.activity;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.InjectUtil;
import net.duohuo.dhroid.ioc.annotation.Inject;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.duohuo.dhroid.util.DhUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.xjtu.bbgmer.R;
import com.xjtu.bbgmer.fragment.MainTab01;
import com.xjtu.bbgmer.fragment.MainTab02;
import com.xjtu.bbgmer.fragment.MainTab03;
import com.xjtu.bbgmer.fragment.MainTab04;

public class MainActivity extends FragmentActivity {

	public Fragment mTab01;
	public Fragment mTab02;
	public Fragment mTab03;
	public Fragment  mTab04;

	// 底部Tab
	@InjectView(id = R.id.id_tab_bottom_order, click = "onClickCallBack")
	private LinearLayout mTabBtnOder;
	@InjectView(id = R.id.id_tab_bottom_goods, click = "onClickCallBack")
	private LinearLayout mTabBtnGoods;
	@InjectView(id = R.id.id_tab_bottom_message, click = "onClickCallBack")
	private LinearLayout mTabBtnMessage;
	@InjectView(id = R.id.id_tab_bottom_personal, click = "onClickCallBack")
	private LinearLayout mTabBtnPersonal;

	// Tab背景
	@InjectView(id = R.id.id_btn_tab_bottom_order)
	private ImageButton mBtnOrder;
	@InjectView(id = R.id.id_btn_tab_bottom_goods)
	private ImageButton mBtnGoods;
	@InjectView(id = R.id.id_btn_tab_bottom_message)
	private ImageButton mBtnMessager;
	@InjectView(id = R.id.id_btn_tab_bottom_personal)
	private ImageButton mBtnPersonal;

	// 用于管理Tabs
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InjectUtil.inject(this);
	
		// 获取Fragment管理器		
		fragmentManager = getSupportFragmentManager();
		// 设置第一个Tab被选中
		setTabSelection(1);
	}

	/**
	 * 处理Tab点击事件
	 * 
	 * @param view
	 */
	public void onClickCallBack(View view) {
		switch (view.getId()) {
		case R.id.id_tab_bottom_order:
			setTabSelection(1);
			break;
		case R.id.id_tab_bottom_goods:
			setTabSelection(2);
			break;
		case R.id.id_tab_bottom_message:
			setTabSelection(3);
			break;
		case R.id.id_tab_bottom_personal:
			setTabSelection(4);
			break;

		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页
	 * 
	 * @param index
	 */
	private void setTabSelection(int index) {
		// 重置所有按钮
		resetBtn();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		
		switch (index) {
		case 1:
			mBtnOrder.setSelected(true);
			if(mTab01 == null){
				// 如果MessageFragment为空，则创建一个并添加到界面上  
				mTab01 = new MainTab01();
				transaction.add(R.id.id_main_content, mTab01);
			}else{
				// 如果MessageFragment不为空，则直接将它显示出来  
                transaction.show(mTab01);  
			}

			break;
		case 2:
			mBtnGoods.setSelected(true);
			if(mTab02 == null){
				// 如果MessageFragment为空，则创建一个并添加到界面上  
				mTab02 = new MainTab02();
				transaction.add(R.id.id_main_content, mTab02);
			}else{
				// 如果MessageFragment不为空，则直接将它显示出来  
                transaction.show(mTab02);  
			}
			break;
		case 3:
			mBtnMessager.setSelected(true);
			if(mTab03 == null){
				// 如果MessageFragment为空，则创建一个并添加到界面上  
				mTab03 = new MainTab03();
				transaction.add(R.id.id_main_content, mTab03);
			}else{
				// 如果MessageFragment不为空，则直接将它显示出来  
                transaction.show(mTab03);  
			}
			break;
		case 4:
			mBtnPersonal.setSelected(true);
			if(mTab04 == null){
				// 如果MessageFragment为空，则创建一个并添加到界面上  
				mTab04 = new MainTab04();
				transaction.add(R.id.id_main_content, mTab04);
			}else{
				// 如果MessageFragment不为空，则直接将它显示出来  
                transaction.show(mTab04);  
			}
			break;
		}
		
		transaction.commit();
	}

	/**
	 * 清除所有选中状态
	 */
	private void resetBtn() {
		mBtnOrder.setSelected(false);
		mBtnGoods.setSelected(false);
		mBtnMessager.setSelected(false);
		mBtnPersonal.setSelected(false);
	}

	/**
	 * 隐藏所有Fragment
	 * 
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mTab01 != null) {
			transaction.hide(mTab01);
		}
		if (mTab02 != null) {
			transaction.hide(mTab02);
		}
		if (mTab03 != null) {
			transaction.hide(mTab03);
		}
		if (mTab04 != null) {
			transaction.hide(mTab04);
		}
	}

}
