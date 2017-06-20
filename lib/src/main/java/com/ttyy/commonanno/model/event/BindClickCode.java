package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindClickCode
 */

public class BindClickCode {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodCode       mActionMethod;

    public BindClickCode setActionMethod(BindEventMethodCode method){
        this.mActionMethod = method;
        return this;
    }

    public BindClickCode setOnClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindClickCode setOnClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getOnClickResourceIds(){
        return nIds;
    }

    public String[] getOnClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodCode getActionMethod(){
        return mActionMethod;
    }

}
