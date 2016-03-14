package com.xjtu.bbgmer.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.xjtu.bbgmer.R;
import com.xjtu.bbgmer.app.AppContext;
import com.xjtu.bbgmer.utils.CusPerference;
import com.xjtu.bbgmer.utils.MyProperUtil;

import net.duohuo.dhroid.activity.BaseActivity;
import net.duohuo.dhroid.ioc.annotation.Inject;
import net.duohuo.dhroid.ioc.annotation.InjectView;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登录
 * @author Ming
 *
 */
public class LoginActivity extends BaseActivity{
	@InjectView(id=R.id.id_login_username)
	private EditText etUsername;
	@InjectView(id = R.id.id_login_password)
	private EditText etPassword;
	@InjectView(id= R.id.id_login_sign_up,click = "onClickCallBack")
	private Button btnSignUp;
	@Inject
	CusPerference cusPerference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	/**
	 * 点击事件
	 * @param view
	 */
	public void onClickCallBack(View view){
		switch (view.getId()) {
		case R.id.id_login_sign_up:	
			if (isEmpty(etUsername.getText().toString().trim())
					|| isEmpty(etPassword.getText().toString().trim())) {
				Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_SHORT).show();
			} else {
				signUp(etUsername.getText().toString().trim(),
						etPassword.getText().toString().trim());
			}
			break;

		default:
			break;
		}
	}
	public void doShopLoginIn(final String uid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		JSONObject json = new JSONObject(map);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", json.toString());
		String url = MyProperUtil.getProperties(this,
				"appConfigDebugHost.properties").getProperty("Host")
				+ MyProperUtil.getProperties(this, "appConfigDebug.properties")
						.getProperty("shop_login");
		DhNet request=new DhNet(url, params);
		request.doGet(new NetTask(LoginActivity.this) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (AppContext.CODE_SHOP_SIGN_UP_SUCCESS.equals(JSONUtil.getString(response.jSON(), "code"))) {
					Toast.makeText(LoginActivity.this,
							"商家登录成功",Toast.LENGTH_SHORT).show();	
				}
				else{
					Toast.makeText(LoginActivity.this,
							"您尚不属于任何店铺，马上申请开设自己的小店吧",Toast.LENGTH_SHORT).show();	
	
				}
			}
		});
		
		
		
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	public void signUp(final String username,final String password) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("passwd", password);
		map.put("cercodeValue", AppContext.CERCODEVALUE);
		map.put("cercodeKey", AppContext.CERCODEKEY);
		JSONObject json = new JSONObject(map);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", json.toString());
		String url = MyProperUtil.getProperties(this,
				"appConfigDebugHost.properties").getProperty("Host")
				+ MyProperUtil.getProperties(this, "appConfigDebug.properties")
						.getProperty("login");
		DhNet request = new DhNet(url, params);
		System.out.println("开始登录.."+params);
		request.doPost(new NetTask(LoginActivity.this) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				System.out.println(response.jSON());
				if (AppContext.CODE_SIGN_UP_SUCCESS.equals(JSONUtil.getString(response.jSON(), "code"))) {
					String uid = JSONUtil.getString(response.jSON(), "data.id");
					cusPerference.userName = username;
					cusPerference.uid = uid;
					cusPerference.password = password;
					cusPerference.commit();
					AppContext.uid=uid;
				
					if(AppContext.STATUS_OK.equals(JSONUtil.getString(response.jSON(), "data.status"))){
					  
						
								
					}
					}	
				else if(AppContext.CODE_ACCOUNT_ERR.equals(JSONUtil.getString(response.jSON(), "code")))
				{
					Toast.makeText(LoginActivity.this,
							"账号异常",Toast.LENGTH_SHORT).show();	
				}
				else{
					Toast.makeText(LoginActivity.this,
							"用户名或密码错误",Toast.LENGTH_SHORT).show();	
				}
			}
		});
	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断字符串是否为空
	 * @param @param str
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
}
