package com.example.springbatch;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回结果类
 *
 * @author zhangyonghong
 * @date 2019.12.15
 */
public class Result extends JSONObject {

    private Result() {
    }

    private Result(ResultEnum resultEnum) {
        this.put("code", resultEnum.getCode());
        this.put("message", resultEnum.getMessage());
    }

    private <T> Result(ResultEnum resultEnum, T result) {
        this.put("code", resultEnum.getCode());
        this.put("message", resultEnum.getMessage());
    }

    public static Result getInstance(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

    public static <T> Result getInstance(ResultEnum resultEnum, T t) {
        return  new Result(resultEnum, t);
    }


}
