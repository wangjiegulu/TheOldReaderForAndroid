package com.wangjie.theoldreaderforandroid.util;

import android.text.Html;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.utils.ABJsonUtil;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.theoldreaderforandroid.entity.FeedItem;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class ResultParserUtil {
    public static final String TAG = ResultParserUtil.class.getSimpleName();

    public static <T> RuntaskResult<T> parserIfError(JSONArray errors, RuntaskResult<T> result){
        if(ABJsonUtil.isEmpty(errors)){

        }
        StringBuilder errorMsgSb = new StringBuilder();
        int len = errors.length();
        for(int i = 0; i < len; i++){
            try {
                errorMsgSb.append(errors.getString(i)).append(";");
            } catch (JSONException e) {
                Logger.e(TAG, e);
            }
        }
        if(!ABTextUtil.isEmpty(errorMsgSb)){
            result.setErrorMessage(errorMsgSb.toString());
        }
        return result;
    }


    public static FeedItem parserFeedItem(JSONObject itemJo) throws Exception{
        String id = ABJsonUtil.getString(itemJo, "id");
        if(ABTextUtil.isEmpty(id)){
            return null;
        }
        FeedItem item = new FeedItem();
        item.setId(id.substring(id.lastIndexOf("/")));
        item.setUserId(PrefsKey.getCachedUserId());
        item.setCrawlTimeMsec(ABJsonUtil.getLong(itemJo, "crawlTimeMsec"));
        item.setTimestampUsec(ABJsonUtil.getLong(itemJo, "timestampUsec"));
        item.setTitle(ABJsonUtil.getString(itemJo, "title"));
        item.setPublished(ABJsonUtil.getLong(itemJo, "published"));
        item.setUpdated(ABJsonUtil.getLong(itemJo, "updated"));

        JSONArray canonicalJa = ABJsonUtil.getJSONArray(itemJo, "canonical");
        if(!ABJsonUtil.isEmpty(canonicalJa)){
            item.setUrlCanonical(ABJsonUtil.getString(canonicalJa.getJSONObject(0), "href"));
        }

        JSONArray alternateJa = ABJsonUtil.getJSONArray(itemJo, "alternate");
        if(!ABJsonUtil.isEmpty(alternateJa)){
            item.setUrlAlternate(ABJsonUtil.getString(alternateJa.getJSONObject(0), "href"));
        }

        JSONObject summaryJo = ABJsonUtil.getJSONObject(itemJo, "summary");
        if(null != summaryJo){
            String summaryContent = ABJsonUtil.getString(summaryJo, "content");
            summaryContent = ABTextUtil.isEmpty(summaryContent) || summaryContent.length() < 51 ? summaryContent : summaryContent.substring(0, 50);
            item.setSummaryContent(summaryContent);
            item.setSummaryContentHtml(Html.fromHtml(summaryContent));
        }

        item.setAuthor(ABJsonUtil.getString(itemJo, "author"));

        JSONObject originJo = ABJsonUtil.getJSONObject(itemJo, "origin");
        if(null != originJo){
            item.setOriginStreamId(ABJsonUtil.getString(originJo, "streamId"));
        }
        return item;
    }






}
