package com.rzaaeeff;

import com.rzaaeeff.core.Parser;
import com.rzaaeeff.core.model.FieldModel;
import com.rzaaeeff.util.FileUtils;
import com.rzaaeeff.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File sourceFile = new File("SourceCode.java");

        try {
            if (Parser.LOG_FILE.exists())
                Parser.LOG_FILE.delete();
            Parser.LOG_FILE.createNewFile();

            String code = FileUtils.read(sourceFile);
//            String rawCode = Parser.removeComments(code);
//
//            System.out.println("Source Code:\n" + code);
//            System.out.println("\nParsed Source Code:\n" + rawCode);
//
//            List<FieldModel> fields = Parser.getFields(rawCode);
//
//            System.out.println("\n\nFields:");
//            for (FieldModel field : fields) {
//                System.out.println(field.getType().toString()+ " "
//                        + field.getName() + " "
//                        + field.getValue());
//            }

            Parser.run(code);

            // You may find extracted comments in ParserLog.txt file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
