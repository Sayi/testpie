package com.deepoove.testpie.target;

public class Result<R> {

    private boolean success;
    private String msg;
    private R data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [success=" + success + ", msg=" + msg + ", data=" + data + "]";
    }
    

}
