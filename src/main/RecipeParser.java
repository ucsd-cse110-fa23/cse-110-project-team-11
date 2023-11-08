package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RecipeParser{
    private String TITLE;
    private List<String> INGREDIENTS = new ArrayList<>();
    private List<String> STEPS = new ArrayList<>();

    // getter methods for private variables
    public String getTitle() {
        return TITLE;
    }
    public List<String> getIngredients() {
        return INGREDIENTS;
    }
    public List<String> getSteps() {
        return STEPS;
    }

    public void parse() throws IOException, InterruptedException {
        FileReader fr
        = new FileReader("recipe.txt"); // reads recipes text created by RecipeCreator
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            String line = br.readLine();
            if (!line.isEmpty()){ // checks for non-empty line
                char first = line.charAt(0);
                switch(first) {
                    case 'T': // Title
                        TITLE = line.substring(7); // start of recipe title
                        break;
                    case '-': // Ingredients
                        line = line.substring(1); // gets rid of dash
                        first = line.charAt(0); // check for space
                        if (first == ' ') {
                            line = line.substring(1); // gets rid of space
                        }
                        INGREDIENTS.add(line);
                        break;
                    case '#': // Steps
                        line = line.substring(3); // start of step
                        STEPS.add(line);
                }
            }
        }
        br.close();
        // System.out.println(TITLE);
        // System.out.println();
        // for(String s : INGREDIENTS) {
        //     System.out.println(s);
        // }
        // System.out.println();
        // for(String s : STEPS) {
        //     System.out.println(s);
        // }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        RecipeParser parser = new RecipeParser();
        parser.parse();
    }
}
