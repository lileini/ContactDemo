package com.example.administrator.contactdemo.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.contactdemo.MyApplication;
import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.adapter.PhoneAdapter;
import com.example.administrator.contactdemo.broadcastreciever.NetworkConnectStateReceiver;
import com.example.administrator.contactdemo.contact.PhoneDao;
import com.example.administrator.contactdemo.contact.PhoneDbHelper;
import com.example.administrator.contactdemo.contact.PhonesLoader;
import com.example.administrator.contactdemo.entity.Const;
import com.example.administrator.contactdemo.entity.GrucMobilesResult;
import com.example.administrator.contactdemo.observer.ContactObserver;
import com.example.administrator.contactdemo.service.ContactService;
import com.example.administrator.contactdemo.util.NetworkManager;
import com.example.administrator.contactdemo.util.PermissionManager;
import com.example.administrator.contactdemo.util.SharePrefrenceManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;
    PhoneAdapter phoneAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (new SharePrefrenceManager(this).getIsFirstLoad()){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            init();
                        }
                    });

                }
            }, 2000);
        }else {
            init();
        }
    }
    ContactObserver mContactObserver;
    PhoneDbHelper phoneDbHelper;
    PhoneDao phoneDao;
    NetworkConnectStateReceiver networkConnectStateReceiver;
    private void init() {
        EventBus.getDefault().register(this);
        phoneDbHelper = new PhoneDbHelper(MainActivity.this);
        mContactObserver = new ContactObserver(this,handler);
        startPhoneLoader();


        registerContactObserverAndReceiver();
    }

    private void startPhoneLoader() {
        if (new PermissionManager(this).checkPermissionAll(PermissionManager.PERMISSION_READ_CONTACTS, true)) {
            try {
                Loader loader = getLoaderManager().initLoader(0, null, PhonesLoader.getInstance(this));
                loader.startLoading();
            } catch (Exception e) {
                Log.i(TAG, "loader 启动失败");
                e.printStackTrace();
            }
        }
    }

    //处理联系人改变
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startPhoneLoader();
        }
    };

    private void registerContactObserverAndReceiver() {
        IntentFilter filter = new IntentFilter(Const.CONTACT_LOAD_FINISHED);
        registerReceiver(phoneLoadReciever, filter);
        networkConnectStateReceiver = new NetworkConnectStateReceiver();
        registerReceiver(networkConnectStateReceiver, new IntentFilter(NetworkConnectStateReceiver.ACTION));

        this.getContentResolver().registerContentObserver(ContactsContract.RawContacts.CONTENT_URI, true, mContactObserver);
    }

    private void unRegisterReceiverAndObserver() {
        unregisterReceiver(phoneLoadReciever);
        unregisterReceiver(networkConnectStateReceiver);
        this.getContentResolver().unregisterContentObserver(mContactObserver);

    }

    /**
     * 接收eventBus发送的事件，更新电话的标记
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 100)
    public void updatePhonesTag(GrucMobilesResult.MatchsEntity matchsEntity){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (phoneAdapter != null)
                    phoneAdapter.notifyDataSetChanged();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                phoneDbHelper.onUpgrade(phoneDbHelper.getWritableDatabase(),0,1);
                if (phoneDao == null){
                    phoneDao  = new PhoneDao(MainActivity.this);
                }
                phoneDao.savePhones(Const.phoneList);
            }
        }).start();
    }


    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    private BroadcastReceiver phoneLoadReciever  = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            SharePrefrenceManager sharePrefrenceManager = new SharePrefrenceManager(MyApplication.getInstance());
            if (!NetworkManager.isNetworkAvailable(MyApplication.getInstance())){
                Toast.makeText(MyApplication.getInstance(), R.string.problem_internet, Toast.LENGTH_SHORT).show();
                //如果没有网络直接从数据空中加载数据
                return;
            }
            if (phoneAdapter == null){
                phoneAdapter = new PhoneAdapter(MainActivity.this, Const.phoneList);
                listView.setAdapter(phoneAdapter);
            }else {
                phoneAdapter.setPhoneList(Const.phoneList);
            }
            Intent intentService = new Intent(MainActivity.this, ContactService.class);
            MainActivity.this.startService(intentService);

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
        unRegisterReceiverAndObserver();
    }

}
