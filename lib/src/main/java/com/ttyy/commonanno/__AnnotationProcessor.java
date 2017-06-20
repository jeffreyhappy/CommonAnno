package com.ttyy.commonanno;

import com.ttyy.commonanno.anno.BindLayout;
import com.ttyy.commonanno.anno.BindLayout2;
import com.ttyy.commonanno.anno.BindView;
import com.ttyy.commonanno.anno.Inject;
import com.ttyy.commonanno.anno.OnClick;
import com.ttyy.commonanno.anno.OnClick2;
import com.ttyy.commonanno.anno.OnItemClick;
import com.ttyy.commonanno.anno.OnItemClick2;
import com.ttyy.commonanno.anno.OnLongClick;
import com.ttyy.commonanno.anno.OnLongClick2;
import com.ttyy.commonanno.filter.$BindClassFilter;
import com.ttyy.commonanno.model.BindClassCode;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

/**
 * Author: hjq
 * Date  : 2017/06/19 19:27
 * Name  : __AnnotationProcessor
 * Intro : Edit By hjq
 * Version : 1.0
 */
public class __AnnotationProcessor extends AbstractProcessor{

    Messager mPrinter;
    Filer mFiler;
    Elements mElements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mPrinter = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
        mElements = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        ArrayList<BindClassCode> classCodes = $BindClassFilter.filter(roundEnv, mElements);
        for(BindClassCode tmp : classCodes){
            TypeElement typeElement = tmp.getTypeElement();
            try {
                JavaFileObject codeFile = mFiler.createSourceFile(tmp.getSelfClassName(),typeElement);
                Writer writer = codeFile.openWriter();
                writer.append(tmp.toClassCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>(){
            {
                add(BindLayout.class.getCanonicalName());
                add(BindLayout2.class.getCanonicalName());
                add(BindView.class.getCanonicalName());

                add(Inject.class.getCanonicalName());

                add(OnClick.class.getCanonicalName());
                add(OnClick2.class.getCanonicalName());

                add(OnItemClick.class.getCanonicalName());
                add(OnItemClick2.class.getCanonicalName());

                add(OnLongClick.class.getCanonicalName());
                add(OnLongClick2.class.getCanonicalName());
            }
        };
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
