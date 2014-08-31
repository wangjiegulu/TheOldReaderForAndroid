package com.wangjie.theoldreaderforandroid.entity;

import java.io.Serializable;

/**
 * feed所属网站信息
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class FeedOrigin implements Serializable{

    private String streamId; // 所属网站的id
    private String title; // 所属网站的名字
    private String htmlUrl; // 所属网站的url

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Override
    public String toString() {
        return "FeedOrigin{" +
                "streamId='" + streamId + '\'' +
                ", title='" + title + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }
}
