package com.wangjie.theoldreaderforandroid.entity;

import android.text.Spanned;
import com.wangjie.androidinject.annotation.annotations.orm.AIColumn;
import com.wangjie.androidinject.annotation.annotations.orm.AIPrimaryKey;
import com.wangjie.androidinject.annotation.annotations.orm.AITable;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
@AITable("feed_item")
public class FeedItem implements Serializable{

    @AIColumn @AIPrimaryKey(insertable = true) private String id;
    @AIColumn("user_id") private String userId; // 用户id
    @AIColumn("timestamp_usec") private long timestampUsec; // millis
    @AIColumn("crawl_time_msec") private long crawlTimeMsec; // 秒，需要*1000
    private String[] categories; // 所属分类
    @AIColumn private String title; // 标题
    @AIColumn private long published; // 文章发表时间，秒，需要*1000
    @AIColumn private long updated; // 文章更新时间，秒，需要*1000

    @AIColumn("url_canonical") private String urlCanonical; // 文章url
    @AIColumn("url_alternate") private String urlAlternate; // 备用的url
    @AIColumn("summary_content") private String summaryContent; // 内容概要
    private Spanned summaryContentHtml; // 被解析后的内容html
    @AIColumn private String author; // 作者
    @AIColumn("origin_stream_id") private String originStreamId; // feed所属网站的id

    private FeedOrigin origin; // feed所属网站

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestampUsec() {
        return timestampUsec;
    }

    public void setTimestampUsec(long timestampUsec) {
        this.timestampUsec = timestampUsec;
    }

    public long getCrawlTimeMsec() {
        return crawlTimeMsec;
    }

    public void setCrawlTimeMsec(long crawlTimeMsec) {
        this.crawlTimeMsec = crawlTimeMsec;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getUrlCanonical() {
        return urlCanonical;
    }

    public void setUrlCanonical(String urlCanonical) {
        this.urlCanonical = urlCanonical;
    }

    public String getUrlAlternate() {
        return urlAlternate;
    }

    public void setUrlAlternate(String urlAlternate) {
        this.urlAlternate = urlAlternate;
    }

    public String getSummaryContent() {
        return summaryContent;
    }

    public void setSummaryContent(String summaryContent) {
        this.summaryContent = summaryContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FeedOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(FeedOrigin origin) {
        this.origin = origin;
    }

    public String getOriginStreamId() {
        return originStreamId;
    }

    public void setOriginStreamId(String originStreamId) {
        this.originStreamId = originStreamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Spanned getSummaryContentHtml() {
        return summaryContentHtml;
    }

    public void setSummaryContentHtml(Spanned summaryContentHtml) {
        this.summaryContentHtml = summaryContentHtml;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", timestampUsec=" + timestampUsec +
                ", crawlTimeMsec=" + crawlTimeMsec +
                ", categories=" + Arrays.toString(categories) +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", updated=" + updated +
                ", urlCanonical='" + urlCanonical + '\'' +
                ", urlAlternate='" + urlAlternate + '\'' +
                ", summaryContent='" + summaryContent + '\'' +
                ", author='" + author + '\'' +
                ", originStreamId='" + originStreamId + '\'' +
                ", origin=" + origin +
                '}';
    }
}
