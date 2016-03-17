package com.xjtu.bbgmer.utils;

/**
 * 放映射表
 * 
 * @ClassName: 
 * @Description: 
 * @author zhongfeng.rzf
 * @date 2016年3月5日下午8:19:23
 *
 */
public class MapUtil {

	public static String getOrderStatus(int key){
		switch (key) {
		case 0:			
			return "未处理";
		case 1:			
			return "已接单";
		case 2:			
			return "已发货";
		case 3:			
			return "快递员已接单";
		case 4:			
			return "快递员已收货";
		case 5:			
			return "已收货";
		case 6:			
			return "已评价";
		case 7:			
			return "申请取消订单";
		case 8:			
			return "已同意取消";
		case 9:			
			return "取消成功";
		case 10:			
			return "拒绝取消";
		case 11:			
			return "未付款";
		case 12:			
			return "申请退货";
		case 13:			
			return "同意退货";
		case 14:			
			return "退货完成";
		case 15:			
			return "拒绝退货";
		default:
			return null;
		}
	}
}
