package com.wangjie.theoldreaderforandroid.constant;

/**
 * https://github.com/theoldreader/api
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class ConstantUrl {

    public static final String BASE_URL = "https://theoldreader.com";
    public static final String BASE_URL_API = BASE_URL + "/reader/api/0";

    /**
     * 登录
     */
    public static final String URL_CLIENT_LOGIN = BASE_URL_API + "/accounts/ClientLogin";

    /**
     * 获取登录用户信息
     */
    public static final String URL_USER_INFO = BASE_URL_API + "/user-info?output=json";

    /**
     * 获取feed ids
     */
    public static final String URL_FEED_ITEMS_IDS = BASE_URL_API + "/stream/items/ids?output=json";
    /**
     * 获取feed内容
     */
    public static final String URL_FEED_ITEMS_CONTENT = BASE_URL_API + "/stream/items/contents?output=json";





}
