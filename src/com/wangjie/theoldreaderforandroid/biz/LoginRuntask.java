package com.wangjie.theoldreaderforandroid.biz;

import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.thread.Runtask;
import com.wangjie.androidbucket.utils.ABJsonUtil;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidinject.annotation.core.net.AINetWork;
import com.wangjie.theoldreaderforandroid.constant.ConstantUrl;
import com.wangjie.theoldreaderforandroid.entity.LoginInfo;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class LoginRuntask extends Runtask<Void, RuntaskResult<LoginInfo>>{
    public static final String TAG = LoginRuntask.class.getSimpleName();
    private String username;
    private String password;

    public LoginRuntask(Object... objs) {
        super(objs);
        username = (String) objs[0];
        password = (String) objs[1];
    }

    @Override
    public RuntaskResult<LoginInfo> runInBackground() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("client", "TheOlderReaderForAndroid");
        params.put("accountType", "HOSTED");
        params.put("service", "reader");
        params.put("Email", username);
        params.put("Passwd", password);
        params.put("output", "json");
        try {
            StringBuilder sb = AINetWork.postStringFromUrl(null, ConstantUrl.URL_CLIENT_LOGIN, params);
            Logger.d(TAG, null == sb ? "null" : sb.toString());
            if(ABTextUtil.isEmpty(sb)){
                return RuntaskResult.generateFail(LoginInfo.class);
            }
            JSONObject resultJo = new JSONObject(sb.toString());
            String auth = ABJsonUtil.getString(resultJo, "Auth");

            if(ABTextUtil.isEmpty(auth) || auth.equals("none")){
                return RuntaskResult.generateFail(LoginInfo.class);
            }

            LoginInfo li = new LoginInfo();
            li.setAuth(auth);
            li.setLsid(ABJsonUtil.getString(resultJo, "LSID"));
            li.setSid(ABJsonUtil.getString(resultJo, "SID"));

            return RuntaskResult.generateSuccess(LoginInfo.class).setObj(li);

        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return RuntaskResult.generateFail(LoginInfo.class);
    }
}
