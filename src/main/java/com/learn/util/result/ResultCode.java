package com.learn.util.result;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
public enum ResultCode {

    SUCCESS(1, "成功"),
    FAILURE(0, "失败"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    // @Getter
    private int code;
    // @Getter
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


    //public static String getMessage(String name) {
    //    for (ResultCode item : ResultCode.values()) {
    //        if (item.name().equals(name)) {
    //            return item.message;
    //        }
    //    }
    //    return name;
    //}
    //
    //public static Integer getCode(String name) {
    //    for (ResultCode item : ResultCode.values()) {
    //        if (item.name().equals(name)) {
    //            return item.code;
    //        }
    //    }
    //    return null;
    //}

    //@Override
    //public String toString() {
    //    return this.name();
    //}

}
