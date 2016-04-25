package com.example.administrator.contactdemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.contactdemo.BuildConfig;
import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.contact.PhoneDao;
import com.example.administrator.contactdemo.entity.Const;
import com.example.administrator.contactdemo.entity.GrucUserResult;
import com.example.administrator.contactdemo.entity.UserDate;
import com.example.administrator.contactdemo.gruc.Gruc;
import com.example.administrator.contactdemo.gruc.GrucDao;
import com.example.administrator.contactdemo.gruc.GrucDbHelper;
import com.example.administrator.contactdemo.util.GsonUtil;
import com.example.administrator.contactdemo.util.HttpUtils;
import com.example.administrator.contactdemo.util.NetworkManager;
import com.example.administrator.contactdemo.util.SharePrefrenceManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.List;

public class GrucService extends IntentService {


    private static final String TAG = "GrucService";
    SharePrefrenceManager sharePrefrenceManager;
    private PhoneDao phoneDao;
    private GrucDao grucDao;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public GrucService() {
        super("ContactService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharePrefrenceManager = new SharePrefrenceManager(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (TextUtils.isEmpty(sharePrefrenceManager.getAccessToken())) {//这里没考虑token的失效日期
            requestAccessToken();
        } else {
            if (NetworkManager.isNetworkAvailable(this)) {

                requestAllGrucUser(100, 1);
            } else {
                Toast.makeText(this, R.string.problem_internet, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestAllGrucUser(final int results_per_page, final int page) {
        String Url = Const.GRUC_USER_URL+"?results_per_page="+results_per_page+"&page="+page;
        RequestParams params = new RequestParams(Url);
        params.addHeader("Content-Type", "application/json");
        Log.i(TAG, "sharePrefrenceManager.getAccessToken() = " + sharePrefrenceManager.getAccessToken());
        params.addHeader("Authorization", "JWT "+sharePrefrenceManager.getAccessToken());
        HttpUtils.HttpGetMethod(new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "requestAllGrucUser success result = " + result);
                GrucUserResult grucUserResult = GsonUtil.getResult(result,GrucUserResult.class);
                if (grucUserResult == null)
                    return;
                List<Gruc> objects = grucUserResult.getObjects();
                int total_pages = grucUserResult.getTotal_pages();
                int page1 = grucUserResult.getPage();
                Log.i(TAG,"total_pages = "+total_pages);
                Log.i(TAG,"page1 = "+page1);
                if (objects == null)
                    return;
                if (page1 <= total_pages){
                    if (page1 == 1 ){
                        Const.grucUsersList = objects;
                    }else {
                        Const.grucUsersList.addAll(objects);
                    }
                    if (page1 < total_pages){

                        requestAllGrucUser(results_per_page,page1+1);
                        return;
                    }
                }
                Log.i(TAG, "begain save");
                GrucDbHelper grucDbHelper = GrucDbHelper.getInstance(GrucService.this,null);
                grucDbHelper.onUpgrade(grucDbHelper.getWritableDatabase(),1,1);
//                GrucDbHelper.getInstance(GrucService.this,null).dropTable();
//                GrucDbHelper.getInstance(GrucService.this,null).creatTableAfterDrop();

                //                grucDbHelper.onCreate(grucDbHelper.getWritableDatabase());
                //                grucDbHelper.onUpgrade(grucDbHelper.getWritableDatabase(),1,1);
                if (grucDao == null){
                    grucDao  = new GrucDao(GrucService.this,null);
                }
                grucDao.saveGrucList(Const.grucUsersList);
                Log.i(TAG, "end save");
                Log.i(TAG,"Const.grucUsersList.size() = "+Const.grucUsersList.size());
                //                EventBus.getDefault().post();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "requestAllGrucUser ex.getMessage() = " + ex.getMessage());
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "ex.toString() = " + ex.toString());
                String result = ex.toString();
//                result = result.substring(result.indexOf("result: ") + 8);
                Log.i(TAG, "requestAllGrucUser result = " + result);
                if (result.contains("PermissionError:this api need auth")) {
                    requestAccessToken();
                    Log.i(TAG, " requestAccessToken()");
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        }, params);
    }

//    {"username": "qingquan","password":"123456","domain":"caas.grcaassip.com"}
    private void requestAccessToken() {
        String URL = Const.ACCESS_TOKEN_URL;
        RequestParams params = new RequestParams(URL);
        UserDate userDate = new UserDate("qingquan","123456","caas.grcaassip.com");

        Gson gson = new Gson();
        String s = gson.toJson(userDate);
        params.setBodyContent(s);
        params.addHeader("Content-Type", "application/json");
        HttpUtils.HttpPostMethod(new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "requestAccessToken result = " + result);
                try {
                    JSONObject object = new JSONObject(result);
                    String access_token = object.getString("access_token");
                    Log.i(TAG,"token = "+ access_token);
                    if (!TextUtils.isEmpty(access_token)) {
                        sharePrefrenceManager.saveAccessToken(access_token);
                    }
                    requestAllGrucUser(100,1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "requestAccessToken ex.getMessage() = " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        },params);
    }
}
