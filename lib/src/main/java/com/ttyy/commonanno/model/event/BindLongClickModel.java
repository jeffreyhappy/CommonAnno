package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindLongClickModel
 */

public class BindLongClickModel {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodModel mActionMethod;

    public BindLongClickModel setActionMethod(BindEventMethodModel method){
        this.mActionMethod = method;
        return this;
    }

    public BindLongClickModel setLongClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindLongClickModel setLongClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getLongClickResourceIds(){
        return nIds;
    }

    public String[] getLongClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodModel getActionMethod(){
        return mActionMethod;
    }
}
