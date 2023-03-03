package com.wbb.generator.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author:William
 * @Date:2023/2/26 16:08
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {

    }

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "success";

    public static final int PARAMS_ERROR_CODE = 400;
    public static final int SERVICE_ERROR_CODE = 500;


    public static Result successResult(Object o){
        Result result = new Result();
        result.code = SUCCESS_CODE;
        result.msg = SUCCESS_MSG;
        result.data = o;
        return result;
    }

    public static Result errorResult(int code,String msg){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        return result;
    }

}
