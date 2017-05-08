package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class FileIO {
    
    public static String readLine(String pathString, int lineNumber) {
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

    public static void readConfig(String pathString, 
            ArrayList<Ingredient> ingredients, ArrayList<Meal> meals) {
        Path path = Paths.get(pathString);
        Charset charset = Charset.forName("ISO-8859-1");
        //Code cleanup and better error handling needed       

        HashMap<String, Integer> ingredientIndices = new HashMap<>();
        
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("ingredient")) {
                    String[] words = currentLine.split(" ");
                    
                    if (words.length != 3) {
                        System.err.println("Config file invalid");
                        return;
                    }

                    ingredients.add(new Ingredient(words[1],
                            Double.parseDouble(words[2])));
                    ingredientIndices.put(words[1], ingredientIndices.size());
                } else if (currentLine.startsWith("meal")) {
                    String[] words = currentLine.split(" ");
                    words[1] = words[1].replaceAll("_", " ");
                    
                    if (words.length < 4) {
                        System.err.println("Config file invalid");
                        return;
                    }
                    
                    Meal meal = new Meal(words[1],
                            Double.parseDouble(words[2]),
                            Integer.parseInt(words[3]));

                    for (int i = 5; i < words.length; i += 2) {
                        meal.addIngredient(ingredients.get(
                                ingredientIndices.get(words[i - 1])),
                                Integer.parseInt(words[i]));
                    }

                    meals.add(meal);
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        } 
    }
}
