package pantryPal.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;

public class HomePageAppFrame extends BorderPane{
    private HomePageHeader homePageHeader;
    private RecipeList recipeList;
    private Button createButton;


    public HomePageAppFrame(InputAppFrame InputPage) {
        homePageHeader = new HomePageHeader();
        recipeList = new RecipeList();
        // loadRecipes(); // loads recipe

        createButton = homePageHeader.getCreateButton();    

        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        this.setCenter(scrollPane);

        // addListeners(InputPage);
    }

    public HomePageHeader getHomePageHeader() {
        return homePageHeader;
    }
    
    public void setCreateButtonAction(EventHandler<ActionEvent> eventHandler){
        createButton.setOnAction(eventHandler);
    }

    // public void loadRecipes(){
    //     Controller.loadRecipes();
    // }

    public RecipeList getRecipeList() {
        return recipeList;
    }    
}
