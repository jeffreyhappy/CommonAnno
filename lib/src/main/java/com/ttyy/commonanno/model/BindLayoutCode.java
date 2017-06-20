package com.ttyy.commonanno.model;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindLayoutCode
 */

public class BindLayoutCode {

    protected String                strIdName;
    protected int                   nId;
    protected boolean               isContentView;
    protected String strFieldType;
    protected String                strFieldName;

    public BindLayoutCode(){
        strIdName = null;
        nId = -1;
        isContentView = false;
    }

    public BindLayoutCode setIsContentView(boolean value){
        this.isContentView = value;
        return this;
    }

    public BindLayoutCode setResourceId(int id){
        this.nId = id;
        return this;
    }

    public BindLayoutCode setResourceIdName(String name){
        this.strIdName = name;
        return this;
    }

    public BindLayoutCode setFieldType(String viewType){
        this.strFieldType = viewType;
        return this;
    }

    public String getFieldType(){
        return strFieldType;
    }


    public BindLayoutCode setFieldName(String name){
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
