package com.xjtu.bbgmer.bean;

import java.util.ArrayList;
import java.util.List;

public class ShopBean {
	private List<ShopBranchBean> shopBranchs;

	public ShopBean() {
		super();
		// TODO Auto-generated constructor stub
		shopBranchs = new ArrayList<ShopBranchBean>();
	}

	public List<ShopBranchBean> getShopBranchs() {
		return shopBranchs;
	}

	public void setShopBranchs(List<ShopBranchBean> shopBranchs) {
		this.shopBranchs = shopBranchs;
	}
	
}
