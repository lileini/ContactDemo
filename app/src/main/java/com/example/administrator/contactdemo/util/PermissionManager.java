package com.example.administrator.contactdemo.util;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class PermissionManager {
	private String TAG ="PermissionManager.class";
	private Context context;
//	public static final String  = "AIS netcall does not have permission to access your Contacts, Please check.\n";

	public static final String  PERMISSION_RECORD_AUDIO= "android.permission.RECORD_AUDIO";
	public static final String  PERMISSION_READ_CONTACTS="android.permission.READ_CONTACTS";
	public static final String  PERMISSION_WRITE_CONTACTS="android.permission.WRITE_CONTACTS";
	public static final String  PERMISSION_READ_SMS="android.permission.READ_SMS";
//	public static final String  PERMISSION_="";
	
	
	public PermissionManager(Context context) {
		this.context = context;
	}


	/**
	 * check one times permission spend time 2-5ms
	 * @param permission
	 * @return
	 */
	public boolean checkPermission(String permission,boolean isShowToast) {
		if (TextUtils.isEmpty(permission))
			return false;
		PackageManager pm = context.getPackageManager();
		int pe = pm.checkPermission(permission, context.getPackageName());

		Log.i(TAG, "don't have permission of pe= " + pe + "~~" + context.getPackageName());

		if (PackageManager.PERMISSION_GRANTED == pe) {
			return true;
		}
		Log.i(TAG, "don't have permission of "+permission);
		if(isShowToast){
			if (permission.equals(PERMISSION_RECORD_AUDIO)){
				Toast.makeText(context, "Sorry, we are unable to access your microphone. Please check microphone setting.", Toast.LENGTH_LONG).show();
			}
			if (PERMISSION_READ_CONTACTS.equals(permission)){
				ToastUtil.showToast(context,"not have permission to access your Contacts, Please check.",1);
			}

		}
		
		return false;
	}
	public boolean checkPermissionAll(String permission,boolean isShowToast) {
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			return checkPermission3(permission,isShowToast);
		}else {
			return checkPermission(permission,isShowToast);
		}*/
		Log.i(TAG,"checkPermission3(permission,isShowToast) = "+ checkPermission3(permission,isShowToast));
		Log.i(TAG,"checkPermission(permission,isShowToast) = "+ checkPermission(permission,isShowToast));
		return checkPermission3(permission,isShowToast) && checkPermission(permission,isShowToast);


	}
	/**
	 * check one times permission spend time 2-5ms
	 * @param permission
	 * @return
	 */
	public boolean checkPermission3(String permission,boolean isShowToast) {
		int permissionCheck = ContextCompat.checkSelfPermission(context,
				permission);
		if (permissionCheck == PackageManager.PERMISSION_GRANTED){
			return true;
		}
		if(isShowToast){
			if (PERMISSION_READ_CONTACTS.equals(permission)){
				Toast.makeText(context, "not have permission to access your Contacts, Please check.", Toast.LENGTH_LONG).show();
			}
			if (PERMISSION_RECORD_AUDIO.equals(permission)){
				Toast.makeText(context, "Sorry, we are unable to access your microphone. Please check microphone setting.", Toast.LENGTH_LONG).show();
			}
		}
		Log.i(TAG, "don't have permission of="+permission+"~~"+context.getPackageName());
		return false;

	}

	
	public boolean checkPermission2(String permission,boolean isShowToast) {
	
		PackageManager pm = context.getPackageManager();
		int pe = pm.checkPermission(permission, context.getPackageName());
		if (PackageManager.PERMISSION_GRANTED == pe) {
			return true;
		}
		Log.i(TAG, "don't have permission of "+permission);
		if(isShowToast){
			if (context instanceof Activity) {
				Activity activity = (Activity) context;
				if (activity.isFinishing()) {
					Log.i(TAG, "activity is finish");
					return false;
				}
				activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(context, "Sorry, we are unable to access your microphone. Please check microphone setting.", Toast.LENGTH_LONG).show();
					}
				});
			}
			
		}
		
		return false;
	}


}
