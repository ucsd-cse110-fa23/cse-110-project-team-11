package pantryPal.client;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecButtons;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.UI;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class Controller {

    private Input input = new Input();
    private RecipeCreator rc = new RecipeCreator();
    private InputAppFrame inputFrame;
    private RecipeParser rp = new RecipeParser();
    private LoginPageAppFrame lp;
    private UI ui;
    private HomePageAppFrame hp;
    private RecipeDisplayAppFrame rd;
    private RecipeTitle rt = new RecipeTitle("");
    private Model model;

    public Controller(UI ui, Model model) {
        this.model = model;
        this.ui = ui;
        this.inputFrame = ui.getInputPage();
        this.hp = ui.getHomePage();
        this.rd = ui.getDisplayPage(); 
        this.lp = ui.getLoginPage();       
        
        this.inputFrame.setStartButtonAction(this::handleStartButton);
        this.inputFrame.setStopButtonAction(event -> {
            try {
                handleStopButton(event);
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        this.inputFrame.setBackButtonAction(this::handleBackButton);
        this.rd.setBackButtonAction2(this::handleBackButton2);
        this.rd.setDeleteButtonAction(this::handleDeleteButton);
        this.hp.setCreateButtonAction(this::handleCreateButton);
        this.rd.setSaveButtonAction(this::handleSaveButton);
        this.rd.setEditButtonAction(this::handleEditButton);
        this.rt.setViewButtonAction(this::handleViewButton);
        this.rd.setRegenerateButtonAction(event -> {
            try {
                handleRegenerateButton(event);
            } catch (InterruptedException | IOException  e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        this.lp.setLoginButtonAction(this::handleLoginButton);
        this.lp.setCreateAccButtonAction(this::handleCreateAccButton);
        this.inputFrame.setLogoutButtonAction(this::handleLogoutButton2);
        this.rd.setLogoutButtonAction(this::handleLogoutButton);
        this.hp.setLogoutButtonAction(this::handleLogoutButton);
        
    }

    public void handleCreateButton(ActionEvent event) {
        ui.getRoot().setCenter(inputFrame);
        ui.getRoot().setTop(inputFrame.getReturnHeader());
    }

    public void handleStartButton(ActionEvent event) {
        RecButtons rb = inputFrame.getRecButtons();
        rb.setRecipeText("Recording");
        input.captureAudio();
        inputFrame.getRecButtons().getButtonBox().getChildren().remove(inputFrame.getRecButtons().getStartButton());
        inputFrame.getRecButtons().getButtonBox().getChildren().add(inputFrame.getRecButtons().getStopButton());
    }
    // TODO auto stop when press back

    public void handleStopButton(ActionEvent event) throws InterruptedException, IOException {
        // Stop Button

        String promptType = input.getPromptType();
        inputFrame.getRecButtons().getButtonBox().getChildren().remove(inputFrame.getRecButtons().getStopButton());
        inputFrame.getRecButtons().getButtonBox().getChildren().add(inputFrame.getRecButtons().getStartButton());
        if(input.stopCapture(promptType)){
            
            if(promptType.equals("MealType")){
                inputFrame.getRecButtons().setRecipeText("Please input Ingredients.\n\nMeal Type: " + input.getType());
                input.setPromptType("Ingredients");
            }
            else{
                
                inputFrame.getRecButtons().setRecipeText("Recipe Displayed");
                RecipeDisplay rec = new RecipeDisplay();
                input.setPromptType("MealType");
                rc.generateRecipe();
                try {
                    rp.parse(); 
                    rec.setID(null);
                    rec.setTitle(rp.getTitle());
                    rec.setIngreds(rp.getStringIngredients());
                    rec.setSteps(rp.getStringSteps());
                    System.out.println(rec.getIngredients().getText());
                    System.out.println(rec.getSteps().getText());
                    System.out.println("SDUHFIOSDHFIOSHDOFHSDIOFHSDIOFSIDHFOSDIFHSODi");
                    RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(rec);
                    displayRec.setBackButtonAction2(this::handleBackButton2);
                    displayRec.setLogoutButtonAction(this::handleLogoutButton);
                    displayRec.setDeleteButtonAction(this::handleDeleteButton);
                    displayRec.setSaveButtonAction(this::handleSaveButton);
                    displayRec.setEditButtonAction(this::handleEditButton);
                    displayRec.setRegenerateButtonAction(ev -> {
                        try {
                            handleRegenerateButton(ev);
                        } catch (InterruptedException | IOException  e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    });
                    this.rd = displayRec;
                                
                    ui.setDisplayPage(displayRec);
                    ui.getRoot().setCenter(displayRec);
                    ui.getRoot().setTop(displayRec.getRecipeDisplayHeader());
                    // recipeText.setText(text);
                    // br.close();
                } catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
        else{
            
            inputFrame.getRecButtons().setRecipeText("Invalid Input. Please say a proper meal type.\n\nTranscription: " + input.getTranscription());
        }
        
        
    }

    private void handleBackButton(ActionEvent event){
        ui.returnHomePage();   
        resetInput();
    }

    private void handleBackButton2(ActionEvent event){
        ui.returnHomePage();   
        input.setPromptType("MealType"); 
        inputFrame.getRecButtons().setRecipeText("Select Meal Type: Breakfast, Lunch, or Dinner");    
        if(this.rd.getEditable()){
            RecipeDisplayAppFrame r = this.rd;
            TextArea ingredients = r.getIngredients();
            Button editButton = r.getEditButton();
            TextArea steps = r.getSteps();
            ingredients.setEditable(false);
            steps.setEditable(false);
            ImageView editImage = new ImageView(new Image("file:graphics/e2.png"));
            editImage.setPreserveRatio(true);
            editImage.setFitHeight(25);
            editImage.setFitWidth(45);
            editButton.setGraphic(editImage);
            r.setEditable(false);
            reload();
            ui.returnHomePage();
        }
    }

    private void handleEditButton(ActionEvent event) {
        boolean editable = rd.getEditable();
        TextArea ingredients = rd.getIngredients();
        Button editButton = rd.getEditButton();
        TextArea steps = rd.getSteps();
        if (!editable) {
                ingredients.setEditable(true);
                steps.setEditable(true);
                ImageView editImage = new ImageView(new Image("file:graphics/st2.png"));
                editImage.setPreserveRatio(true);
                editImage.setFitHeight(25);
                editImage.setFitWidth(45);
                editButton.setGraphic(editImage);
                rd.setEditable(true);
            }
        else {
            ingredients.setEditable(false);
            steps.setEditable(false);
            ImageView editImage = new ImageView(new Image("file:graphics/e2.png"));
            editImage.setPreserveRatio(true);
            editImage.setFitHeight(25);
            editImage.setFitWidth(45);
            editButton.setGraphic(editImage);
            rd.setEditable(false);
        }
    }

    public void handleSaveButton(ActionEvent event) {
        rd.getIngredients().setEditable(false);
        rd.getSteps();
        if (rd.getID() == null) { // if does not exist in MongoDB 
            // System.out.println("HANDLE SAVE BUTTON (CONTROLLER)");
            String stringID = rd.getID();
            String title = rd.getTitle().getText();
            String ingredients = rd.getIngredients().getText();
            String steps = rd.getSteps().getText();
            model.performRequest("PUT", stringID, title, ingredients, steps);
            
            RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps);
            RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
            RecipeTitle recipeDis = new RecipeTitle(stringID, title, rec);
            rd.setID(RecipeManager.getStringID());
            recipeDis.setViewButtonAction(this::handleViewButton);
            recipeDis.getRecipeDetail().setBackButtonAction2(this::handleBackButton2);
            recipeDis.getRecipeDetail().setLogoutButtonAction(this::handleLogoutButton);
            this.rt = recipeDis;
            hp.getRecipeList().getChildren().add(recipeDis);
            reload();
        }
        else {
            try {
                RecipeManager.updateRecipe(rd.getTitle().getText(), rd.getIngredients().getText(), rd.getSteps().getText(), rd.getID());
            } catch (IOException e1) {
                
                e1.printStackTrace();
            }
        }
        this.rd.getRecipe().getSaveButton().setStyle("-fx-background-color: #5DBB63; -fx-border-width: 0;");
        PauseTransition pause = new PauseTransition(
            Duration.seconds(1)
        );
        pause.setOnFinished(e2 -> {
            this.rd.getRecipe().getSaveButton().setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        });
        pause.play();
    }

    private void handleViewButton(ActionEvent event){
        ui.getRoot().setCenter(this.rt.getRecipeDetail()); 
        ui.getRoot().setTop(this.rt.getRecipeDetail().getRecipeDisplayHeader());
    }

    private void handleDeleteButton(ActionEvent event) {
        //System.out.println("HELLOOO");
        String stringID = rd.getID();
        model.performRequest("DELETE", stringID, null, null, null);
        reload();
        ui.returnHomePage();
    }

    private void handleRegenerateButton(ActionEvent event) throws IOException, InterruptedException { 

        RecipeDisplay rec = new RecipeDisplay();
        input.setPromptType("MealType");
        rc.generateRecipe();
        try {
            rp.parse(); 
            rec.setID(null);
            rec.setTitle(rp.getTitle());
            rec.setIngreds(rp.getStringIngredients());
            rec.setSteps(rp.getStringSteps());
            System.out.println(rec.getIngredients().getText());
            System.out.println(rec.getSteps().getText());
            System.out.println("SDUHFIOSDHFIOSHDOFHSDIOFHSDIOFSIDHFOSDIFHSODi");
            RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(rec);
            displayRec.setBackButtonAction2(this::handleBackButton2);
            displayRec.setLogoutButtonAction(this::handleLogoutButton);
            displayRec.setDeleteButtonAction(this::handleDeleteButton);
            displayRec.setSaveButtonAction(this::handleSaveButton);
            displayRec.setEditButtonAction(this::handleEditButton);
            displayRec.setRegenerateButtonAction(ev -> {
                try {
                    handleRegenerateButton(ev);
                } catch (InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            this.rd = displayRec;
                        
            ui.setDisplayPage(displayRec);
            ui.getRoot().setCenter(displayRec);
            ui.getRoot().setTop(displayRec.getRecipeDisplayHeader());
        } catch(Exception err){
            err.printStackTrace();
        }
    }

    private void handleLoginButton(ActionEvent event){

        ui.returnHomePage(); 
    }

    private void handleCreateAccButton(ActionEvent event){

        ui.returnHomePage(); 
    }

    private void handleLogoutButton(ActionEvent event){

        System.out.println("TESTTESTSTESTSETSETSET");
        ui.setLoginPage();
    }
    private void handleLogoutButton2(ActionEvent event){

        System.out.println("from i");
        ui.setLoginPage();
        resetInput();
    }

    public void reload(){
        hp.getRecipeList().getChildren().removeIf(RecipeTitle -> RecipeTitle instanceof RecipeTitle && true);  
        loadRecipes(); // loads recipe
    }

    private void resetInput(){
        input.setPromptType("MealType"); 
        inputFrame.getRecButtons().setRecipeText("Select Meal Type: Breakfast, Lunch, or Dinner");  
        if(input.getMic() != null){
            input.getMic().stop();
            input.getMic().close();
        }  
        if (inputFrame.getRecButtons().getButtonBox().getChildren().contains(inputFrame.getRecButtons().getStopButton())){
            System.out.println("TEST");
            inputFrame.getRecButtons().getButtonBox().getChildren().remove(inputFrame.getRecButtons().getStopButton());
            inputFrame.getRecButtons().getButtonBox().getChildren().add(inputFrame.getRecButtons().getStartButton());
        }
    }

    public void loadRecipes(){
        hp.getRecipeList().getChildren().removeIf(RecipeTitle -> RecipeTitle instanceof RecipeTitle && true); 
        ArrayList<String[]> recipes = RecipeManager.loadRecipes();
        
        for(int i = 0; i < recipes.size(); i++){
            String stringID = recipes.get(i)[0];
            String title = recipes.get(i)[1];
            String ingredients = recipes.get(i)[2];
            String steps = recipes.get(i)[3];
            RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps);
            RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
            RecipeTitle recipeTitle = new RecipeTitle(stringID, title, rec);
            rec.setID(recipeTitle.getID());
            recipeTitle.getViewButton().setOnAction(e1->{
                    ui.getRoot().setCenter(recipeTitle.getRecipeDetail()); 
                    ui.getRoot().setTop(recipeTitle.getRecipeDetail().getRecipeDisplayHeader());
                    this.rd = rec;

                    rec.setEditButtonAction(this::handleEditButton);
                    rec.setSaveButtonAction(this::handleSaveButton);
                    rec.setDeleteButtonAction(this::handleDeleteButton);

            });
            
            hp.getRecipeList().getChildren().add(recipeTitle);
            recipeTitle.getRecipeDetail().setBackButtonAction2(this::handleBackButton2);
            recipeTitle.getRecipeDetail().setLogoutButtonAction(this::handleLogoutButton);
        }
    }
}