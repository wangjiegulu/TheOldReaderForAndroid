package com.wangjie.theoldreaderforandroid.biz;

import android.text.Html;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.thread.Runtask;
import com.wangjie.androidbucket.utils.ABJsonUtil;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.theoldreaderforandroid.constant.ConstantUrl;
import com.wangjie.theoldreaderforandroid.database.DbExecutor;
import com.wangjie.theoldreaderforandroid.entity.FeedItem;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import com.wangjie.theoldreaderforandroid.net.NetWorkUtil;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import com.wangjie.theoldreaderforandroid.util.ResultParserUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class FeedIdsBeforeRuntask extends Runtask<Void, RuntaskResult<FeedItem>>{
    public static final String TAG = FeedIdsBeforeRuntask.class.getSimpleName();

    private DbExecutor<FeedItem> dbExecutor;
    private long before;
    private int limit;

    public FeedIdsBeforeRuntask(Object... objs) {
        super(objs);
        dbExecutor = (DbExecutor<FeedItem>) objs[0];
        before = (long) objs[1];
        limit = (int) objs[2];
    }

    @Override
    public RuntaskResult<FeedItem> runInBackground() {
        try {
            /**
             * 获取item的id
             */
            List<String> ids = new ArrayList<>();
            StringBuilder sb = NetWorkUtil.getStringFromUrl(ConstantUrl.URL_FEED_ITEMS_IDS
                            + "&s=user/-/state/com.google/reading-list"
                            + "&xt=user/-/state/com.google/read"
                            + "&nt=" + before
                            + "&n=" + limit
            );
            if(ABTextUtil.isEmpty(sb)){
                return RuntaskResult.generateFail(FeedItem.class);
            }
            Logger.d(TAG, "get item ids result: " + (null == sb ? "null" : sb.toString()));
            JSONObject resultJo = new JSONObject(sb.toString());
            JSONArray itemRefsJa = ABJsonUtil.getJSONArray(resultJo, "itemRefs");
            for(int i = 0, len = itemRefsJa.length(); i < len; i++){
                JSONObject jo = itemRefsJa.getJSONObject(i);
                ids.add(ABJsonUtil.getString(jo, "id"));
            }

            if(ABTextUtil.isEmpty(ids)){
                return RuntaskResult.generateSuccess(FeedItem.class);
            }

            /**
             * 获取item的内容
             */
            List<FeedItem> items = new ArrayList<>();
            StringBuilder paramSb = new StringBuilder();
            for(int i = 0, len = ids.size(); i < len; i++){
                paramSb.append("&i=").append(ids.get(i));
            }
            Logger.d(TAG, "get items content params: " + paramSb.toString());

            String url = ConstantUrl.URL_FEED_ITEMS_CONTENT + paramSb.toString();
            Logger.d(TAG, "get items content url: " + url);
            StringBuilder contentSb = NetWorkUtil.getStringFromUrl(url);
            Logger.d(TAG, "get item content result: " + (null == contentSb ? "null" : contentSb.toString()));
            JSONObject contentJo = new JSONObject(contentSb.toString());
            JSONArray itemJa = ABJsonUtil.getJSONArray(contentJo, "items");
            if(null != itemJa){
                for(int i = 0, len = itemJa.length(); i < len; i++){
                    JSONObject itemJo = itemJa.getJSONObject(i);
                    FeedItem item = ResultParserUtil.parserFeedItem(itemJo);
                    if(null == item){
                        continue;
                    }
                    try{
                        dbExecutor.executeSaveIfNotExist(item);
                    }catch(Exception ex){
                        Logger.e(TAG, ex);
                    }
                    items.add(item);
                }
            }

            return RuntaskResult.generateSuccess(FeedItem.class).setObjs(items);
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return RuntaskResult.generateFail(FeedItem.class);
    }

}
