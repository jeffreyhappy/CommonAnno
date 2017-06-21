package com.ttyy.commonanno.model.event;

import com.ttyy.commonanno.__Symbols;

import java.util.LinkedList;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindEventMethodModel
 */

public class BindEventMethodModel {

    protected String                    strMethodName;
    protected String                    strReturnType;
    protected LinkedList<Parameter>     mParameters;

    public static class Parameter{

        public String strParameterName;
        public String strParameterType;


        public String getDefaultValue(){
            if (strParameterType.equals("int")
                    || strParameterType.equals("float")
                    || strParameterType.equals("double")
                    || strParameterType.equals("long")
                    || strParameterType.equals(Integer.class.getCanonicalName())
                    || strParameterType.equals(Float.class.getCanonicalName())
                    || strParameterType.equals(Double.class.getCanonicalName())
                    || strParameterType.equals(Long.class.getCanonicalName())) {
                return "-1";
            }else if(Boolean.class.getCanonicalName().equals(strParameterType)
                    || "boolean".equals(strParameterType)){
                return "false";
            }else {
                return "null";
            }
        }

    }

    public BindEventMethodModel(){
        mParameters = new LinkedList<>();
    }

    public BindEventMethodModel setMethodName(String name){
        this.strMethodName = name;
        return this;
    }

    public BindEventMethodModel setReturnType(String returnType){
        this.strReturnType = returnType;
        return this;
    }

    public BindEventMethodModel addParameter(Parameter param){
        this.mParameters.add(param);
        return this;
    }

    public String toUse(LinkedList<Parameter> sourceParams){
        StringBuilder sb = new StringBuilder();
        sb.append(__Symbols.OBJ_TARGET)
                .append(__Symbols.DOT_DIVIDER)
                .append(strMethodName)
                .append(__Symbols.PARAM_START);

        if(sourceParams != null
                && sourceParams.size() > 0){
            for(int i = 0 ; i < mParameters.size(); i++){
                Parameter selfParam = mParameters.get(i);
                String paramValue = selfParam.getDefaultValue();

                for(Parameter sourceParam : sourceParams){
                    if(selfParam.strParameterType.equals(sourceParam.strParameterType)){
                        paramValue = sourceParam.strParameterName;
                        break;
                    }
                }

                sb.append(paramValue);
                if(i < mParameters.size() - 1){
                    sb.append(__Symbols.PARAM_DIVIDER);
                }
            }
        }

        sb.append(__Symbols.PARAM_END).append(__Symbols.LINE_END);

        return sb.toString();
    }
}
