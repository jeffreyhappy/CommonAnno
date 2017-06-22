package com.ttyy.commonanno.util;

import com.ttyy.commonanno.__RouteLoader;

import java.util.HashMap;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: $$RouteProvider
 */

public class $$RouteProvider {

    HashMap<String, Class> routeMaps;

    private $$RouteProvider() {
        routeMaps = new HashMap<>();

        for(String path : $$RouteProviderModulePool.get().getModuleNamePool()){

            try {
                __RouteLoader provider = (__RouteLoader) Class.forName(path).newInstance();

                provider.loadInto(routeMaps);

            } catch (ClassNotFoundException e) {
                break;
            } catch (InstantiationException e) {
                break;
            } catch (IllegalAccessException e) {
                break;
            }

        }

    }

    volatile static $$RouteProvider INSTANCE = null;

    public static $$RouteProvider get() {

        $$RouteProvider inst = INSTANCE;

        if (inst == null) {
            synchronized ($$RouteProvider.class) {
                inst = INSTANCE;
                if (inst == null) {
                    inst = new $$RouteProvider();
                    INSTANCE = inst;
                }
            }
        }

        return inst;
    }

    public Class getMappedRouteClass(String uri) {

        return routeMaps.get(uri);
    }

}
