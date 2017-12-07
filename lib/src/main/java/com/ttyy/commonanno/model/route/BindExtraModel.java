package com.ttyy.commonanno.model.route;


import com.ttyy.commonanno.__Symbols;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: BindExtraModel
 */

public class BindExtraModel {

    private String strExtraKey;
    private String strFieldName;
    private String strFieldType;
    private String strFieldTypePath;

    public BindExtraModel() {

    }

    public BindExtraModel setExtraKey(String key) {
        this.strExtraKey = key;
        return this;
    }

    public BindExtraModel setFieldName(String name) {
        this.strFieldName = name;
        return this;
    }

    public BindExtraModel setFieldType(TypeElement type) {
        this.strFieldType = type.getQualifiedName().toString();

        this.strFieldTypePath = this.strFieldType;
        if (this.strFieldTypePath.contains("<")) {
            this.strFieldTypePath = this.strFieldTypePath.substring(0, this.strFieldTypePath.indexOf("<"));
        }

        PackageElement packageElement = null;
        Element tempType = type;
        while (tempType.getKind() != ElementKind.PACKAGE) {
            tempType = tempType.getEnclosingElement();
        }
        packageElement = (PackageElement) tempType;
        String packagePath = packageElement.getQualifiedName().toString();

        String simpleFieldTypePath = this.strFieldTypePath.substring(packagePath.length() + 1);
        simpleFieldTypePath = simpleFieldTypePath.replace(".", __Symbols.TYPE_DIVIDER);
        this.strFieldTypePath = packagePath + "." + simpleFieldTypePath;
        return this;
    }

    public String getExtraKey() {
        return this.strExtraKey;
    }

    public String getFieldName() {
        return this.strFieldName;
    }

    public String getFieldTypePath() {
        return this.strFieldTypePath;
    }

    public String getFieldType() {
        return this.strFieldType;
    }

}
