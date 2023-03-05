package com.example.springbatch;

import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 栈异常信息输出工具类
 *
 * @author zhangyonghong
 * @date 2019.6.13
 */
public class ErrorPrintUtil {

    private ErrorPrintUtil() {
    }

    public static void printErrorMsg(Logger logger, Throwable e) {
        printErrorMsg(logger, e, null);
    }

    public static void printErrorMsg(Logger logger, Throwable e, String prefix) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter, true));
        if (prefix != null) {
            logger.error("{}{}", prefix, stringWriter);
        } else {
            logger.error("{}", stringWriter);
        }
    }

}
