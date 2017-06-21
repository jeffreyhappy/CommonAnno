package com.ttyy.commonanno;

import java.io.Serializable;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: __RouterIntf
 */

public interface __RouterIntf<T, P>{

    void setRouteUri(String uri);

    void setRouteClass(Class cls);

    __RouterIntf putInt(String key, int value);

    __RouterIntf putFloat(String key, float value);

    __RouterIntf putDouble(String key, double value);

    __RouterIntf putBoolean(String key, boolean value);

    __RouterIntf putString(String key, String value);

    __RouterIntf putSerializable(String key, Serializable value);

    __RouterIntf putParcelable(String key, P value);

    __RouterIntf addIntentFlags(int flags);

    __RouterIntf setIntentFlags(int flags);

    __RouterIntf setRequestCode(int code);

    void navigate(T context);
}
