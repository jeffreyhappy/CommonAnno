package com.ttyy.commonanno;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: __InjectIntf
 */

public interface __InjectIntf<T> {

    void inject(Finder type, Class<?> RClass, Object source, T target);

}
