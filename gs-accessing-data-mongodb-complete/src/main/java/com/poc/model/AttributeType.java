package com.poc.model;

public enum AttributeType {
    IntegerField("Integer"),
    DoubleFiled("Double");

    AttributeType(String discription) {
        this.discription = discription;
    }

    private String discription;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }






}
