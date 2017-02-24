package com.rzaaeeff.util;

import java.io.*;

/**
 * Created by Rzaaeeff on 2/24/2017.
 */
public class FileUtils {
    public static String read(File file) throws IOException {
        if (file == null)
            throw new NullPointerException();

        StringBuilder content = new StringBuilder();
        String line;

        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }

    public static void write(File file, String content) throws IOException {
        if (file == null || content == null)
            throw new NullPointerException();


        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
