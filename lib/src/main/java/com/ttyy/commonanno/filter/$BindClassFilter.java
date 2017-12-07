package com.ttyy.commonanno.filter;

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
import com.ttyy.commonanno.model.BindClassModel;
import com.ttyy.commonanno.model.BindLayoutModel;
import com.ttyy.commonanno.model.BindViewModel;
import com.ttyy.commonanno.model.event.BindClickModel;
import com.ttyy.commonanno.model.event.BindEventMethodModel;
import com.ttyy.commonanno.model.event.BindItemClickModel;
import com.ttyy.commonanno.model.event.BindLongClickModel;
import com.ttyy.commonanno.model.route.BindExtraModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: $BindClassFilter
 */

public class $BindClassFilter {

    private $BindClassFilter(){

    }

    public static final ArrayList<BindClassModel> filter(RoundEnvironment env, Elements mElements){
        HashMap<String, BindClassModel> maps = new HashMap<>();

        // BindLayout Annotated Class Need To Create Special JinClass File First
        for(Element e : env.getElementsAnnotatedWith(BindLayout.class)){
            if(e.getKind() != ElementKind.CLASS){
                continue;
            }

            TypeElement mClassElement = (TypeElement) e;

            BindClassModel tmp = new BindClassModel(mClassElement);
            String strPackageName = getTypeElementPackageName(mClassElement, mElements);
            String strClassName = mClassElement.getQualifiedName().toString();
            tmp.setPackageName(strPackageName).setClassName(strClassName);

            if(maps.get(tmp.getSelfClassName()) != null){
                // Has Created The Class Document Object
                continue;
            }else {
                maps.put(tmp.getSelfClassName(), tmp);
            }

            BindLayout annoBindLayout = e.getAnnotation(BindLayout.class);
            BindLayoutModel contentView = new BindLayoutModel();
            int viewId = annoBindLayout.value();
            contentView.setIsContentView(true).setResourceId(viewId);
            tmp.setContentView(contentView);

            getChildElementAnnos(mClassElement, tmp);
        }

        // BindLayout2  Annotated Class Need To Create Special JinClass File Second
        for(Element e : env.getElementsAnnotatedWith(BindLayout2.class)){
            if(e.getKind() != ElementKind.CLASS){
                continue;
            }

            TypeElement mClassElement = (TypeElement) e;

            BindClassModel tmp = new BindClassModel(mClassElement);
            String strPackageName = getTypeElementPackageName(mClassElement, mElements);
            String strClassName = mClassElement.getQualifiedName().toString();
            tmp.setPackageName(strPackageName).setClassName(strClassName);

            if(maps.get(tmp.getSelfClassName()) != null){
                // Has Created The Class Document Object
                continue;
            }else {
                maps.put(tmp.getSelfClassName(), tmp);
            }

            BindLayout2 annoBindLayout2 = e.getAnnotation(BindLayout2.class);
            BindLayoutModel contentView = new BindLayoutModel();
            String viewIdName = annoBindLayout2.value();
            contentView.setIsContentView(true).setResourceIdName(viewIdName);
            tmp.setContentView(contentView);

            getChildElementAnnos(mClassElement, tmp);
        }

        // Inject Annotated Class Need To Create Special JinClass File Third
        for(Element e : env.getElementsAnnotatedWith(Inject.class)){
            if(e.getKind() != ElementKind.CLASS){
                continue;
            }

            TypeElement mClassElement = (TypeElement) e;

            BindClassModel tmp = new BindClassModel(mClassElement);
            String strPackageName = getTypeElementPackageName(mClassElement, mElements);
            String strClassName = mClassElement.getQualifiedName().toString();
            tmp.setPackageName(strPackageName).setClassName(strClassName);

            if(maps.get(tmp.getSelfClassName()) != null){
                // Has Created The Class Document Object
                continue;
            }else {
                maps.put(tmp.getSelfClassName(), tmp);
            }

            getChildElementAnnos(mClassElement, tmp);
        }

        return new ArrayList<>(maps.values());
    }

    static final void getChildElementAnnos(TypeElement typeElement, BindClassModel code){
        for(Element e : typeElement.getEnclosedElements()){

            if(injectMemberView(e, code)){

            }else if(injectMemberEvent(e, code)){

            }else {
                injectMemberExtra(e, code);
            }

        }
    }

    static final boolean injectMemberView(Element e, BindClassModel code){
        if(e.getAnnotation(BindView.class) != null){
            BindViewModel viewCode = new BindViewModel();

            BindView bindView = e.getAnnotation(BindView.class);
            viewCode.setResourceId(bindView.value());
            viewCode.setResourceIdName(e.getSimpleName().toString());

            viewCode.setFieldName(e.getSimpleName().toString());
            viewCode.setFieldType(e.asType().toString());

            code.addFindView(viewCode);

        }else if(e.getAnnotation(BindLayout.class) != null){
            BindLayoutModel layoutCode = new BindLayoutModel();

            BindLayout bindLayout = e.getAnnotation(BindLayout.class);
            layoutCode.setResourceId(bindLayout.value());
            layoutCode.setResourceIdName(e.getSimpleName().toString());

            layoutCode.setFieldName(e.getSimpleName().toString());
            layoutCode.setFieldType(e.asType().toString());

            code.addInflateLayout(layoutCode);

        }else if(e.getAnnotation(BindLayout2.class) != null){
            BindLayoutModel layoutCode = new BindLayoutModel();

            BindLayout2 bindLayout2 = e.getAnnotation(BindLayout2.class);
            layoutCode.setResourceIdName(bindLayout2.value());

            layoutCode.setFieldName(e.getSimpleName().toString());
            layoutCode.setFieldType(e.asType().toString());

            code.addInflateLayout(layoutCode);

        }else {

            return false;
        }
        return true;
    }

    static final boolean injectMemberExtra(Element e, BindClassModel code){
        if(e.getAnnotation(BindExtra.class) != null){

            BindExtra bindExtra = e.getAnnotation(BindExtra.class);

            BindExtraModel extra = new BindExtraModel();
            extra.setFieldName(e.getSimpleName().toString())
                    .setFieldType(e);

            if(bindExtra.value() == null
                    || bindExtra.value().trim().equals("")){
                extra.setExtraKey(e.getSimpleName().toString());
            }else {
                extra.setExtraKey(bindExtra.value());
            }

            code.addExtra(extra);

            return true;
        }

        return false;
    }

    static final boolean injectMemberEvent(Element e, BindClassModel code){
        if(e.getAnnotation(OnClick.class) != null){
            BindClickModel clickCode = new BindClickModel();

            OnClick onClick = e.getAnnotation(OnClick.class);
            clickCode.setOnClickResourceIds(onClick.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnClick(clickCode);

        }else if(e.getAnnotation(OnClick2.class) != null){
            BindClickModel clickCode = new BindClickModel();

            OnClick2 onClick2 = e.getAnnotation(OnClick2.class);
            clickCode.setOnClickResourceIdNames(onClick2.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnClick(clickCode);

        }else if(e.getAnnotation(OnLongClick.class) != null){
            BindLongClickModel longClickCode = new BindLongClickModel();

            OnLongClick onLongClick = e.getAnnotation(OnLongClick.class);
            longClickCode.setLongClickResourceIds(onLongClick.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnLongClick(longClickCode);

        }else if(e.getAnnotation(OnLongClick2.class) != null){
            BindLongClickModel longClickCode = new BindLongClickModel();

            OnLongClick2 onLongClick2 = e.getAnnotation(OnLongClick2.class);
            longClickCode.setLongClickResourceIdNames(onLongClick2.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnLongClick(longClickCode);

        }else if(e.getAnnotation(OnItemClick.class) != null){
            BindItemClickModel itemClickCode = new BindItemClickModel();

            OnItemClick onItemClick = e.getAnnotation(OnItemClick.class);
            itemClickCode.setLongClickResourceIds(onItemClick.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnItemClick(itemClickCode);

        }else if(e.getAnnotation(OnItemClick2.class) != null){
            BindItemClickModel itemClickCode = new BindItemClickModel();

            OnItemClick2 onItemClick2 = e.getAnnotation(OnItemClick2.class);
            itemClickCode.setLongClickResourceIdNames(onItemClick2.value())
                    .setActionMethod(getActionMethod((ExecutableElement) e));

            code.addOnItemClick(itemClickCode);

        }else {

            return false;
        }
        return true;
    }

    static final BindEventMethodModel getActionMethod(ExecutableElement e){

        BindEventMethodModel methodCode = new BindEventMethodModel();
        methodCode.setMethodName(e.getSimpleName().toString())
                .setReturnType(e.getReturnType().toString());

        List<? extends VariableElement> parameters = e.getParameters();
        int index = 0;
        for(VariableElement tmp : parameters){

            BindEventMethodModel.Parameter param = new BindEventMethodModel.Parameter();
            param.strParameterType = tmp.asType().toString();
            param.strParameterName = "var"+index;
            index++;

            methodCode.addParameter(param);
        }


        return methodCode;
    }

    static final String getTypeElementPackageName(TypeElement mClassElement, Elements mElements){
        return mElements.getPackageOf(mClassElement).getQualifiedName().toString();
    }

}
