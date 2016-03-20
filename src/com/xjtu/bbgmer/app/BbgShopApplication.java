package com.xjtu.bbgmer.app;

import com.xjtu.bbgmer.bean.ShopBean;
import com.xjtu.bbgmer.fragment.MainTab01;

import net.duohuo.dhroid.Dhroid;
import net.duohuo.dhroid.dialog.DialogImpl;
import net.duohuo.dhroid.dialog.IDialog;
import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.ioc.Instance.InstanceScope;
import android.app.Application;

public class BbgShopApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Dhroid.init(this);

		IocContainer.getShare().bind(DialogImpl.class).to(IDialog.class)
		// 这是单例
				.scope(InstanceScope.SCOPE_SINGLETON);

		IocContainer.getShare().bind(ShopBean.class).name("shop")
				.scope(InstanceScope.SCOPE_SINGLETON);
	}
}
