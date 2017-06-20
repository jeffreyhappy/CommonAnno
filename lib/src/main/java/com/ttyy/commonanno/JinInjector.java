package com.ttyy.commonanno;

import java.util.HashMap;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: JinInjector
 */

public class JinInjector {

    HashMap<String, __InjectIntf> intfs;

    Class<?> mTmpRClass;

    private JinInjector() {
        intfs = new HashMap<>();
    }

    static class Holder {
        static JinInjector INSTANCE = new JinInjector();
    }

    public static JinInjector get() {
        Holder.INSTANCE.mTmpRClass = null;
        return Holder.INSTANCE;
    }

    public JinInjector setRClass(Class<?> cls) {
        mTmpRClass = cls;
        return this;
    }

    public void injectActivity(Object ft){
        inject(Finder.Activity, ft, ft);
    }

    public void injectView(Object ft){
        inject(Finder.View, ft, ft);
    }

    public void inject(Finder finder, Object from, Object to) {
        String targetClazzName = to.getClass().getCanonicalName();

        __InjectIntf intf = intfs.get(targetClazzName);
        if (intf == null) {
            int subLen = to.getClass().getPackage().getName().length() + 1;
            String aboutClassName = targetClazzName.substring(subLen);
            String selfClassConvertedName = aboutClassName.replace(".", __Symbols.TYPE_DIVIDER);

            // 解决内部类的命名冲突问题
            // xxx.org.bubbls.test.MainActivity.MynostaticView
            // 包名xxx.org.bubbles.test
            // 类名MainActivity.MynostaticView . <-> $
            // 转变MainActivity$MynostaticView
            // 全路径xxx.org.bubbls.test.MainActivity$MynostaticView$$JinClass
            String injectClazzName = to.getClass().getPackage().getName() + "." + selfClassConvertedName + __Symbols.SPECIAL_SUFFIX;

            try {
                Class intfClass = Class.forName(injectClazzName);
                intf = (__InjectIntf) intfClass.newInstance();
                intfs.put(targetClazzName, intf);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(intf != null){
            intf.inject(finder, mTmpRClass, from, to);
        }

        mTmpRClass = null;
    }

}
