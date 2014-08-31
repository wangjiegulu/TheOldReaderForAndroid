package com.wangjie.theoldreaderforandroid.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.wangjie.androidinject.annotation.core.base.AnnotationManager;
import com.wangjie.androidinject.annotation.present.AIActivity;
import com.wangjie.androidinject.annotation.present.AIPresent;
import com.wangjie.androidinject.annotation.present.common.CallbackSample;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class BaseActivity extends ActionBarActivity implements AIPresent, CallbackSample {

    private static final String TAG = AIActivity.class.getSimpleName();
    public Context context;
    public Class<?> clazz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long start = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        context = this;
        clazz = ((Object)this).getClass();
//        clazz = AIActivity.class;
        new AnnotationManager(this).initAnnotations();
        Log.d(TAG, "[" + clazz.getSimpleName() + "]onCreate supper(parser annotations) takes: " + (System.currentTimeMillis() - start) + "ms");
    }


    @Override
    public Context getContext() {
        return context;
    }


    @Override
    public Object getFindViewView() {
        return this;
    }


    @Override
    public void onClickCallbackSample(View view) {}
    @Override
    public void onLongClickCallbackSample(View view) {}
    @Override
    public void onItemClickCallbackSample(AdapterView<?> adapterView, View view, int i, long l) {}
    @Override
    public void onItemLongClickCallbackSample(AdapterView<?> adapterView, View view, int i, long l) {}








    private ProgressDialog loadingDialog;
    public synchronized ProgressDialog obtainLoadingDialog(String message){
        if(null == loadingDialog){
            loadingDialog = new ProgressDialog(context);
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.setMessage(message);
        return loadingDialog;
    }
    public synchronized ProgressDialog obtainLoadingDialog(){
        return obtainLoadingDialog("loading...");
    }


}
