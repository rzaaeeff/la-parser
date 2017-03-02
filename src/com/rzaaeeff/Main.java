package com.rzaaeeff;

import com.rzaaeeff.core.Parser;
import com.rzaaeeff.core.model.SubstringModel;
import com.rzaaeeff.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File sourceFile = new File("SourceCode.java");

        try {
            String code = FileUtils.read(sourceFile);
            String parsedCode = Parser.removeSingleLineComments(code);

            System.out.println("Source Code:\n" + code);
            System.out.println("\nParsed Source Code:\n" + parsedCode);

            // You may find extracted comments in ParserLog.txt file

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
