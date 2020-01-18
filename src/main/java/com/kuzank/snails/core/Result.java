package com.kuzank.snails.core;

/**
 * 返回对象
 *
 * @author aires
 */
public class Result<T> {
    private boolean success = true;
    private int status = 0;
    private T data;
    private String msg;

    public static Result ofsuccess(Object obj) {
        return new Result<Object>() {{
            setData(obj);
        }};
    }

    public static Result oflost(String _msg, int status) {
        return new Result<Object>() {{
            setMsg(_msg);
            setStatus(status);
        }};
    }

    public static Result oflost(int status) {
        return new Result<Object>() {{
            setStatus(status);
            String msg = Config.getMsg(status);
            setMsg(msg);
        }};
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        this.setSuccess(this.status == 0);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
