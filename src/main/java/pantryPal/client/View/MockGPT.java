public class MockChatGPT {

    /**
     * Get ingredient list from prompt
     */
    public static List<String> parseList(String prompt) {
        StringTokenizer st = new StringTokenizer(prompt, " ");
        List<String> ingredientList = new ArrayList<String>();
        while(st.hasMoreTokens()) {
            ingredientList.add(st.nextToken());
        }
        return ingredientList;
    }
    
    /**
     * Generate placeholder response from list of ingredients
     */
    public static String generateResponse(List<String> list) {

        // Generate title
        String response = "";
        String first = list.get(0);
        String last = list.get(list.size()-1);
        String title = "Title: " + first + " and " + last + " dish";
        
        response+=title +"\n\n";
        
        // Generate ingredient list
        String ingredients = "Ingredients: ";
        for(String ingred:list) {
            ingredients += ingred + ", ";
        }
        ingredients = ingredients.substring(0, ingredients.length()-2);
        
        response += ingredients + "\n\n";
        
        // Generate steps
        String steps = "Steps: [steps]";
        response += steps;
        return response;
        
        
    }
    
    // Receives prompt and returns response
    public static String call (String prompt) {
        List<String> ingreds = parseList(prompt);
        String response = generateResponse(ingreds);
        System.out.println(response);
        return response;

    }