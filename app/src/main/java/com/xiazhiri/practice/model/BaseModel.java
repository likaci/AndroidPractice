package com.xiazhiri.practice.model;

/**
 * Created by liuwencai on 16/5/27.
 */
public class BaseModel<Data> {

    private int code;
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
