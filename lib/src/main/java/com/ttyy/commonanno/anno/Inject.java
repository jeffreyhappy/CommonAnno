package com.ttyy.commonanno.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: Inject
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Inject {
}
