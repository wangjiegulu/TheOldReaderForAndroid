package com.wangjie.theoldreaderforandroid.database;

import android.content.Context;
import com.wangjie.androidinject.annotation.core.orm.AIDatabaseHelper;
import com.wangjie.androidinject.annotation.core.orm.AIDbExecutor;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
public class DbExecutor<T> extends AIDbExecutor<T>{
    private final static String TAG = DbExecutor.class.getSimpleName();
    public final static String DB_NAME = "theolderreader_db";
    public final static int VERSION = 1;

    public DbExecutor(Context context) {
        super(context);
    }

    @Override
    public AIDatabaseHelper obtainDbHelper() {
        return new DBHelper(context, DB_NAME, VERSION);
    }

}
