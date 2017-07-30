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
import com.ttyy.commonanno.anno.route.BindExtra;
import com.ttyy.commonanno.anno.route.BindRoute;
import com.ttyy.commonanno.filter.$BindClassFilter;
import com.ttyy.commonanno.filter.$BindRouteFilter;
import com.ttyy.commonanno.model.BindClassModel;
import com.ttyy.commonanno.model.route.BindRouteImplClassModel;
import com.ttyy.commonanno.model.route.BindRouteProviderClassModel;
import com.ttyy.commonanno.util.$$RouteProviderModulePool;
import com.ttyy.commonanno.util.$ProcessorLog;

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
import javax.lang.model.element.Element;
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
public class __AnnotationProcessor extends AbstractProcessor {

    final String OPTIONS_MODULE_KEY = "moduleName";

    Filer mFiler;
    Elements mElements;

    String mUserSettedModuleName = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElements = processingEnv.getElementUtils();

        // logger init
        Messager mPrinter = processingEnv.getMessager();
        $ProcessorLog.init(mPrinter);

        mUserSettedModuleName = processingEnv.getOptions().get(OPTIONS_MODULE_KEY);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        TypeElement typeElement = null;
        ArrayList<BindClassModel> classCodes = $BindClassFilter.filter(roundEnv, mElements);
        for (BindClassModel tmp : classCodes) {
            typeElement = tmp.getTypeElement();
            try {
                JavaFileObject codeFile = mFiler.createSourceFile(tmp.getSelfClassName(), typeElement);
                Writer writer = codeFile.openWriter();
                writer.append(tmp.toClassCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // About Route
        typeElement = null;
        for (Element e : roundEnv.getElementsAnnotatedWith(BindRoute.class)) {
            typeElement = (TypeElement) e;
            break;
        }

        if (typeElement == null) {
            return false;
        } else {
            // find total modules first
            // to find all packages in current module
            $$RouteProviderModulePool.get().findCurrentProjectModules(typeElement);
        }

        // provider
        BindRouteProviderClassModel routeProviderClassModel = $BindRouteFilter.filter(roundEnv, mElements);

        if(mUserSettedModuleName == null
                || mUserSettedModuleName.trim().equals("")){
            $ProcessorLog.log("Getting RouteProvider Class Suffix From NamePool...");
            mUserSettedModuleName = $$RouteProviderModulePool.get().getTemplateUniqueProviderIndex();
        }

        $ProcessorLog.log("Creating RouteProvider With ModuleSuffix " + mUserSettedModuleName + " ...");
        routeProviderClassModel.setModuleName(mUserSettedModuleName);

        try {
            // __RouteProvider Impl Class
            JavaFileObject codeFile = mFiler.createSourceFile(routeProviderClassModel.getSelfClassName(), typeElement);

            Writer writer = codeFile.openWriter();
            writer.append(routeProviderClassModel.toClassCode());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            $ProcessorLog.log("Create RouteProvider With ModuleIndex " + mUserSettedModuleName + " Error!");
        }

        if ($$RouteProviderModulePool.get().isNeedToBuildClassFile(BindRouteImplClassModel.ROUTE_IMPL.getSelfSimpleClassName())) {
            // Route Impl Class Can Only Create Once
            $ProcessorLog.log("Creating Class " + BindRouteImplClassModel.ROUTE_IMPL.getSelfClassName());
            try {
                // __RouterIntf Impl Class
                JavaFileObject codeFile = mFiler.createSourceFile(BindRouteImplClassModel.ROUTE_IMPL.getSelfClassName(), typeElement);
                Writer writer = codeFile.openWriter();
                writer.append(BindRouteImplClassModel.ROUTE_IMPL.toClassCode());
                writer.flush();
                writer.close();

            } catch (IOException e) {
                $ProcessorLog.log("Create Class " + BindRouteImplClassModel.ROUTE_IMPL.getSelfClassName() + " Error!");
            }
        }

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>() {
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

                add(BindRoute.class.getCanonicalName());
                add(BindExtra.class.getCanonicalName());
            }
        };
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
