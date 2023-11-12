import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.*;

import javax.print.attribute.HashDocAttributeSet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.event.EventHandler;
public class Controller {
    
    private Input input = new Input();
    private RecipeCreator rc = new RecipeCreator();
    private InputAppFrame inputFrame;
    private RecipeParser rp = new RecipeParser();
    private UI ui;
    private HomePageHeader hph;
    private HomePageAppFrame hp;
    private RecipeDisplayAppFrame rd;
    private RecipeTitle rt = new RecipeTitle("");

    public Controller(UI ui) {
        this.ui = ui;
        this.inputFrame = ui.getInputPage();
        this.hp = ui.getHomePage();
        this.hph = hp.getHomePageHeader();
        this.rd = ui.getDisplayPage();        
        
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
        this.rd.setDeleteButtonAction(this::handleBackButton);
        this.hp.setCreateButtonAction(this::handleCreateButton);
        this.rd.setSaveButtonAction(this::handleSaveButton);
        this.rd.setEditButtonAction(this::handleEditButton);
        this.rt.setViewButtonAction(this::handleViewButton);
    }

    private void handleCreateButton(ActionEvent event) {
        ui.getRoot().setCenter(inputFrame);
        ui.getRoot().setTop(inputFrame.getReturnHeader());
    }

    private void handleStartButton(ActionEvent event) {
        RecButtons rb = inputFrame.getRecButtons();
        rb.setRecordingLabel("Recording");
        input.captureAudio();
    }
    //TODO auto stop when press back

    private void handleStopButton(ActionEvent event) throws InterruptedException, IOException {
        // Stop Button

        String promptType = input.getPromptType();
        
        if(input.stopCapture(promptType)){
            if(promptType.equals("MealType")){
                input.setPromptType("Ingredients");
                inputFrame.getRecButtons().setRecipeText("");
                inputFrame.getRecButtons().setRecordingLabel("Please input Ingredients");
            }
            else{
                inputFrame.getRecButtons().setRecordingLabel("Recipe Displayed");
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
                    displayRec.setDeleteButtonAction(this::handleBackButton);
                    displayRec.setSaveButtonAction(this::handleSaveButton);
                    displayRec.setEditButtonAction(this::handleEditButton);
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
            inputFrame.getRecButtons().setRecordingLabel("Invalid Input. Please say a proper meal type");
        }
        
    }

    private void handleBackButton(ActionEvent event){
        ui.returnHomePage();   
        input.setPromptType("MealType"); 
        inputFrame.getRecButtons().setRecordingLabel("Select Meal Type: Breakfast, Lunch, or Dinner");    
    }

    private void handleBackButton2(ActionEvent event){
        ui.returnHomePage();   
        input.setPromptType("MealType"); 
        inputFrame.getRecButtons().setRecordingLabel("Select Meal Type: Breakfast, Lunch, or Dinner");    
    }

    private void handleEditButton(ActionEvent event) {
        boolean editable = rd.getEditable();
        TextArea ingredients = rd.getIngredients();
        Button editButton = rd.getEditButton();
        TextArea steps = rd.getSteps();
        if (!editable) {
                ingredients.setEditable(true);
                steps.setEditable(true);
                editButton.setText("Stop");
                rd.setEditable(true);
            }
            else {
                ingredients.setEditable(false);
                steps.setEditable(false);
                editButton.setText("Edit");
                rd.setEditable(false);
            }
    }

    private void handleSaveButton(ActionEvent event){
        rd.getIngredients().setEditable(false);
        rd.getSteps();
        if (rd.getID() == null) {
            try {

                RecipeTitle recipeDis = RecipeManager.insertRecipe(rd.getTitle().getText(), rd.getIngredients().getText(), rd.getSteps().getText());
                rd.setID(RecipeManager.getStringID());
                recipeDis.setViewButtonAction(this::handleViewButton);
                recipeDis.getRecipeDetail().setBackButtonAction2(this::handleBackButton2);
                this.rt = recipeDis;
                hp.getRecipeList().getChildren().add(recipeDis);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else {
            try {
                RecipeManager.updateRecipe(rd.getID(), rd.getTitle().getText(), rd.getIngredients().getText(), rd.getSteps().getText());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    private void handleViewButton(ActionEvent event){
   
        ui.getRoot().setCenter(this.rt.getRecipeDetail()); 
        ui.getRoot().setTop(this.rt.getRecipeDetail().getRecipeDisplayHeader());
    }

    public void loadRecipes(){
        ArrayList<RecipeTitle> recipes = RecipeManager.loadRecipes();
        
        for(int i = 0; i < recipes.size(); i++){
            //recipes.get(i).setViewButtonAction(this::handleViewButton);
            RecipeTitle title = recipes.get(i);
            recipes.get(i).getViewButton().setOnAction(e1->{
                ui.getRoot().setCenter(title.getRecipeDetail()); 
                ui.getRoot().setTop(title.getRecipeDetail().getRecipeDisplayHeader());
            });
            hp.getRecipeList().getChildren().add(recipes.get(i));
            title.getRecipeDetail().setBackButtonAction2(this::handleBackButton2);
        }

    }



}