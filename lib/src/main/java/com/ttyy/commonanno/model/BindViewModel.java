package com.ttyy.commonanno.model;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindViewModel
 */

public class BindViewModel {

    protected String                strIdName;
    protected int                   nId;
    protected String strFieldType;
    protected String                strFieldName;

    public BindViewModel setResourceIdName(String name){
        this.strIdName = name;
        return this;
    }

    public BindViewModel setResourceId(int id){
        this.nId = id;
        return this;
    }

    public BindViewModel setFieldType(String fieldType){
        this.strFieldType = fieldType;
        return this;
    }

    public String getFieldType(){
        return strFieldType;
    }

    public BindViewModel setFieldName(String name){
        this.strFieldName = name;
        return this;
    }

    public String getFieldName(){
        return this.strFieldName;
    }

    public String getResourceIdName(){
        return this.strIdName;
    }

    public int getResourceId(){
        return this.nId;
    }

}
