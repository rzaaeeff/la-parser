package com.rzaaeeff.core;

import com.rzaaeeff.core.model.SubstringModel;
import com.rzaaeeff.util.FileUtils;
import com.rzaaeeff.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rzaaeeff on 2/24/2017.
 */
public class Parser {
    private static final File LOG_FILE = new File("ParserLog.txt");

    private static String removeRange(String content, int start, int end) {
        StringBuffer buffer = new StringBuffer(content);
        return buffer.delete(start, end).toString();
    }

    private static SubstringModel findSingleLineComment(String content, int startPosition) {
        int start = content.indexOf(Internals.Comment.SINGLE_LINE, startPosition);
        int end = content.indexOf(System.lineSeparator(), start);

        return new SubstringModel(start, end);
    }

    public static String removeSingleLineComments(String content) {
        int index = 0;
        StringBuilder log = new StringBuilder("Extracted single-line comments:\n");

        do {
            SubstringModel comment = findSingleLineComment(content, index);

            if (comment.start() < 0) {
                break;
            }

            if (StringUtils.isString(content, comment.start())) {
                index = comment.start() + 1;
                continue;
            } else {
                log.append(content.substring(comment.start(), comment.end())
                        + System.lineSeparator());
                content = removeRange(content, comment.start(), comment.end());
                index = comment.start();
            }
        } while (index < content.length());

        try {
            FileUtils.write(LOG_FILE, log.toString());
        } catch (Exception exc) {}

        return content;
    }
}