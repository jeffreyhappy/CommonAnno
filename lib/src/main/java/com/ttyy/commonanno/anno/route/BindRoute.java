package com.ttyy.commonanno.anno.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: BindRoute
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface BindRoute {

    String value();

}
