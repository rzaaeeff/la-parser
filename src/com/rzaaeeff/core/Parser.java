package com.rzaaeeff.core;

import com.rzaaeeff.core.model.FieldModel;
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
    private static final String PATTERN_SPLIT_WITHOUT_STRINGS = "( (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$))";
    public static final File LOG_FILE = new File("ParserLog.txt");

    private static String removeRange(String content, int start, int end) {
        StringBuffer buffer = new StringBuffer(content);
        return buffer.delete(start, end).toString();
    }

    private static SubstringModel findSingleLineComment(String content, int startPosition) {
        int start = content.indexOf(Internals.Comment.SINGLE_LINE, startPosition);
        int end = content.indexOf(System.lineSeparator(), start);

        return new SubstringModel(start, end);
    }

    private static SubstringModel findMultiLineComment(String content, int startPosition) {
        int start = content.indexOf(Internals.Comment.MULTI_LINE_START, startPosition);
        int end = content.indexOf(Internals.Comment.MULTI_LINE_END, start);

        return new SubstringModel(start, end + 2);
    }

    public static String removeSingleLineComments(String content) {
        int index = 0;
        StringBuilder log = new StringBuilder("\nExtracted single-line comments:\n");

        do {
            SubstringModel comment = findSingleLineComment(content, index);

            if (comment.start() < 0) {
                break;
            }

            if (StringUtils.isMultiLineComment(content, comment.start()) ||
                    StringUtils.isString(content, comment.start())) {
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
            FileUtils.append(LOG_FILE, log.toString());
        } catch (Exception exc) {
        }

        return content;
    }

    public static String removeMultiLineComments(String content) {
        int index = 0;
        StringBuilder log = new StringBuilder("\nExtracted multi-line comments:\n");

        do {
            SubstringModel comment = findMultiLineComment(content, index);

            if (comment.start() < 0) {
                break;
            }

            if (StringUtils.isSingleLineComment(content, comment.start()) ||
                    StringUtils.isString(content, comment.start())) {
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
            FileUtils.append(LOG_FILE, log.toString());
        } catch (Exception exc) {
        }

        return content;
    }

    public static String removeComments(String content) {
        content = removeSingleLineComments(content);
        content = removeMultiLineComments(content);

        return content;
    }

    private static List<FieldModel> _getFieldsByType(String content, Internals.Type type) {
        List<FieldModel> fields = new ArrayList<>();
//        int startType, startName, startValue, startLine;
//        int endType, endName, endValue, endLine;
//
//        for (String keyword : type.getKeywords()) {
//            keyword += ' ';
//            while (content.contains(keyword)) {
//                startLine = content.indexOf(keyword);
//
//                if (startLine != 0)
//                    startLine = content.indexOf(';' + keyword);
//
//                if (startLine == -1)
//                    startLine = content.indexOf(' ' + keyword);
//
//                if (startLine == -1)
//                    startLine = content.indexOf(System.lineSeparator() + keyword);
//
//                if (startLine == -1)
//                    break;
//
//                endLine = content.indexOf(';', startLine);
//
//                if (endLine != -1) {
//                    startType = startLine;
//                    endType = content.indexOf(' ', startType);
//
//                    if (endType != -1) {
//
//                        FieldModel field = new FieldModel();
//
//                        startName = endType + 1;
//                        endName = content.indexOf('=', startName);
//
//                        if (endName > endLine)
//                            endName = -1;
//
//                        if (endName == -1) {
//                            // we don't have value
//                            endName = endLine;
//                            field.setName(content.substring(startName, endName));
//                            field.setType(type);
//                            fields.add(field);
//                        } else {
//                            field.setName(content.substring(startName, endName));
//
//                            startValue = endName + 1;
//                            endValue = endLine;
//
//                            // Type name = value; is valid
//                            // Type name =; is not valid
//                            // Let's check it
//                            if (startValue < endLine) {
//                                String value = content.substring(startValue, endValue);
//
//                                value = value.replaceAll("\"", "");
//                                value = value.replaceAll("'", "");
//
//                                field.setValue(value);
//                                field.setType(type);
//                                fields.add(field);
//                            }
//                        }
//
//                        content = removeRange(content, startLine, endLine + 1); // +1 for semicolon
//                    } else {
//                        // it's not valid keyword
//                        content = removeRange(content, startType, startType + keyword.length());
//                    }
//                } else {
//                    // line doesn't end with semicolon
//                    content = removeRange(content, startLine, startLine + keyword.length());
//                }
//            }
//        }
//
        String[] tokens = content.split(PATTERN_SPLIT_WITHOUT_STRINGS);
        String[] keywords = type.getKeywords();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(keywords[0]) || tokens[i].equals(keywords[keywords.length - 1])) {
                try {
                    // found variable type
                    FieldModel field = new FieldModel();
                    field.setType(type);
                    field.setName(tokens[i + 1]);

                    if (tokens[i + 2].equals("=")) {
                        // we have value
                        field.setValue(tokens[i + 3]);
                    } else {
                        // we don't have value
                        field.setValue(null);
                    }

                    fields.add(field);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    continue;
                }
            }
        }

        return fields;
    }

    public static List<FieldModel> getFieldsByType(String content, Internals.Type type) {
        content = StringUtils.cleanCode(content);

        return _getFieldsByType(content, type);
    }

    public static List<FieldModel> getFields(String content) {
        List<FieldModel> fields = new ArrayList<>();

        content = StringUtils.cleanCode(content);

        for (Internals.Type type : Internals.Type.values()) {
            fields.addAll(_getFieldsByType(content, type));
        }

        return fields;
    }

    public static void run(String code) {
        code = removeComments(code);
        code = StringUtils.cleanCode(code);

        List<FieldModel> fields = new ArrayList<>();
        for (Internals.Type type : Internals.Type.values()) {
            fields.addAll(_getFieldsByType(code, type));
        }

        String[] tokens = code.split(PATTERN_SPLIT_WITHOUT_STRINGS);

        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case Internals.Functions.PRINT:
                    System.out.print(StringUtils.trimQuotes(tokens[i+2]));
                    break;
                case Internals.Functions.PRINTLN:
                    System.out.println(StringUtils.trimQuotes(tokens[i+2]));
                    break;
            }
        }
    }
}