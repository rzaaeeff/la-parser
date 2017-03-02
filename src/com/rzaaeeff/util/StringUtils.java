package com.rzaaeeff.util;

/**
 * Created by Rzaaeeff on 3/1/2017.
 */
public class StringUtils {
    public static boolean isString(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException("Position cannot be negative.");

        boolean isString = false;

        for (int i = 0; i < position; i++)
            if (content.toCharArray()[i] == '"')
                isString = !isString;

        return isString;
    }

    public static boolean isChar(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException("Position cannot be negative.");

        if (position < 1)
            return false;

        if (content.toCharArray()[position - 1] == '\'')
            return true;

        return false;
    }

    public static int countOf(String content, String substring) {
        return content.length() - content.replace(substring, "").length();
    }
}
