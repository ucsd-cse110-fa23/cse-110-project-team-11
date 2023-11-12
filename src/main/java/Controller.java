import javafx.event.ActionEvent;

public class Controller {
    
    private Input input;
    private RecipeCreator rc;

    public Controller(Input input, RecipeCreator rc) {
        this.input = input;
        this.rc = rc;
    }

    private void handleStopButton(ActionEvent event) {
        // Stop Button

        promptType = input.getPromptType();
        
        if(Input.stopCapture(promptType)){
            if(promptType.equals("MealType")){
                promptType = "Ingredients";
                recipeText.setText("");
                recordingLabel.setText("Please input Ingredients");
            }
            else{
                recordingLabel.setText("Recipe Displayed");
                RecipeDisplay rec = new RecipeDisplay();
                promptType = "MealType";
                try {
                    RecipeParser.parse(); 
                    rec.setTitle(RecipeParser.getTitle());
                    rec.setIngreds(RecipeParser.getStringIngredients());
                    rec.setSteps(RecipeParser.getStringSteps());
                    System.out.println(rec.getIngredients().getText());
                    System.out.println(rec.getSteps().getText());
                    RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(rec);
                    UI.getRoot().setCenter(displayRec);
                    UI.getRoot().setTop(displayRec.getRecipeDisplayHeader());
                    // recipeText.setText(text);
                    // br.close();
                } catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
        else{
            recordingLabel.setText("Invalid Input. Please say a proper meal type");
        }
        
    }

}