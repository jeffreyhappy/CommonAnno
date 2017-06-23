package com.ttyy.commonanno.model.route;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: BindExtraModel
 */

public class BindExtraModel {

    private String strExtraKey;
    private String strFieldName;
    private String strFieldType;

    public BindExtraModel(){

    }

    public BindExtraModel setExtraKey(String key){
        this.strExtraKey = key;
        return this;
    }

    public BindExtraModel setFieldName(String name){
        this.strFieldName = name;
        return this;
    }

    public BindExtraModel setFieldType(String type){
        this.strFieldType = type;
        return this;
    }

    public String getExtraKey(){
        return this.strExtraKey;
    }

    public String getFieldName(){
        return this.strFieldName;
    }

    public String getFieldType(){
        return this.strFieldType;
    }

}
