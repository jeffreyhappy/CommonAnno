package com.ttyy.commonanno.model;

import com.ttyy.commonanno.Finder;
import com.ttyy.commonanno.__InjectIntf;
import com.ttyy.commonanno.__Symbols;
import com.ttyy.commonanno.model.event.BindClickCode;
import com.ttyy.commonanno.model.event.BindEventMethodCode;
import com.ttyy.commonanno.model.event.BindItemClickCode;
import com.ttyy.commonanno.model.event.BindLongClickCode;
import com.ttyy.commonanno.util.$RClassCodeUtil;

import java.util.LinkedList;

import javax.lang.model.element.TypeElement;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: BindClassCode
 */

public class BindClassCode {

    String strPackageName;
    String strParentClassName;
    String strSelfClassName;
    TypeElement mTypeElement;

    BindLayoutCode contentView;

    LinkedList<BindViewCode> views;

    LinkedList<BindLayoutCode> layouts;

    LinkedList<BindClickCode> onClicks;

    LinkedList<BindLongClickCode> onLongClicks;

    LinkedList<BindItemClickCode> onItemClicks;

    public BindClassCode(TypeElement element) {
        mTypeElement = element;
        views = new LinkedList<>();
        layouts = new LinkedList<>();
        onClicks = new LinkedList<>();
        onLongClicks = new LinkedList<>();
        onItemClicks = new LinkedList<>();
    }

    public TypeElement getTypeElement() {
        return mTypeElement;
    }

    public BindClassCode setPackageName(String pkg) {
        this.strPackageName = pkg;
        return this;
    }

    public BindClassCode setClassName(String className) {
        this.strParentClassName = className;

        int subLen = strPackageName.length() + 1;
        String aboutClassName = strParentClassName.substring(subLen);
        String selfClassConvertedName = aboutClassName.replace(".", __Symbols.TYPE_DIVIDER);

        // 解决内部类的命名冲突问题
        //             xxx.org.bubbls.test.MainActivity.MynostaticView
        // 包名         xxx.org.bubbles.test
        // 类名         MainActivity.MynostaticView . <-> $
        // 转变         MainActivity$MynostaticView
        // 真实路径     xxx.org.bubbls.test.MainActivity$MynostActicView$$JinClass
        this.strSelfClassName = strPackageName + "." + selfClassConvertedName + __Symbols.SPECIAL_SUFFIX;

        return this;
    }

    public String getPackageName() {
        return strPackageName;
    }

    public String getParentClassName() {
        return strParentClassName;
    }

    public String getSelfClassName() {
        return strSelfClassName;
    }

    public String getSelfClassSimpleName() {
        return strSelfClassName.substring(strPackageName.length() + 1);
    }

    public BindClassCode setContentView(BindLayoutCode code) {
        this.contentView = code;
        return this;
    }

    public BindClassCode addFindView(BindViewCode code) {
        this.views.add(code);
        return this;
    }

    public BindClassCode addInflateLayout(BindLayoutCode code) {
        this.layouts.add(code);
        return this;
    }

    public BindClassCode addOnClick(BindClickCode code) {
        this.onClicks.add(code);
        return this;
    }

    public BindClassCode addOnLongClick(BindLongClickCode code) {
        this.onLongClicks.add(code);
        return this;
    }

    public BindClassCode addOnItemClick(BindItemClickCode code) {
        this.onItemClicks.add(code);
        return this;
    }

    public String toClassCode() {
        StringBuilder sb = new StringBuilder();

        // package xx.xx.xxx
        sb.append(__Symbols.TYPE_PACKAGE)
                .append(getPackageName())
                .append(__Symbols.LINE_END);

        // import xx.xx.xxx
        sb.append(__Symbols.ACTION_IMPORT)
                .append(Finder.class.getCanonicalName())
                .append(__Symbols.LINE_END);

        sb.append("import android.app.Activity;\n");
        sb.append("import android.view.ViewGroup;\n");
        sb.append("import android.view.LayoutInflater;\n");
        sb.append("import android.view.View;\n");
        sb.append("import android.widget.AdapterView;\n");

        // public class xx.xx.xxx.xxx$xxx$$JinClass implements __InjectIntf{
        sb.append(__Symbols.AUTH_PUBLIC)
                .append(__Symbols.TYPE_CLASS)
                .append(getSelfClassSimpleName())
                .append("<").append("T extends ").append(strParentClassName).append(">")
                .append(__Symbols.ACTION_EXTEND)
                .append(strParentClassName)
                .append(__Symbols.ACTION_IMPL)
                .append(__InjectIntf.class.getCanonicalName())
                .append("<T>")
                .append(__Symbols.CODE_START);

        // public void inject(Finder finder, Class<?> RClass, Object source, Object target){
        //  xxxx;
        //  xxxxxx;
        // }
        sb.append(__Symbols.AUTH_PUBLIC)
                .append(__Symbols.RETURN_VOID)
                .append("inject(Finder type, Class<?> RClass, Object source, final T target)")
                .append(__Symbols.CODE_START);

        // R.layout.class
        sb.append("Class<?> ")
                .append(__Symbols.OBJ_RCLASS_LAYOUT)
                .append(__Symbols.MATH_EQUAL)
                .append("null;\n");
        sb.append("if(RClass != null){\n")
                .append($RClassCodeUtil.setResourceLayoutClass())
                .append("}\n");

        // R.id.class
        sb.append("Class<?> ")
                .append(__Symbols.OBJ_RCLASS_ID)
                .append(__Symbols.MATH_EQUAL)
                .append("null;\n");
        sb.append("if(RClass != null){\n")
                .append($RClassCodeUtil.setResouceIdClass())
                .append("}\n");

        // final Initialize field variables
        sb.append("try{\n");
        if (contentView != null) {
            sb.append("final int $$JinContentViewId = ");
            if (contentView.getResourceId() == -1) {
                sb.append("(int)")
                        .append(__Symbols.OBJ_RCLASS_LAYOUT)
                        .append(__Symbols.DOT_DIVIDER)
                        .append("getField(")
                        .append("\"").append(contentView.getResourceIdName()).append("\"")
                        .append(")")
                        .append(".get(")
                        .append(__Symbols.OBJ_RCLASS_LAYOUT)
                        .append(");\n");
            } else {
                sb.append(contentView.getResourceId())
                        .append(";\n");
            }
        }

        for (BindViewCode tmp : views) {
            sb.append("final int ")
                    .append(tmp.getResourceIdName())
                    .append(__Symbols.MATH_EQUAL);
            if (tmp.getResourceId() == -1) {
                sb.append("(int)")
                        .append(__Symbols.OBJ_RCLASS_ID)
                        .append("getField(")
                        .append("\"").append(tmp.getResourceIdName()).append("\"")
                        .append(")")
                        .append(".get(")
                        .append(__Symbols.OBJ_RCLASS_ID)
                        .append(");\n");
            } else {
                sb.append(tmp.getResourceId())
                        .append(";\n");
            }
        }

        for (BindLayoutCode tmp : layouts) {
            sb.append("final int ")
                    .append(tmp.getResourceIdName())
                    .append(__Symbols.MATH_EQUAL);
            if (tmp.getResourceId() == -1) {
                sb.append("(int)")
                        .append(__Symbols.OBJ_RCLASS_LAYOUT)
                        .append("getField(")
                        .append("\"").append(tmp.getResourceIdName()).append("\"")
                        .append(")")
                        .append(".get(")
                        .append(__Symbols.OBJ_RCLASS_LAYOUT)
                        .append(");\n");
            } else {
                sb.append(tmp.getResourceId())
                        .append(";\n");
            }
        }

        // setContentView findView inflateLayout
        sb.append("if(type == Finder.Activity){\n");
        if (contentView != null) {
            sb.append(Finder.Activity.setContentView("$$JinContentViewId"));
        }

        for (BindViewCode tmp : views) {
            sb.append(__Symbols.OBJ_TARGET).append(__Symbols.DOT_DIVIDER)
                    .append(tmp.getFieldName()).append(__Symbols.MATH_EQUAL);

            sb.append("(").append(tmp.getFieldType()).append(")");
            sb.append(Finder.Activity.findViewById(tmp.getResourceIdName()));
        }

        for (BindLayoutCode tmp : layouts) {
            sb.append(__Symbols.OBJ_TARGET).append(__Symbols.DOT_DIVIDER)
                    .append(tmp.getFieldName()).append(__Symbols.MATH_EQUAL);

            sb.append("(").append(tmp.getFieldType()).append(")");
            sb.append(Finder.Activity.inflateLayoutById(tmp.getResourceIdName()));
        }

        sb.append("\n} else if (type == Finder.View){\n");
        if (contentView != null) {
            sb.append(Finder.View.setContentView("$$JinContentViewId"));
        }

        for (BindViewCode tmp : views) {
            sb.append(__Symbols.OBJ_TARGET).append(__Symbols.DOT_DIVIDER)
                    .append(tmp.getFieldName()).append(__Symbols.MATH_EQUAL);

            sb.append("(").append(tmp.getFieldType()).append(")");
            sb.append(Finder.View.findViewById(tmp.getResourceIdName()));
        }

        for (BindLayoutCode tmp : layouts) {
            sb.append(__Symbols.OBJ_TARGET).append(__Symbols.DOT_DIVIDER)
                    .append(tmp.getFieldName()).append(__Symbols.MATH_EQUAL);

            sb.append("(").append(tmp.getFieldType()).append(")");
            sb.append(Finder.View.inflateLayoutById(tmp.getResourceIdName()));
        }
        sb.append("\n}\n");

        // onClick事件
        // View.OnClickListener onClickListener = new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //
        //     }
        // };
        sb.append("View.OnClickListener onClickListener = new View.OnClickListener() {\n")
                .append("@Override\n")
                .append("public void onClick(View v) {\n");
        sb.append("switch (v.getId()){\n");

        LinkedList<BindEventMethodCode.Parameter> onClickParameters = new LinkedList<>();
        BindEventMethodCode.Parameter onClickParam0 = new BindEventMethodCode.Parameter();
        onClickParam0.strParameterType = "android.view.View";
        onClickParam0.strParameterName = "v";
        onClickParameters.add(onClickParam0);

        for (BindClickCode tmp : onClicks) {
            if (tmp.getOnClickResourceIds() != null) {
                for (int id : tmp.getOnClickResourceIds()) {
                    sb.append("case ").append(id).append(":\n");
                    sb.append(tmp.getActionMethod().toUse(onClickParameters));
                    sb.append("break;");
                }
            } else if (tmp.getOnClickResourceIdNames() != null) {
                for (String idName : tmp.getOnClickResourceIdNames()) {
                    sb.append("case ").append(idName).append(":\n");
                    sb.append(tmp.getActionMethod().toUse(onClickParameters));
                    sb.append("break;");
                }
            }
        }

        sb.append("\n}\n");
        sb.append("\n}\n};\n");

        // setOnClickListener
        for (BindClickCode tmp : onClicks) {
            if (tmp.getOnClickResourceIds() != null) {
                sb.append("if (type == Finder.Activity){\n");
                for (int id : tmp.getOnClickResourceIds()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(id)
                            .append(")).setOnClickListener(")
                            .append("onClickListener")
                            .append(");\n");
                }
                sb.append("\n} else if(type == Finder.View){\n");
                for (int id : tmp.getOnClickResourceIds()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(id)
                            .append(")).setOnClickListener(")
                            .append("onClickListener")
                            .append(");\n");
                }
                sb.append("\n}");
            } else if (tmp.getOnClickResourceIdNames() != null) {
                sb.append("if (type == Finder.Activity){\n");
                for (String idName : tmp.getOnClickResourceIdNames()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(idName)
                            .append(")).setOnClickListener(")
                            .append("onClickListener")
                            .append(");\n");
                }
                sb.append("\n} else if(type == Finder.View){\n");
                for (String idName : tmp.getOnClickResourceIdNames()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(idName)
                            .append(")).setOnClickListener(")
                            .append("onClickListener")
                            .append(");\n");
                }
                sb.append("\n}");
            }
        }

        // onLongClick事件
        // View.OnLongClickListener onLongClickListener = new View.OnLongClickListener(){
        //     @Override
        //     public boolean onLongClick(View v) {
        //         return false;
        //     }
        // };
        sb.append("View.OnLongClickListener onLongClickListener = new View.OnLongClickListener(){\n")
                .append("@Override\n")
                .append("public boolean onLongClick(View v) {\n");
        sb.append("switch (v.getId()){\n");

        LinkedList<BindEventMethodCode.Parameter> onLongClickParameters = new LinkedList<>();
        BindEventMethodCode.Parameter onLongClickParam0 = new BindEventMethodCode.Parameter();
        onLongClickParam0.strParameterType = "android.view.View";
        onLongClickParam0.strParameterName = "v";
        onLongClickParameters.add(onLongClickParam0);

        for (BindLongClickCode tmp : onLongClicks) {
            if (tmp.getLongClickResourceIds() != null) {
                for (int id : tmp.getLongClickResourceIds()) {
                    sb.append("case ").append(id).append(":\n");
                    sb.append(tmp.getActionMethod().toUse(onLongClickParameters));
                    sb.append("break;");
                }
            } else if (tmp.getLongClickResourceIdNames() != null) {
                for (String idName : tmp.getLongClickResourceIdNames()) {
                    sb.append("case ").append(idName).append(":\n");
                    sb.append(tmp.getActionMethod().toUse(onClickParameters));
                    sb.append("break;");
                }
            }
        }

        sb.append("\n}\n");
        sb.append("return true;\n}\n};");

        // setOnLongClickListener
        for (BindClickCode tmp : onClicks) {
            if (tmp.getOnClickResourceIds() != null) {
                sb.append("if (type == Finder.Activity){\n");
                for (int id : tmp.getOnClickResourceIds()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(id)
                            .append(")).setOnLongClickListener(")
                            .append("onLongClickListener")
                            .append(");\n");
                }
                sb.append("\n} else if(type == Finder.View){\n");
                for (int id : tmp.getOnClickResourceIds()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(id)
                            .append(")).setOnLongClickListener(")
                            .append("onLongClickListener")
                            .append(");\n");
                }
                sb.append("\n}");
            } else if (tmp.getOnClickResourceIdNames() != null) {
                sb.append("if (type == Finder.Activity){\n");
                for (String idName : tmp.getOnClickResourceIdNames()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(idName)
                            .append(")).setOnLongClickListener(")
                            .append("onLongClickListener")
                            .append(");\n");
                }
                sb.append("\n} else if(type == Finder.View){\n");
                for (String idName : tmp.getOnClickResourceIdNames()) {
                    sb.append("(((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append(".findViewById(")
                            .append(idName)
                            .append(")).setOnLongClickListener(")
                            .append("onLongClickListener")
                            .append(");\n");
                }
                sb.append("\n}");
            }
        }
        // onItemClick事件
        // AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //
        //     }
        // };
        LinkedList<BindEventMethodCode.Parameter> onItemClickParameters = new LinkedList<>();

        BindEventMethodCode.Parameter onItemClickParam0 = new BindEventMethodCode.Parameter();
        onItemClickParam0.strParameterType = "android.widget.AdapterView";
        onItemClickParam0.strParameterName = "parent";
        onItemClickParameters.add(onItemClickParam0);

        BindEventMethodCode.Parameter onItemClickParam1 = new BindEventMethodCode.Parameter();
        onItemClickParam1.strParameterType = "android.view.View";
        onItemClickParam1.strParameterName = "view";
        onItemClickParameters.add(onItemClickParam1);

        BindEventMethodCode.Parameter onItemClickParam2 = new BindEventMethodCode.Parameter();
        onItemClickParam2.strParameterType = "int";
        onItemClickParam2.strParameterName = "position";
        onItemClickParameters.add(onItemClickParam2);

        BindEventMethodCode.Parameter onItemClickParam3 = new BindEventMethodCode.Parameter();
        onItemClickParam3.strParameterType = "long";
        onItemClickParam3.strParameterName = "id";
        onItemClickParameters.add(onItemClickParam3);

        int i = 0;
        for (BindItemClickCode tmp : onItemClicks) {
            if (tmp.getItemClickResourceIds() != null) {
                for (int id : tmp.getItemClickResourceIds()) {
                    sb.append("AdapterView.OnItemClickListener onItemClickListener").append(i)
                            .append("= new AdapterView.OnItemClickListener() {\n")
                            .append("@Override\n")
                            .append("public void onItemClick(AdapterView<?> parent, View view, int position, long id) {\n");
                    tmp.getActionMethod().toUse(onItemClickParameters);
                    sb.append("\n}\n};");

                    sb.append("if (type == Finder.Activity){\n")
                            .append("((android.widget.ListView)")
                            .append("((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append("findViewById(")
                            .append(id)
                            .append(")).setOnItemClickListener(")
                            .append("onItemClickListener").append(i)
                            .append(")")
                            .append("\n}");

                    sb.append("else if (type == Finder.View){\n")
                            .append("((android.widget.ListView)")
                            .append("((View)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append("findViewById(")
                            .append(id)
                            .append(")).setOnItemClickListener(")
                            .append("onItemClickListener").append(i)
                            .append(")")
                            .append("\n}");

                    i++;
                }
            } else if (tmp.getItemClickResourceIdNames() != null) {
                for (String idName : tmp.getItemClickResourceIdNames()) {
                    sb.append("AdapterView.OnItemClickListener onItemClickListener").append(i)
                            .append("= new AdapterView.OnItemClickListener() {\n")
                            .append("@Override\n")
                            .append("public void onItemClick(AdapterView<?> parent, View view, int position, long id) {\n");
                    tmp.getActionMethod().toUse(onItemClickParameters);
                    sb.append("\n}\n};");

                    sb.append("if (type == Finder.Activity){\n")
                            .append("((ListView)")
                            .append("((Activity)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append("findViewById(")
                            .append(idName)
                            .append(")).setOnItemClickListener(")
                            .append("onItemClickListener").append(i)
                            .append(")")
                            .append("\n}");

                    sb.append("else if (type == Finder.View){\n")
                            .append("((ListView)")
                            .append("((View)").append(__Symbols.OBJ_SOURCE).append(")")
                            .append("findViewById(")
                            .append(idName)
                            .append(")).setOnItemClickListener(")
                            .append("onItemClickListener").append(i)
                            .append(")")
                            .append("\n}");

                    i++;
                }
            }
        }

        // 方法结束
        sb.append("\n} catch (Exception e) {\n")
                .append("e.printStackTrace();\n}\n}\n");

        sb.append(__Symbols.CODE_END);
        return sb.toString();
    }
}
