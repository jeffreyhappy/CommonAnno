package com.ttyy.commonanno.model;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindLayoutModel
 */

public class BindLayoutModel {

    protected String                strIdName;
    protected int                   nId;
    protected boolean               isContentView;
    protected String strFieldType;
    protected String                strFieldName;

    public BindLayoutModel(){
        strIdName = null;
        nId = -1;
        isContentView = false;
    }

    public BindLayoutModel setIsContentView(boolean value){
        this.isContentView = value;
        return this;
    }

    public BindLayoutModel setResourceId(int id){
        this.nId = id;
        return this;
    }

    public BindLayoutModel setResourceIdName(String name){
        this.strIdName = name;
        return this;
    }

    public BindLayoutModel setFieldType(String viewType){
        this.strFieldType = viewType;
        return this;
    }

    public String getFieldType(){
        return strFieldType;
    }


    public BindLayoutModel setFieldName(String name){
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
