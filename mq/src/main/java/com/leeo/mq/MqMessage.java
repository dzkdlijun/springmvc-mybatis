package com.leeo.mq;

/**
 * @author lijun
 * @since 2017/7/25 18:09
 */
public class MqMessage {
    private String action;
    private Object data;

    public MqMessage() {
    }

    public MqMessage(String action, Object data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
