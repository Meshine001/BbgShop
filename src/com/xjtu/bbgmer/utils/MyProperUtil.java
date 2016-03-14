package com.xjtu.bbgmer.utils;

import java.io.InputStream;
import java.util.Properties;

import android.content.Context;

/**
 * 读取配置文件工具
 * @author Ming
 *
 */
public class MyProperUtil {
	private static Properties urlProps;

	public static Properties getProperties(Context c,String fileName) {
		Properties props = new Properties();
		try {
			// 方法一：通过activity中的context攻取setting.properties的FileInputStream
			InputStream in = c.getAssets().open(fileName);
			// 方法二：通过class获取setting.properties的FileInputStream
			// InputStream in =
			// PropertiesUtill.class.getResourceAsStream("/assets/  setting.properties "));
			props.load(in);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		urlProps = props;
		return urlProps;
	}
}
