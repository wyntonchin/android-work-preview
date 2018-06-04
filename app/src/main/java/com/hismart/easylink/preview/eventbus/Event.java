package com.hismart.easylink.preview.eventbus;


/**
 * @author qinwendong.
 * @date 2018/5/30
 * description EventBus Event基类只有继承该类才能通过重写BaseActivity的
 *             public void onEvent(Event event)方法监听到
 */


public class Event<T> {
    private int code;
    private T data;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
