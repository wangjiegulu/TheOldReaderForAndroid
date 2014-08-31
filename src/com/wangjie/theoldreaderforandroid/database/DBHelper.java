package com.wangjie.theoldreaderforandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.wangjie.androidinject.annotation.core.orm.AIDatabaseHelper;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class DBHelper extends AIDatabaseHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, int version) {
        super(context, name, version);
    }

    /*************************************************/
    /**
     * feed表，（from VERSION 1）
     */
    public static final String CREATE_TABLE_FEED_ITEM = "create table if not exists feed_item(" +
            "id varchar(20) primary key UNIQUE, " +
            "user_id varchar(20), " +
            "timestamp_usec long, " +
            "crawl_time_msec long, " +
            "title varchar(100), " +
            "published long, " +
            "updated long, " +
            "url_canonical varchar(255), " +
            "url_alternate varchar(255), " +
            "summary_content varchar(255), " +
            "author varchar(20), " +
            "origin_stream_id varchar(20) " +
            ")";

    /*************************************************/

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);

        db.execSQL(CREATE_TABLE_FEED_ITEM);



    }



}
