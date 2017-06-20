package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindItemClickCode
 */

public class BindItemClickCode {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodCode       mActionMethod;

    public BindItemClickCode setActionMethod(BindEventMethodCode method){
        this.mActionMethod = method;
        return this;
    }

    public BindItemClickCode setLongClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindItemClickCode setLongClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getItemClickResourceIds(){
        return nIds;
    }

    public String[] getItemClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodCode getActionMethod(){
        return mActionMethod;
    }
}
