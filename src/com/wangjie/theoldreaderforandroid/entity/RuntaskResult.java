package com.wangjie.theoldreaderforandroid.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/24/14.
 */
public class RuntaskResult<T> implements Serializable{
    private int resultCode;
    public static final int RESULT_CODE_FAIL = -1;
    public static final int RESULT_CODE_SUCCESS = 0;

    private String errorMessage;
    private T obj;
    private List<T> objs;

    public static <T> RuntaskResult<T> generateFail(Class<T> clazz){
        return new RuntaskResult<T>().setResultCode(RESULT_CODE_FAIL).setErrorMessage("Operation Failure");
    }
    public static <T> RuntaskResult<T> generateSuccess(Class<T> clazz){
        return new RuntaskResult<T>().setResultCode(RESULT_CODE_SUCCESS);
    }
    public static RuntaskResult generateFail(){
        return new RuntaskResult().setResultCode(RESULT_CODE_FAIL).setErrorMessage("Operation Failure");
    }
    public static RuntaskResult generateSuccess(){
        return new RuntaskResult().setResultCode(RESULT_CODE_SUCCESS);
    }


    public int getResultCode() {
        return resultCode;
    }

    public RuntaskResult<T> setResultCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RuntaskResult<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public T getObj() {
        return obj;
    }

    public RuntaskResult<T> setObj(T obj) {
        this.obj = obj;
        return this;
    }

    public List<T> getObjs() {
        return objs;
    }

    public RuntaskResult<T> setObjs(List<T> objs) {
        this.objs = objs;
        return this;
    }
}
