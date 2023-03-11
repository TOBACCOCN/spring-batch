package com.example.springbatch;

import lombok.Getter;

/**
 * 返回结果枚举类
 *
 * @author zhangyonghong
 * @date 2019.12.15
 */
@Getter
public enum ResultEnum {

    SUCCESS("2000", "Success"),
    NO_JOB_ID_FOUND("4001", "No job id found"),

    FAILED("5000", "Failed");

    private final String code;
    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
