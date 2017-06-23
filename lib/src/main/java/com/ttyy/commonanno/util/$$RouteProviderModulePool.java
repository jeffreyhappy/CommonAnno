package com.ttyy.commonanno.util;

import com.ttyy.commonanno.__Symbols;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.TypeElement;

/**
 * Author: hjq
 * Date  : 2017/06/21 21:19
 * Name  : $$RouteProviderModulePool
 * Intro : Edit By hjq
 * Version : 1.0
 */
public class $$RouteProviderModulePool {

    LinkedList<String> moduleRouterPackageAbsolutePaths;

    String currentModuleBuildPath;

    String currentModuleJavaPath;

    private $$RouteProviderModulePool() {
        moduleRouterPackageAbsolutePaths = new LinkedList<>();
    }

    public static class Holder {
        static $$RouteProviderModulePool INSTANCE = new $$RouteProviderModulePool();
    }

    public static $$RouteProviderModulePool get() {
        return Holder.INSTANCE;
    }

    public void findCurrentProjectModules(TypeElement te) {

        String currentPackageTotalPath = getClass().getResource("").getPath();
        Pattern pathPattern = Pattern.compile("file:(.+)/build/(.+)");
        Matcher matcher = pathPattern.matcher(currentPackageTotalPath);
        matcher.matches();
        String currentProject = matcher.group(1);

        String hostProjectPath = new File(currentProject).getParent();

        File projDir = new File(hostProjectPath);

        // Android Studio Project File Struct
        String currentLocationTag = "/src/main/java/" + te.getQualifiedName().toString().replaceAll("\\.", "/") + ".java";

        for (File tmp : projDir.listFiles()) {

            if (tmp.isFile()
                    || tmp.getName().equals(".gradle")
                    || tmp.getName().equals(".idea")
                    || tmp.getName().equals("gradle")
                    || tmp.getName().equals("build")) {
                continue;
            }

            String modulePath = tmp.getPath();

            String moduleRouterPackagePath = modulePath + "/build/intermediates/classes/debug/";
            if(new File(moduleRouterPackagePath).exists()){
                $ProcessorLog.log("Find Module Project: " + moduleRouterPackagePath);
            }else {
                moduleRouterPackagePath = modulePath + "/build/intermediates/classes/release/";
                if(new File(moduleRouterPackagePath).exists()){
                    $ProcessorLog.log("Find Module Project: " + moduleRouterPackagePath);
                }
            }

            String locationTagTotalPath = modulePath + currentLocationTag;
            if (currentModuleBuildPath == null
                    && new File(locationTagTotalPath).exists()) {
                currentModuleBuildPath = moduleRouterPackagePath;
                currentModuleJavaPath = modulePath + "/src/main/java/";
                $ProcessorLog.log("Current Module Project Path: " + currentModuleBuildPath);
            }else {
                moduleRouterPackageAbsolutePaths.add(moduleRouterPackagePath);
            }
        }

    }

    /**
     * 预先准备20个moduleName
     *
     * @return
     */
    public String getTemplateModuleName() {

        String moduleIndex = System.currentTimeMillis()+"";
        for (int i = 0; i < 20; i++) {

            String clspath = __Symbols.ROUTE_PACKAGE + ".$RouteProvider$$" + i;
            clspath = clspath.replaceAll("\\.", "/")+".class";

            String selfAbsClsPath = currentModuleBuildPath + clspath;
            if(new File(selfAbsClsPath).exists()){
                moduleIndex = i + "";
                break;
            }

            boolean isExistInOtherDir = false;
            for(String dir : moduleRouterPackageAbsolutePaths){
                String abspath = dir+clspath;
                if(new File(abspath).exists()){
                    isExistInOtherDir = true;
                    break;
                }
            }

            if(!isExistInOtherDir){
                moduleIndex = i+"";
                break;
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

    public ArrayList<String> getModulePackages(){
        File file = new File(currentModuleJavaPath);
        ArrayList<String> paths = new ArrayList<>();
        filterDirs(file, paths);
        return paths;
    }

    private void filterDirs(File file, ArrayList<String> paths){
        for(File tmp : file.listFiles()){
            if(tmp.isDirectory()){
                paths.add(tmp.getPath().substring(currentModuleJavaPath.length()).replaceAll("\\\\", "."));
                filterDirs(tmp, paths);
            }
        }
    }

}
