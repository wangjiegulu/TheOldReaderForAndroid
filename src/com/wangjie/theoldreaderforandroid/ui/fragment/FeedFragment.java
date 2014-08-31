package com.wangjie.theoldreaderforandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.thread.ThreadPool;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidinject.annotation.annotations.base.AIItemClick;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.adapter.FeedAdapter;
import com.wangjie.theoldreaderforandroid.biz.FeedIdsBeforeRuntask;
import com.wangjie.theoldreaderforandroid.database.DbExecutor;
import com.wangjie.theoldreaderforandroid.entity.FeedItem;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import com.wangjie.theoldreaderforandroid.ui.activity.WebBrowserActivity;
import com.wangjie.theoldreaderforandroid.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/30/14.
 */
@AILayout(R.layout.feed_fragment)
public class FeedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String TAG = FeedFragment.class.getSimpleName();

    @AIView(R.id.feed_fragment_srl)
    private SwipeRefreshLayout feedSwipe;

    private DbExecutor<FeedItem> dbExecutor;

    @AIView(R.id.feed_fragment_lv)
    private ListView feedLv;
    private List<FeedItem> feeds = new ArrayList<>();
    private FeedAdapter adapter;

    private boolean isLoadingOlder;
    private boolean isNoOlder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbExecutor = new DbExecutor<>(context);

        feedSwipe.setColorScheme(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light
        );
        feedSwipe.setOnRefreshListener(this);

        adapter = new FeedAdapter(context, feeds);
        feedLv.setAdapter(adapter);

        feedLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                synchronized (((Object)this).getClass()) {
                    if (!isLoadingOlder && !isNoOlder) {
                        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) { // 当不滚动时
                            int lastPosition = feeds.size() - 1;
                            if (view.getLastVisiblePosition() >= lastPosition) { // 是否滚动到底部
                                isLoadingOlder = true;
                                refreshData(false);
                            }
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        refreshData(true);

    }

    private void refreshData(final boolean isFirstPage) {
        final long before = ABTextUtil.isEmpty(feeds) ?
                System.currentTimeMillis() / 1000 :
                feeds.get(feeds.size() - 1).getCrawlTimeMsec();

        ThreadPool.go(new FeedIdsBeforeRuntask(dbExecutor, before, 20){
            @Override
            public void onBefore() {
                super.onBefore();
                feedSwipe.setRefreshing(true);
            }

            @Override
            public void onResult(RuntaskResult<FeedItem> result) {
                super.onResult(result);
                feedSwipe.setRefreshing(false);
                isLoadingOlder = false;
                List<FeedItem> items = result.getObjs();
                Logger.d(TAG, "result: " + items);
                if(ABTextUtil.isEmpty(items)){
                    isNoOlder = true;
                    return;
                }
                if(isFirstPage){
                    feeds.clear();
                    isNoOlder = false;
                }
                feeds.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onRefresh() {
        refreshData(true);
    }

    @Override
    @AIItemClick(R.id.feed_fragment_lv)
    public void onItemClickCallbackSample(AdapterView<?> adapterView, View view, int i, long l) {

        FeedItem feedItem = (FeedItem) adapterView.getItemAtPosition(i);
        if(null == feedItem){
            Toast.makeText(context, "item error", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent iWebBrowser = new Intent(context, WebBrowserActivity.class);
        iWebBrowser.putExtra("url", feedItem.getUrlCanonical());
        startActivity(iWebBrowser);

    }


}
