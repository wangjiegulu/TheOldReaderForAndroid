package com.wangjie.theoldreaderforandroid.biz;

import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.thread.Runtask;
import com.wangjie.androidbucket.utils.ABJsonUtil;
import com.wangjie.androidbucket.utils.ABPrefsUtil;
import com.wangjie.androidinject.annotation.core.net.AINetWork;
import com.wangjie.theoldreaderforandroid.constant.ConstantUrl;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import com.wangjie.theoldreaderforandroid.net.NetWorkUtil;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class GetUserInfoRuntask extends Runtask<Void, RuntaskResult>{
    public static final String TAG = GetUserInfoRuntask.class.getSimpleName();

    @Override
    public RuntaskResult runInBackground() {
        try {
            StringBuilder sb = NetWorkUtil.getStringFromUrl(ConstantUrl.URL_USER_INFO);

            Logger.d(TAG, "result: " + (null == sb ? "null" : sb.toString()));
            JSONObject resultJo = new JSONObject(sb.toString());

            ABPrefsUtil prefsUtil = ABPrefsUtil.getInstance();
            prefsUtil.putString(PrefsKey.KEY_USER_ID, ABJsonUtil.getString(resultJo, "userId"))
                    .putString(PrefsKey.KEY_USERNAME, ABJsonUtil.getString(resultJo, "userName"))
                    .putString(PrefsKey.KEY_USER_PROFILE_ID, ABJsonUtil.getString(resultJo, "userProfileId"))
                    .putString(PrefsKey.KEY_USER_EMAIL, ABJsonUtil.getString(resultJo, "userEmail"))
                    .putBoolean(PrefsKey.KEY_USER_IS_BLOGGER, ABJsonUtil.getBoolean(resultJo, "isBloggerUser"))
                    .putLong(PrefsKey.KEY_USER_SIGN_UP_TIME_SEC, ABJsonUtil.getLong(resultJo, "signupTimeSec"))
                    .putBoolean(PrefsKey.KEY_USER_IS_MULTI_LOGIN_ENABLED, ABJsonUtil.getBoolean(resultJo, "isMultiLoginEnabled"))
                    .putBoolean(PrefsKey.KEY_USER_IS_PREMIUM, ABJsonUtil.getBoolean(resultJo, "isPremium"))
                    .commit();

            return RuntaskResult.generateSuccess();
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return RuntaskResult.generateFail();
    }
}
