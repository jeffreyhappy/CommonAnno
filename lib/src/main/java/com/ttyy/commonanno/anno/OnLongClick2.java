package com.ttyy.commonanno.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: hjq
 * Date  : 2017/06/19 19:33
 * Name  : OnClick
 * Intro : Edit By hjq
 * Version : 1.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface OnLongClick2 {

    String[] value();

}
