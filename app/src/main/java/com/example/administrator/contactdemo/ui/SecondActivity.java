package com.example.administrator.contactdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.administrator.contactdemo.BuildConfig;
import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.entity.Const;
import com.example.administrator.contactdemo.entity.GrucMobile;
import com.example.administrator.contactdemo.entity.GrucMobilesResult;
import com.example.administrator.contactdemo.entity.UserDate;
import com.example.administrator.contactdemo.util.GsonUtil;
import com.example.administrator.contactdemo.util.HttpUtils;
import com.example.administrator.contactdemo.util.SharePrefrenceManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecondActivity extends Activity implements OnClickListener{
    @Bind(R.id.button)
    Button button;
    private final String TAG = "SecondActivity";
    SharePrefrenceManager sharePrefrenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        button.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:

//            {"username": "d1_m1_u1","password":"123456","domain":"d1_m1.gnum.com"}


                break;
        }

    }

    private void requestGrucMobile() {
        String Url = Const.CONFIRM_GRUC_MOBILE_URL;
        RequestParams params = new RequestParams(Url);
        GrucMobile grucMobile = new GrucMobile();
        List<String> mobileList = new ArrayList<>();
        mobileList.add("200");
        mobileList.add("199");
        mobileList.add("231");
        mobileList.add("220");
        grucMobile.setMobiles(mobileList);
        String s = GsonUtil.toJson(grucMobile);
        params.setBodyContent(s);
        params.addHeader("Content-Type", "application/json");
        Log.i(TAG,"sharePrefrenceManager.getAccessToken() = "+sharePrefrenceManager.getAccessToken());
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
                EventBus.getDefault().post(mobilesResult);
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

    private void requestAccessToken() {
        String URL = Const.ACCESS_TOKEN_URL;
        RequestParams params = new RequestParams(URL);
        UserDate userDate = new UserDate("d1_m1_u1","123456","d1_m1.gnum.com");

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
                Log.d(TAG, "e.getMessage() = " + ex.getMessage());

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
