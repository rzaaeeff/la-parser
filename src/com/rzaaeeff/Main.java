package com.rzaaeeff;

import com.rzaaeeff.core.Parser;
import com.rzaaeeff.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String PATH = "";
    private static final String FILENAME = "SourceCode";
    private static final String EXTENSION = Constants.Extension.E1;

    public static void main(String[] args) {
        File file = new File(PATH + FILENAME + "." + EXTENSION);

        try {
//            FileUtils.write(file, "test\ntest");
            String code = FileUtils.read(file);
            System.out.println(Parser.removeAllComments(code));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
