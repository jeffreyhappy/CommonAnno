package com.ttyy.commonanno.util;

import com.ttyy.commonanno.__Symbols;
import com.ttyy.commonanno.model.route.BindRouteImplClassModel;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/**
 * Author: hjq
 * Date  : 2017/06/21 21:19
 * Name  : $$RouteProviderModulePool
 * Intro : Edit By hjq
 * Version : 1.0
 */
public class $$RouteProviderModulePool {

    private $$RouteProviderModulePool() {
    }

    public static class Holder {
        static $$RouteProviderModulePool INSTANCE = new $$RouteProviderModulePool();
    }

    public static $$RouteProviderModulePool get() {
        return Holder.INSTANCE;
    }

    /**
     * 预先准备20个moduleName
     *
     * @return
     */
    public String getTemplateModuleName(Filer mFiler, TypeElement te) {

        String moduleIndex = "100";
        for (int i = 0; i < 20; i++) {

            Class cls = null;
            String clspath = __Symbols.ROUTE_PACKAGE + ".$RouteProvider$$" + i;
            try {
                cls = Class.forName(clspath);
            } catch (ClassNotFoundException e) {

            }

            if (cls == null) {
                moduleIndex = i + "";
                break;
            } else {

                if (cls == null
                        || cls.getResource("") == null)
                    continue;

                String clsFilePath = cls.getResource("").getPath() + "$RouteProvider$$" + i+".class";
                File clsFile = new File(clsFilePath);
                if(!clsFile.exists()){
                    // not in this project ignore this logic
                    continue;
                }
                long lastModified = clsFile.lastModified();
                long currentMillions = System.currentTimeMillis();


                if (currentMillions - lastModified >= 5 * 1000) {
                    System.out.println("Diff Time >= 5s Override Provider Index " + i);
                    moduleIndex = i + "";
                    break;
                } else {
                    System.out.println("Diff Time < 5s So I Think It's Processing ");
                }

            }
        }

        return moduleIndex;
    }

    public List<String> getModuleNamePool() {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(__Symbols.ROUTE_PACKAGE + ".$RouteProvider$$" + i);
        }
        return list;
    }

    public static class TemplateModuleInfo {

        public String moduleIndex;

        public JavaFileObject codeFile;

    }

}
