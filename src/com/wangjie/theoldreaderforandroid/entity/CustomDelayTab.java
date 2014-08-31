package com.wangjie.theoldreaderforandroid.entity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/30/14.
 */
public class CustomDelayTab<T> {
    private Class<? extends T> clazz;
    private T obj;
    private String title;

    public Class<? extends T> getClazz() {
        return clazz;
    }

    public CustomDelayTab<T> setClazz(Class<? extends T> clazz) {
        this.clazz = clazz;
        return this;
    }

    public T getObj() {
        return obj;
    }

    public CustomDelayTab<T> setObj(T obj) {
        this.obj = obj;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CustomDelayTab<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "CustomDelayTab{" +
                "clazz=" + clazz +
                ", obj=" + obj +
                ", title='" + title + '\'' +
                '}';
    }
}
