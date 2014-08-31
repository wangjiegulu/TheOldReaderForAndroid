package com.wangjie.theoldreaderforandroid.prefs;

import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.utils.ABPrefsUtil;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class PrefsKey {
    public static final String TAG = PrefsKey.class.getSimpleName();

    public static final String KEY_USERNAME = "username";
    public static final String KEY_AUTH = "user_auth";
    public static final String KEY_SID = "user_sid";
    public static final String KEY_LSID = "user_lsid";

    public static final String KEY_USER_ID = "user_id";
//    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_PROFILE_ID = "user_profile_id";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_IS_BLOGGER = "user_is_blogger";
    public static final String KEY_USER_SIGN_UP_TIME_SEC = "user_sign_up_time_sec";
    public static final String KEY_USER_IS_MULTI_LOGIN_ENABLED = "is_multi_login_enabled";
    public static final String KEY_USER_IS_PREMIUM = "user_is_premium";



    /**
     * 由于要在每个请求中添加auth，所以缓存在内存中
     */
    private static String auth;
    public synchronized static String getCachedAuth(){
        if(null == auth){
            auth = ABPrefsUtil.getInstance().getString(KEY_AUTH);
        }
        Logger.d(TAG, "getCachedAuth: " + auth);
        return auth;
    }
    private static String userId;
    public synchronized static String getCachedUserId(){
        if(null == userId){
            userId = ABPrefsUtil.getInstance().getString(KEY_USER_ID);
        }
        return userId;
    }




}
