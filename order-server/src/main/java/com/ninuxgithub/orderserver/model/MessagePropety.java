package com.ninuxgithub.orderserver.model;

import java.io.Serializable;

/**
 * mq message property
 */
public class MessagePropety implements Serializable {

    private static final long serialVersionUID = 2271937896081805672L;

    /**
     * message uuid
     */
    private String uuid;

    /**
     * mq data
     */
    private Object data;

    /**
     * request type
     */
    private String requestType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
