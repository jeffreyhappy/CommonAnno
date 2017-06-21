package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindItemClickModel
 */

public class BindItemClickModel {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodModel mActionMethod;

    public BindItemClickModel setActionMethod(BindEventMethodModel method){
        this.mActionMethod = method;
        return this;
    }

    public BindItemClickModel setLongClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindItemClickModel setLongClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getItemClickResourceIds(){
        return nIds;
    }

    public String[] getItemClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodModel getActionMethod(){
        return mActionMethod;
    }
}
