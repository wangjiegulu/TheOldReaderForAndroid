package com.wangjie.theoldreaderforandroid.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wangjie.androidbucket.utils.ABViewUtil;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.entity.FeedItem;

import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class FeedAdapter extends BaseAdapter{
    public static final String TAG = FeedAdapter.class.getSimpleName();
    private Context context;
    private List<FeedItem> list;

    public FeedAdapter(Context context, List<FeedItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.feed_fragment_lv_item, null);
        }

        TextView titleTv = ABViewUtil.obtainView(convertView, R.id.feed_fragment_lv_item_title_tv);
        TextView contentTv = ABViewUtil.obtainView(convertView, R.id.feed_fragment_lv_item_content_tv);

        FeedItem feedItem = (FeedItem) getItem(position);
        if(null != feedItem){
            titleTv.setText(feedItem.getTitle());
            contentTv.setText(feedItem.getSummaryContentHtml());
        }
        return convertView;
    }


}
