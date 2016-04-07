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
import com.example.administrator.contactdemo.contact.Phone;
import com.example.administrator.contactdemo.entity.Const;
import com.example.administrator.contactdemo.entity.GrucMobile;
import com.example.administrator.contactdemo.entity.GrucMobilesResult;
import com.example.administrator.contactdemo.entity.TokenErrorResult;
import com.example.administrator.contactdemo.entity.UserDate;
import com.example.administrator.contactdemo.util.GsonUtil;
import com.example.administrator.contactdemo.util.HttpUtils;
import com.example.administrator.contactdemo.util.NetworkManager;
import com.example.administrator.contactdemo.util.SharePrefrenceManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class ContactService extends IntentService {


    private static final String TAG = "ContactService";
    SharePrefrenceManager sharePrefrenceManager;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ContactService() {
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
        if (TextUtils.isEmpty(sharePrefrenceManager.getAccessToken())){//这里没考虑token的失效日期
                requestAccessToken();
            }else {
                if (NetworkManager.isNetworkAvailable(this)){

                    requestGrucMobile();
                }else {
                    Toast.makeText(this, R.string.problem_internet, Toast.LENGTH_SHORT).show();
                }
            }

    }
    private void requestGrucMobile() {
        String Url = Const.CONFIRM_GRUC_MOBILE_URL;
        RequestParams params = new RequestParams(Url);
        GrucMobile grucMobile = new GrucMobile();
        List<String> mobileList = new ArrayList<>();
        for (Phone phone : Const.phoneList){
            mobileList.add(phone.getCountryCode()+phone.getNumber());
        }
        grucMobile.setMobiles(mobileList);
        String s = GsonUtil.toJson(grucMobile);
        params.setBodyContent(s);
        params.addHeader("Content-Type", "application/json");
        Log.i(TAG, "sharePrefrenceManager.getAccessToken() = " + sharePrefrenceManager.getAccessToken());
        params.addHeader("Authorization", "JWT "+sharePrefrenceManager.getAccessToken());
        HttpUtils.HttpPostMethod(new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "result = " + result);
                GrucMobilesResult mobilesResult = GsonUtil.getResult(result, GrucMobilesResult.class);
                if (mobilesResult.getCode() != 200)
                    return;
                List<GrucMobilesResult.MatchsEntity> matchs = mobilesResult.getMatchs();
                if (matchs == null || matchs.size() ==0)
                    return;
                for (GrucMobilesResult.MatchsEntity matchsEntity : matchs){
                    String m = matchsEntity.getM();
                    if (Const.phoneMap == null)
                        return;
                    Phone phone = Const.phoneMap.get(m);
                    if (phone == null)
                        return;
                    if (phone.getGrucType() ==Phone.GrucType.SYSTEM){
                        phone.setGrucType(Phone.GrucType.GRUC);
                        phone.setGruc_name(matchsEntity.getU());
                        phone.setGruc_photo(matchsEntity.getI());
//                        Log.i(TAG, "matchsEntity.getI() = "+ matchsEntity.getI());
                    }
                }
                EventBus.getDefault().post(matchs.get(0));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "ex.getMessage() = " + ex.getMessage());
                if (BuildConfig.DEBUG)
                    Log.d(TAG,"ex.toString() = "+ex.toString());
                String result = ex.toString();
                result = result.substring(result.indexOf("result: ")+8);
                Log.i(TAG,"result = "+result);
                TokenErrorResult tokenErrorResult = GsonUtil.getResult(result, TokenErrorResult.class);
                if ("PermissionError:this api need auth".equals(tokenErrorResult.getDescription())){
                    requestAccessToken();
                    Log.i(TAG," requestAccessToken()");
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
                Log.d(TAG, "result = " + result);
                try {
                    JSONObject object = new JSONObject(result);
                    String access_token = object.getString("access_token");
                    Log.i(TAG,"token = "+ access_token);
                    if (!TextUtils.isEmpty(access_token)) {
                        sharePrefrenceManager.saveAccessToken(access_token);
                    }
                    requestGrucMobile();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "ex.getMessage() = " + ex.getMessage());
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
