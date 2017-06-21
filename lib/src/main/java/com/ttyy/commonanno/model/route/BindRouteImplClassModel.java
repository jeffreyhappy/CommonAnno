package com.ttyy.commonanno.model.route;

import com.ttyy.commonanno.__RouterIntf;
import com.ttyy.commonanno.__Symbols;
import com.ttyy.commonanno.util.$RouteUriUtil;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: BindRouteImplClassModel
 */

public class BindRouteImplClassModel {

    private BindRouteImplClassModel(){

    }

    public String getPackageName(){
        return __Symbols.ROUTE_PACKAGE;
    }

    public String getSelfClassName(){
        return __Symbols.ROUTE_PACKAGE+"."+getSelfSimpleClassName();
    }

    public String getSelfSimpleClassName(){
        return "$RouteImpl";
    }

    public String toClassCode(){
        StringBuilder sb = new StringBuilder();

        sb.append(__Symbols.TYPE_PACKAGE)
                .append(getPackageName())
                .append(__Symbols.LINE_END);

        sb.append("import android.content.Intent;\n")
                .append("import android.os.Parcelable;\n")
                .append("import java.io.Serializable;\n")
                .append("import android.content.Context;\n")
                .append("import android.app.Activity;\n")
                .append("import ").append($RouteUriUtil.class.getCanonicalName()).append(";\n")
                .append("import ").append(__RouterIntf.class.getCanonicalName()).append(";\n");

        // class document start
        sb.append(__Symbols.AUTH_PUBLIC)
                .append(__Symbols.TYPE_CLASS)
                .append(getSelfSimpleClassName())
                .append(__Symbols.ACTION_IMPL)
                .append(__RouterIntf.class.getCanonicalName())
                .append(" <").append("Context, Parcelable").append("> {\n");

        // declare local field
        sb.append("private Intent intent;\n");
        sb.append("private int flags;\n");
        sb.append("private Class cls;\n");
        sb.append("private int mRequestCode;\n");

        // empty params constructor
        sb.append("public $RouteImpl(){\n");
        sb.append("intent = new Intent();\n");
        sb.append("flags = intent.getFlags();\n");
        sb.append("mRequestCode = -1;\n");
        sb.append("\n}\n");

        // setRouteUri method start
        sb.append("public void setRouteUri(String uri){\n");
        sb.append("$RouteUriUtil.routeInject(uri, this);\n");
        // setRouteUri method end
        sb.append("\n}\n");

        // setRouteClass method start
        sb.append("public void setRouteClass(Class cls){\n");
        sb.append("this.cls = cls;\n");
        // setRouteClass method end
        sb.append("\n}\n");

        // putInt method start
        sb.append("public __RouterIntf putInt(String key, int value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putInt method end
        sb.append("return this;\n}\n");

        // putFloat method start
        sb.append("public __RouterIntf putFloat(String key, float value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putFloat method end
        sb.append("return this;\n}\n");

        // putDouble method start
        sb.append("public __RouterIntf putDouble(String key, double value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putDouble method end
        sb.append("return this;\n}\n");

        // putBoolean method start
        sb.append("public __RouterIntf putBoolean(String key, boolean value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putBoolean method end
        sb.append("return this;\n}\n");

        // putString method start
        sb.append("public __RouterIntf putString(String key, String value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putString method end
        sb.append("return this;\n}\n");

        // putSerializable method start
        sb.append("public __RouterIntf putSerializable(String key, Serializable value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putSerializable method end
        sb.append("return this;\n}\n");

        // putParcelable method start
        sb.append("public __RouterIntf putParcelable(String key, Parcelable value){\n");
        sb.append("this.intent.putExtra(key, value);\n");
        // putParcelable method end
        sb.append("return this;\n}\n");

        // addIntentFlags method start
        sb.append("public  __RouterIntf addIntentFlags(int flags){\n");
        sb.append("this.flags |= flags;\n");
        // addIntentFlags method end
        sb.append("return this;\n}\n");

        // setIntentFlags method start
        sb.append("public __RouterIntf setIntentFlags(int flags){\n");
        sb.append("this.flags = flags;\n");
        // setIntentFlags method end
        sb.append("return this;\n}\n");

        // setRequestCode method start
        sb.append("public __RouterIntf setRequestCode(int code){\n");
        sb.append("this.mRequestCode = code;\n");
        // setRequestCode method end
        sb.append("return this;\n}\n");

        // navigate method start
        sb.append("public void navigate(Context context){\n");

        /*
         Intent intent = new Intent(context, mTargetClass);
        intent.putExtras(mBundle);
        for(int flag : mFlags){
            intent.addFlags(flag);
        }

        if(mRequestCode > 0){
            ((Activity)context).startActivityForResult(intent, mRequestCode);
        }else {
            context.startActivity(intent);
        }
         */
        sb.append("intent.setClass(context, this.cls);\n");
        sb.append("intent.addFlags(this.flags);\n");
        // if start
        sb.append("if(mRequestCode > 0){\n");
        sb.append("((Activity)context).startActivityForResult(intent, mRequestCode);\n");
        // if end
        sb.append("\n}");
        // else start
        sb.append(" else {\n");
        sb.append("context.startActivity(intent);\n");
        // else end
        sb.append("\n}\n");

        // navigate method end
        sb.append("\n}\n");

        // class document end
        sb.append("\n}");
        return sb.toString();
    }

    public static BindRouteImplClassModel ROUTE_IMPL = new BindRouteImplClassModel();
}
