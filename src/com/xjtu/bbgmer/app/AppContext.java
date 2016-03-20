package com.xjtu.bbgmer.app;

import java.util.ArrayList;
import java.util.List;

import com.xjtu.bbgmer.bean.ShopBranchBean;

/**
 * App的相关公共配置信息
 * 
 * @author Ming
 *
 */
public class AppContext {
	//
	public static String uid = "";
	public static String sid = "";
	public static String sbid ="";
	public static String job = "";
	public static String shopStatus = "";
	public static String reason = "";
	public static String editor = "";
	
	
	
	// 网络请求常量
	public static final String CERCODEVALUE = "6c3dfc3f-1dc5-4013-9b80-037e4c9f7b32";
	public static final String CERCODEKEY = "bbg-cercode";
	public static final String CODE_ACCOUNT_ERR="4";
	public static final String CODE_SIGN_UP_SUCCESS="3";
	public static final String STATUS_OK="1";
	public static final String CODE_SHOP_SIGN_UP_SUCCESS="22";
}
