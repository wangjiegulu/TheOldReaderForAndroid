package com.wangjie.theoldreaderforandroid.net;

import com.wangjie.androidinject.annotation.core.net.AINetWork;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class NetWorkUtil {

    public static StringBuilder postStringFromUrl(String url, Map<String, String> params) throws Exception{
        HttpClient httpClient = AINetWork.getSSLHttpClient(20000, 20000);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", "GoogleLogin auth=" + PrefsKey.getCachedAuth());

        List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
        if(null != params){
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData, HTTP.UTF_8);
            httpPost.setEntity(entity);
        }

        return AINetWork.obtainStringFromInputStream(httpClient.execute(httpPost).getEntity().getContent());
    }

    public static StringBuilder getStringFromUrl(String url) throws Exception{
        HttpClient httpClient = AINetWork.getSSLHttpClient(20000, 20000);
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Authorization", "GoogleLogin auth=" + PrefsKey.getCachedAuth());
        return AINetWork.obtainStringFromInputStream(httpClient.execute(httpGet).getEntity().getContent());
    }



}
