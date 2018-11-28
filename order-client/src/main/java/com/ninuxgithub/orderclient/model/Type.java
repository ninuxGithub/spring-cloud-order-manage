package com.ninuxgithub.orderclient.model;

public enum Type {
    StarType("明星产品"), fit("配件");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
