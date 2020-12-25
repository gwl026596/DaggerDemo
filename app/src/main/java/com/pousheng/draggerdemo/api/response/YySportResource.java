package com.pousheng.draggerdemo.api.response;

/**
 * 多个不同字段的返回响应重新定义该类
 * @param <T>
 */
public class YySportResource<T> {
    private String code;
    private T result;
    private String message;



    public YySportResource() {
    }

    public YySportResource(String code, T result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "MeetResource{" +
                "code=" + code +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
