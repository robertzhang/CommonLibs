package com.robertzhang.commonlibs.eventbus;

/**
 * Created by robertzhang on 16/12/14.
 * email: robertzhangsh@gmail.com
 * 所有的 Event 对象都应该基础该类
 */

public class EventMessage<T> {

    /**
     * reserved data
     */
    private T data;

    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    public EventMessage(int eventCode) {
        this(eventCode, null);
    }

    public EventMessage(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    /**
     * get event code
     *
     * @return
     */
    public int getEventCode() {
        return this.eventCode;
    }

    /**
     * get event reserved data
     *
     * @return
     */
    public T getData() {
        return this.data;
    }
}
