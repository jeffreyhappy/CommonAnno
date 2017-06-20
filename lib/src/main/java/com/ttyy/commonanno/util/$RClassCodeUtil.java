package com.ttyy.commonanno.util;

import com.ttyy.commonanno.__Symbols;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: $RClassCodeUtil
 */

public class $RClassCodeUtil {

    private $RClassCodeUtil(){

    }

    public static final String setResourceLayoutClass(){
        StringBuilder sb = new StringBuilder();

//        Class layoutClass = null;
//        try {
//            layoutClass = Class.forName(R.class.getCanonicalName()+"$layout");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        sb.append("try{\n")
                .append(__Symbols.OBJ_RCLASS_LAYOUT)
                .append(__Symbols.MATH_EQUAL)
                .append("Class.forName(RClass.getCanonicalName()+\"$layout\");")
                .append(__Symbols.CODE_END)
                .append(" catch (ClassNotFoundException e) {\n")
                .append("e.printStackTrace();\n}\n");

        return sb.toString();
    }

    public static final String setResouceIdClass(){
        StringBuilder sb = new StringBuilder();

//        Class idClass = null;
//        try {
//            idClass = Class.forName(R.class.getCanonicalName()+"$id");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        sb.append("try{\n")
                .append(__Symbols.OBJ_RCLASS_ID)
                .append(__Symbols.MATH_EQUAL)
                .append("Class.forName(RClass.getCanonicalName()+\"$id\");")
                .append(__Symbols.CODE_END)
                .append(" catch (ClassNotFoundException e) {\n")
                .append("e.printStackTrace();\n}\n");

        return sb.toString();
    }

}
