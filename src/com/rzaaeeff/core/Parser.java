package com.rzaaeeff.core;

import com.rzaaeeff.core.Internals;

/**
 * Created by Rzaaeeff on 2/24/2017.
 */
public class Parser {

    private static String removeRange(String content, int start, int end) {
        StringBuffer buffer = new StringBuffer(content);
        return buffer.delete(start, end).toString();
    }

    private static String removeSingleLineComment(String content) {
        if (content == null)
            throw new NullPointerException();

        int start = content.indexOf(Internals.Comment.SINGLE_LINE);
        int end = content.indexOf(System.lineSeparator(), start);
        return removeRange(content, start, end);
    }

    public static String removeSingleLineComments(String content) {
        while (content.contains(Internals.Comment.SINGLE_LINE)) {
            content = removeSingleLineComment(content);
        }

        return content;
    }

    public static String removeAllComments(String content) {
        return removeSingleLineComments(content);
    }
}