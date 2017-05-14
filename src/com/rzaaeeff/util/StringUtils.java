package com.rzaaeeff.util;

import com.rzaaeeff.core.Internals;

/**
 * Created by Rzaaeeff on 3/1/2017.
 */
public class StringUtils {
    public static boolean isString(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException(Internals.Error.NEGATIVE_POSITION);

        content = content.replaceAll("\\\\\"", "");

        boolean isString = false;

        for (int i = 0; i < position; i++)
            if (content.toCharArray()[i] == '"')
                isString = !isString;

        return isString;
    }

    public static boolean isChar(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException(Internals.Error.NEGATIVE_POSITION);

        if (position < 1)
            return false;

        content = content.replaceAll("\\\\'", "");

        if (content.toCharArray()[position - 1] == '\'')
            return true;

        return false;
    }

    public static boolean isMultiLineComment(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException(Internals.Error.NEGATIVE_POSITION);

        if (position < 2)
            return false;

        boolean isMultiLineComment = false;

        for (int i = 0; i < position - 1; i++) {
            if (content.substring(i, i + 2).equals(Internals.Comment.MULTI_LINE_START))
                isMultiLineComment = true;
            else if (content.substring(i, i + 2).equals(Internals.Comment.MULTI_LINE_END))
                isMultiLineComment = false;
        }

        return isMultiLineComment;
    }

    public static boolean isSingleLineComment(String content, int position) {
        if (position < 0)
            throw new IllegalArgumentException(Internals.Error.NEGATIVE_POSITION);

        if (position < 2)
            return false;

        boolean isSingleLineComment = false;

        for (int i = 0; i < position - 1; i++) {
            if (content.substring(i, i + 2).equals(Internals.Comment.SINGLE_LINE))
                isSingleLineComment = true;
            else if (content.substring(i, i + System.lineSeparator().length()).
                    equals(System.lineSeparator()))
                isSingleLineComment = false;
        }

        return isSingleLineComment;
    }

    public static String cleanCode(String content) {
        content = StringUtils.realTrim(content);
        content = content.replaceAll(" *= *", " = ");
        content = content.replaceAll(" *; *", " ;");
        content = content.replaceAll(" *, *", " , ");
        String separator = System.lineSeparator();
        content = content.replaceAll(" *" + separator + " *", " " + separator + " ");
        content = content.replaceAll(" *\\( *", " \\( ");
        content = content.replaceAll(" *\\) *", " \\) ");

        return content;
    }

    public static String trimQuotes(String content) {
        StringBuilder builder = new StringBuilder(content);

        if (builder.charAt(0) == '"') {
            builder.deleteCharAt(0);

            if (builder.charAt(builder.length() - 1) == '"')
                builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    public static String realTrim(String content) {
        return content.replaceAll(" +", " ").trim();
    }

    public static int countOf(String content, String substring) {
        return content.length() - content.replace(substring, "").length();
    }
}
