package com.wangjie.theoldreaderforandroid.entity;

import java.io.Serializable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class LoginInfo implements Serializable{
    private String sid;
    private String lsid;
    private String auth;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLsid() {
        return lsid;
    }

    public void setLsid(String lsid) {
        this.lsid = lsid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
