package com.wangjie.theoldreaderforandroid.ui.base;

import android.app.ProgressDialog;
import com.wangjie.androidinject.annotation.present.AIActivity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class BaseActivity extends AIActivity{






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
