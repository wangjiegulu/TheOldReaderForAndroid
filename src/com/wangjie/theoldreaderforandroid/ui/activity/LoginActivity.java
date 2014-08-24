package com.wangjie.theoldreaderforandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.wangjie.androidbucket.thread.ThreadPool;
import com.wangjie.androidbucket.utils.ABPrefsUtil;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidinject.annotation.annotations.base.AIClick;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.biz.LoginRuntask;
import com.wangjie.theoldreaderforandroid.entity.LoginInfo;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import com.wangjie.theoldreaderforandroid.ui.base.BaseActivity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
@AILayout(R.layout.login)
public class LoginActivity extends BaseActivity{
    public static final String TAG = LoginActivity.class.getSimpleName();
    @AIView(R.id.login_username_et)
    private EditText usernameEt;
    @AIView(R.id.login_password_et)
    private EditText passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!ABTextUtil.isEmpty(ABPrefsUtil.getInstance().getString(PrefsKey.KEY_AUTH))){
            startActivity(new Intent(context, MainActivity.class));
            finish();
        }


    }


    @Override
    @AIClick({R.id.login_submit_btn})
    public void onClickCallbackSample(View view) {
        switch(view.getId()){
            case R.id.login_submit_btn:
                if(!validateForm()){
                    return;
                }
                executeLogin();
                break;
        }

    }

    private boolean validateForm() {
        if(ABTextUtil.isEmpty(usernameEt.getText())){
            Toast.makeText(context, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ABTextUtil.isEmpty(passwordEt.getText())){
            Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void executeLogin(){
        final String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        obtainLoadingDialog("login...").show();
        ThreadPool.go(new LoginRuntask(username, password){
            @Override
            public void onResult(RuntaskResult<LoginInfo> result) {
                obtainLoadingDialog().cancel();
                if(result.getResultCode() < 0){
                    Toast.makeText(context, result.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginInfo loginInfo = result.getObj();
                ABPrefsUtil.getInstance().putString(PrefsKey.KEY_AUTH, loginInfo.getAuth())
                        .putString(PrefsKey.KEY_LSID, loginInfo.getLsid())
                        .putString(PrefsKey.KEY_SID, loginInfo.getSid())
                        .putString(PrefsKey.KEY_USERNAME, username)
                        .commit();

                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });
    }


}
