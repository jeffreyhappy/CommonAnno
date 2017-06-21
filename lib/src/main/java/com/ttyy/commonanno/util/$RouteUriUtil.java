package com.ttyy.commonanno.util;

import com.ttyy.commonanno.__RouterIntf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: $RouteUriUtil
 */

public class $RouteUriUtil {

    private $RouteUriUtil() {

    }

    public static void routeInject(String uri, __RouterIntf router) {
        $$RouteProvider provider = $$RouteProvider.get();

        if (provider == null) {
            return;
        }

        uri = uri.trim();
        Matcher matcher = URI_P.matcher(uri);

        String clsUri = null;
        if (!matcher.matches()) {
            clsUri = uri;

        } else {
            if (matcher.find(1)) {

                clsUri = uri.split("\\?")[0];


                // a=1&b=2&c=abc
                String params = matcher.group(1);
                for (String param : params.split("&")) {
                    String[] values = param.split("=");
                    String key = values[0].trim();
                    String value = values[1].trim();
                    if (isInteger(value)) {
                        router.putInt(key, Integer.parseInt(value));
                    } else if (isFloat(value)) {
                        router.putFloat(key, Float.parseFloat(value));
                    } else if (isDouble(value)) {
                        router.putDouble(key, Double.parseDouble(value));
                    } else {
                        if ("false".equals(value)
                                || "true".equals(value)) {
                            router.putBoolean(key, Boolean.valueOf(value));
                        } else {
                            router.putString(key, value);
                        }
                    }
                }
            }
        }

        // target class
        router.setRouteClass(provider.getMappedRouteClass(clsUri));
    }

    static boolean isInteger(String target) {
        return INT_P.matcher(target).matches();
    }

    static boolean isFloat(String target) {
        return FLOAT_P.matcher(target).matches();
    }

    static boolean isDouble(String target) {
        return FLOAT_D.matcher(target).matches();
    }

    static Pattern URI_P = Pattern.compile("[^\\?]+\\?([a-zA-Z]+.*=[a-zA-Z0-9-/\\s_]+&?)*");

    static Pattern INT_P = Pattern.compile("[-0-9]*");

    static Pattern FLOAT_P = Pattern.compile("[-0-9]+\\.[0-9]+f");

    static Pattern FLOAT_D = Pattern.compile("[-0-9]+\\.[0-9]+");
}
