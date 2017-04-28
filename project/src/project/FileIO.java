package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    
    public static String readLine(int lineNumber, String pathString) {
        Path path = Paths.get(pathString);
        Charset charset = Charset.forName("ISO-8859-1");

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String currentLine;
            int currentLineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    return currentLine;
                }
               
                currentLineNumber++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return null;
    }
}
