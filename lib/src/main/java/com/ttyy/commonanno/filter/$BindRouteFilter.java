package com.ttyy.commonanno.filter;

import com.ttyy.commonanno.anno.route.BindRoute;
import com.ttyy.commonanno.model.route.BindRouteProviderClassModel;
import com.ttyy.commonanno.model.route.BindRouteTupple;
import com.ttyy.commonanno.util.$ProcessorLog;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: $BindRouteFilter
 */

public class $BindRouteFilter {

    private $BindRouteFilter(){

    }

    public static BindRouteProviderClassModel filter(RoundEnvironment env, Elements mElements){
        BindRouteProviderClassModel model = BindRouteProviderClassModel.create();

        for(Element e : env.getElementsAnnotatedWith(BindRoute.class)){
            BindRoute bindRoute = e.getAnnotation(BindRoute.class);

            TypeElement te = (TypeElement) e;
            BindRouteTupple routeTupple = new BindRouteTupple();
            routeTupple.uri = bindRoute.value();
            routeTupple.className = te.getQualifiedName().toString();

            $ProcessorLog.log("uri "+routeTupple.uri+" cls "+routeTupple.className);
            model.addRouteTupple(routeTupple);
        }

        return model;
    }

}
