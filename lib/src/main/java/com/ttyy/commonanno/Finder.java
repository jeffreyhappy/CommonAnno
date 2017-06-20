package com.ttyy.commonanno;

/**
 * author: admin
 * date: 2017/06/20
 * version: 0
 * mail: secret
 * desc: Finder
 */

public enum Finder {

    Activity {
        @Override
        public String setContentView(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            //  ((Activity)source).setContentView(2130903174)
            sb.append("((Activity)").append(__Symbols.OBJ_SOURCE).append(")").append(__Symbols.DOT_DIVIDER)
                    .append("setContentView(").append(idFieldName).append(");\n");

            return sb.toString();
        }

        @Override
        public String findViewById(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            sb.append("((Activity)").append(__Symbols.OBJ_SOURCE).append(")").append(__Symbols.DOT_DIVIDER)
                    .append("findViewById(").append(idFieldName).append(");\n");

            return sb.toString();
        }

        @Override
        public String inflateLayoutById(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            sb.append("LayoutInflater.from(")
                    .append("(Activity)").append(__Symbols.OBJ_SOURCE).append(").inflate(")
                    .append(idFieldName).append(__Symbols.PARAM_DIVIDER)
                    .append("null);\n");

            return sb.toString();
        }
    },

    View {
        @Override
        public String setContentView(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            //  LayoutInflater.from(((View)source).getContext()).inflate(2130903174, (ViewGroup)source);
            sb.append("LayoutInflater.from(")
                    .append("((View)").append(__Symbols.OBJ_SOURCE).append(").getContext()).")
                    .append("inflate(")
                    .append(idFieldName)
                    .append(__Symbols.PARAM_DIVIDER)
                    .append("(ViewGroup)").append(__Symbols.OBJ_SOURCE).append(");\n");

            return sb.toString();
        }

        @Override
        public String findViewById(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            sb.append("((View)").append(__Symbols.OBJ_SOURCE).append(")").append(__Symbols.DOT_DIVIDER)
                    .append("findViewById(").append(idFieldName).append(");\n");

            return sb.toString();
        }

        @Override
        public String inflateLayoutById(String idFieldName) {
            StringBuilder sb = new StringBuilder();

            sb.append("LayoutInflater.from(")
                    .append("((View)").append(__Symbols.OBJ_SOURCE).append(").getContext())").append(".inflate(")
                    .append(idFieldName).append(__Symbols.PARAM_DIVIDER)
                    .append("null);\n");

            return sb.toString();
        }
    };

    public abstract String setContentView(String idFieldName);

    public abstract String findViewById(String idFieldName);

    public abstract String inflateLayoutById(String idFieldName);

}
