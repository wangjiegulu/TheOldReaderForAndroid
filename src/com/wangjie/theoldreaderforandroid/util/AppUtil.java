package com.wangjie.theoldreaderforandroid.util;

import android.content.Context;
import android.content.Intent;
import com.wangjie.androidbucket.utils.ABPrefsUtil;
import com.wangjie.theoldreaderforandroid.ui.activity.LoginActivity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class AppUtil {
    public static final String TAG = AppUtil.class.getSimpleName();

    /**
     * 退出登录
     * @param context
     */
    public static void loginOut(Context context){
        Intent iLoginOut = new Intent(context, LoginActivity.class);
        iLoginOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ABPrefsUtil.getInstance().editor.clear().commit();
        context.startActivity(iLoginOut);
    }

}
