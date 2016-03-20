package com.xjtu.bbgmer.bean;

public class ShopBranchBean {
	private int sbid;
	private String shopName;
	private int status;
	private String picture;
	
	
	public ShopBranchBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ShopBranchBean(int sbid, String shopName, int status, String picture) {
		super();
		this.sbid = sbid;
		this.shopName = shopName;
		this.status = status;
		this.picture = picture;
	}

	public int getSbid() {
		return sbid;
	}
	public void setSbid(int sbid) {
		this.sbid = sbid;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "ShopBranchBean [sbid=" + sbid + ", shopName=" + shopName
				+ ", status=" + status + ", picture=" + picture + "]";
	}
	
	
}
