package com.example.administrator.contactdemo.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.contactdemo.MyApplication;
import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.adapter.PhoneAdapter;
import com.example.administrator.contactdemo.contact.Phone;
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

import java.util.List;
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
    private void init() {
        EventBus.getDefault().register(this);
        mContactObserver = new ContactObserver(this,null);
        if (new PermissionManager(this).checkPermissionAll(PermissionManager.PERMISSION_READ_CONTACTS, true)) {
            try {
                Loader loader = getLoaderManager().initLoader(0, null, PhonesLoader.getInstance(this));
                loader.startLoading();
            } catch (Exception e) {
                Log.i(TAG,"loader 启动失败");
                e.printStackTrace();
            }
        }


        registerContactObserverAndReceiver();
    }


    private void registerContactObserverAndReceiver() {
        IntentFilter filter = new IntentFilter(Const.CONTACT_LOAD_FINISHED);
        registerReceiver(phoneLoadReciever, filter);
        this.getContentResolver().registerContentObserver(ContactsContract.RawContacts.CONTENT_URI, true, mContactObserver);
    }

    private void unRegisterReceiverAndObserver() {
        unregisterReceiver(phoneLoadReciever);
        this.getContentResolver().unregisterContentObserver(mContactObserver);

    }
    /**
     * 接收eventBus发送的事件，更新电话的标记
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true, priority = 100)
    public void updatePhonesTag(GrucMobilesResult grucMobilesResult){
        List<String> matchs = grucMobilesResult.getMatchs();
        for (String s : matchs){
            if (Const.phoneMap == null)
                return;
            Phone phone = Const.phoneMap.get(s);
            if (phone !=  null){
                if (phone.getGrucType() == Phone.GrucType.SYSTEM)
                    phone.setGrucType(Phone.GrucType.GRUC);
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (phoneAdapter != null)
                    phoneAdapter.notifyDataSetChanged();
            }
        });
    }


    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    private SharePrefrenceManager sharePrefrenceManager;
    private BroadcastReceiver phoneLoadReciever  = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (phoneAdapter == null){
                phoneAdapter = new PhoneAdapter(MainActivity.this, Const.phoneList);
                listView.setAdapter(phoneAdapter);
            }else {
                phoneAdapter.notifyDataSetChanged();
            }

            sharePrefrenceManager = new SharePrefrenceManager(MyApplication.getInstance());
            if (!NetworkManager.isNetworkAvailable(MyApplication.getInstance())){
                Toast.makeText(MyApplication.getInstance(), R.string.problem_internet, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intentService = new Intent(MainActivity.this, ContactService.class);
            intentService.addFlags(Const.INTENT_FLAG0);
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
