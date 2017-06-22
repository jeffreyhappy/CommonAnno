package com.ttyy.commonanno.util;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * author: admin
 * date: 2017/06/22
 * version: 0
 * mail: secret
 * desc: $ProcessorLog
 */

public class $ProcessorLog {

    static Messager mPrinter;

    private $ProcessorLog(){

    }

    public static void init(Messager mPrinter){
        $ProcessorLog.mPrinter = mPrinter;
    }

    public static void log(String message){
        mPrinter.printMessage(Diagnostic.Kind.NOTE, message);
    }

}
