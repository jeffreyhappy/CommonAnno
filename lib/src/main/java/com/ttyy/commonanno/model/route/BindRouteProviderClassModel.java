package com.ttyy.commonanno.model.route;

import com.ttyy.commonanno.__RouteProviderIntf;
import com.ttyy.commonanno.__Symbols;
import com.ttyy.commonanno.anno.route.BindRoute;

import java.util.LinkedList;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: BindRouteProviderClassModel
 */

public class BindRouteProviderClassModel {

    LinkedList<BindRouteTupple> mappedRoutes;

    String moduleName = "";

    private BindRouteProviderClassModel(){
        mappedRoutes = new LinkedList<>();
    }

    public String getPackageName(){
        return __Symbols.ROUTE_PACKAGE;
    }

    public String getSelfClassName(){
        return getPackageName()+"."+getSelfSimpleClassName();
    }

    public String getSelfSimpleClassName(){
        return "$RouteProvider$$"+moduleName;
    }

    public BindRouteProviderClassModel setModuleName(String name){
        this.moduleName = name;
        return this;
    }

    public BindRouteProviderClassModel addRouteTupple(BindRouteTupple tupple){
        mappedRoutes.add(tupple);
        return this;
    }

    public String toClassCode(){
        StringBuilder sb = new StringBuilder();

        sb.append(__Symbols.TYPE_PACKAGE)
                .append(getPackageName()).append(__Symbols.LINE_END);

        sb.append("import java.util.HashMap;\n");

        // class
        sb.append(__Symbols.AUTH_PUBLIC)
                .append(__Symbols.TYPE_CLASS)
                .append(getSelfSimpleClassName())
                .append(__Symbols.ACTION_IMPL)
                .append(__RouteProviderIntf.class.getCanonicalName())
                .append(__Symbols.CODE_START);

        // loadInto method start
        sb.append("public void loadInto(HashMap<String, Class> maps){\n");

        for(BindRouteTupple tmp : mappedRoutes){
            sb.append("maps.put(\"").append(tmp.uri).append("\", ")
                    .append(tmp.className).append(".class);\n");
        }

        // loadInto method end
        sb.append("\n}\n");

        sb.append(__Symbols.CODE_END);
        return sb.toString();
    }

    public static BindRouteProviderClassModel create(){
        return new BindRouteProviderClassModel();
    }
}
