package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindClickModel
 */

public class BindClickModel {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodModel mActionMethod;

    public BindClickModel setActionMethod(BindEventMethodModel method){
        this.mActionMethod = method;
        return this;
    }

    public BindClickModel setOnClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindClickModel setOnClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getOnClickResourceIds(){
        return nIds;
    }

    public String[] getOnClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodModel getActionMethod(){
        return mActionMethod;
    }

}
