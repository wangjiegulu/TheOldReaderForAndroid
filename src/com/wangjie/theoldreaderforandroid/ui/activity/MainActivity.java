package com.wangjie.theoldreaderforandroid.ui.activity;

import android.os.Bundle;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.thread.Runtask;
import com.wangjie.androidbucket.thread.ThreadPool;
import com.wangjie.androidbucket.utils.ABPrefsUtil;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.core.net.AINetWork;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.constant.ConstantUrl;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import com.wangjie.theoldreaderforandroid.ui.base.BaseActivity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

@AILayout(R.layout.main)
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ThreadPool.go(new Runtask<Object, Object>() {
            @Override
            public Object runInBackground() {

                HttpClient httpClient = AINetWork.getSSLHttpClient(20000, 20000);
                HttpGet httpGet = new HttpGet(ConstantUrl.URL_USER_INFO);
                httpGet.addHeader("Authorization", "GoogleLogin auth=" + ABPrefsUtil.getInstance().getString(PrefsKey.KEY_AUTH));

                try {
                    StringBuilder sb = AINetWork.obtainStringFromInputStream(httpClient.execute(httpGet).getEntity().getContent());
                    Logger.d(TAG, null == sb ? "null" : sb.toString());

                } catch (IOException e) {
                    Logger.e(TAG, e);
                }

                return null;
            }
        });

















    }
}
