package com.xjtu.bbgmer.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * "address": "仲英书院东5楼", "contactName": "任7", "orderId": 17760256,
	 * "payMethod": 0, "phoneNum": "17791947766", "status": 2, "time":
	 * 1453906245000, "timeRange": "21:20~23:00"
	 */
	/*0-未处理，1-卖家已接单，2-卖家发货，3-快递员接单，4-快递员已收货，5-收货未评价6-已评价，
	7-买家取消订单，8-卖家同意取消订单但买家尚未收款9-取消订单成功，10-拒绝取消订单，
	11-订单采用线上支付，但是尚未付款，12-申请退货，13-卖家同意退货，
	14-退货完成，15-卖家拒绝退货，16-买家删除订单，17-卖家删除订单，18-快递员删除订单*/
	private String address;
	private String contactName;
	private String orderId;
	private String payMethod;//0-线下支付，1-支付宝，2-微信支付，3-银联支付，4-我的钱包支付
	private String phoneNum;
	private int status;
	private String time;
	private String timeRange;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public OrderBean(String address, String contactName, String orderId,
			String payMethod, String phoneNum, int status, String time,
			String timeRange) {
		super();
		this.address = address;
		this.contactName = contactName;
		this.orderId = orderId;
		this.payMethod = payMethod;
		this.phoneNum = phoneNum;
		this.status = status;
		this.time = time;
		this.timeRange = timeRange;
	}

	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderBean [address=" + address + ", contactName=" + contactName
				+ ", orderId=" + orderId + ", payMethod=" + payMethod
				+ ", phoneNum=" + phoneNum + ", status=" + status + ", time="
				+ time + ", timeRange=" + timeRange + "]";
	}

	
	
}
