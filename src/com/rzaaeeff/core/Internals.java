package com.rzaaeeff.core;

import com.rzaaeeff.core.model.FieldModel;

/**
 * Created by Rzaaeeff on 2/24/2017.
 */
public class Internals {
    public class Error {
        public static final String NEGATIVE_POSITION = "Position cannot be negative.";
    }

    public class Comment {
        public static final String SINGLE_LINE = "//";
        public static final String MULTI_LINE_START = "/*";
        public static final String MULTI_LINE_END = "*/";
    }

    public enum Type {
        BOOLEAN("boolean", "Boolean"),
        BYTE("byte", "Byte"),
        SHORT("short", "Short"),
        INTEGER("int", "Integer"),
        FLOAT("float", "Float"),
        DOUBLE("double", "Double"),
        CHARACTER("char", "Character"),
        STRING("String");

        private String[] keywords;

        Type(String... keywords) {
            this.keywords = new String[keywords.length];

            for (int i = 0; i < keywords.length; i++)
                this.keywords[i] = keywords[i];
        }

        public String[] getKeywords() {
            return keywords;
        }

        public String toString() {
            return keywords[keywords.length - 1];
        }

        public Class toClass() {
            try {
                return Class.forName("java.lang." + toString());
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        public Object newInstance() {
            try {
                return toClass().newInstance();
            } catch (Exception exc) {
                return  null;
            }
        }
    }

    public enum Modifier {
        PUBLIC("public"),
        PROTECTED("protected"),
        PRIVATE("private");

        String[] keywords;

        Modifier(String... keywords) {
            this.keywords = new String[keywords.length];

            for (int i = 0; i < keywords.length; i++)
                this.keywords[i] = keywords[i];
        }

        public String[] getKeywords() {
            return keywords;
        }
    }

    public static final class Functions {
        public static final String PRINT = "System.out.print";
        public static final String PRINTLN = "System.out.println";
    }
}
