package com.example.administrator.contactdemo.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.administrator.contactdemo.util.ClickUtil;

public class NetworkConnectStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkConnectStateRec";
    NetworkInfo.State wifiState = null;
    NetworkInfo.State mobileState = null;
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    /*private NetworkConnectStateReceiver(){

    }
    public static NetworkConnectStateReceiver getInstance(){
        if (instance ==null)
            instance = new NetworkConnectStateReceiver();
        return instance;
    }*/
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (ACTION.equals(intent.getAction())) {
            //获取手机的连接服务管理器，这里是连接管理器类
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            //                mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            if (wifiState != null /*&& mobileState != null*/ && NetworkInfo.State.CONNECTED == wifiState) {
                if (ClickUtil.isFastDoubleClick()){
                    return;
                }
                //如果网络改变重新查询联系人
//                EventBus.getDefault().post();
                Log.i(TAG, "无限网络连上了");
            } else if (wifiState != null /*&& mobileState != null*/ && NetworkInfo.State.CONNECTED != wifiState) {
                Log.i(TAG, "网络断开了");
            }
        }
    }

}
