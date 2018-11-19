package com.ninuxgithub.orderserver.model;

import java.io.Serializable;

public class MessagePropety implements Serializable {

    private static final long serialVersionUID = 2271937896081805672L;

    private String uuid;

    private Object data;

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
}
