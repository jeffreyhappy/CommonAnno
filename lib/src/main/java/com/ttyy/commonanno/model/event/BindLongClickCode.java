package com.ttyy.commonanno.model.event;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindLongClickCode
 */

public class BindLongClickCode {

    protected String[]                  strIdNames;
    protected int[]                     nIds;
    protected BindEventMethodCode       mActionMethod;

    public BindLongClickCode setActionMethod(BindEventMethodCode method){
        this.mActionMethod = method;
        return this;
    }

    public BindLongClickCode setLongClickResourceIds(int[] ids){
        this.nIds = ids;
        return this;
    }

    public BindLongClickCode setLongClickResourceIdNames(String[] idNames){
        this.strIdNames = idNames;
        return this;
    }

    public int[] getLongClickResourceIds(){
        return nIds;
    }

    public String[] getLongClickResourceIdNames(){
        return strIdNames;
    }

    public BindEventMethodCode getActionMethod(){
        return mActionMethod;
    }
}
