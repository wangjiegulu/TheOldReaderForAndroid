package com.wangjie.theoldreaderforandroid.util;

import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.utils.ABJsonUtil;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.theoldreaderforandroid.entity.RuntaskResult;
import org.json.JSONArray;
import org.json.JSONException;

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
}
