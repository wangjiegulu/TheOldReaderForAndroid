package com.wangjie.theoldreaderforandroid.util;

import android.graphics.Bitmap;
import com.wangjie.androidbucket.application.ABApplication;
import com.wangjie.androidbucket.log.LogConfig;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidinject.annotation.core.net.AINetWork;
import com.wangjie.imageloadersample.imageloader.CacheConfig;
import com.wangjie.imageloadersample.imageloader.ImageLoader;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class MyApplication extends ABApplication{

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void initNetWorkSSLScheme() {
        AINetWork.setOnSSLHttpClientSchemeRegister(new AINetWork.OnSSLHttpClientSchemeRegister() {
            @Override
            public Scheme getConfigScheme() {
                return new Scheme("https", PlainSocketFactory.getSocketFactory(), 80);
            }
        });
    }

    @Override
    protected void initLogger() {
        Logger.initLogger(
                new LogConfig()
                .setDebug(true)
                .setLogFile(false)
        );
    }

    @Override
    protected void initImageLoader() {
        ImageLoader.init(getApplicationContext(),
                new CacheConfig()
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .setDefRequiredSize(300)
                );
    }



}
