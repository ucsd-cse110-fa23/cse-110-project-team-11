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
public class UI  {

    private BorderPane root;
    private HomePageAppFrame HomePage;
    private InputAppFrame inputPage;
    private RecipeDisplayAppFrame displayPage;

    public UI(BorderPane r, HomePageAppFrame hp, InputAppFrame ip, RecipeDisplayAppFrame dp){

        this.root = r;
        this.HomePage = hp; 
        this.inputPage = ip;
        this.displayPage = dp;
    }

    public HomePageAppFrame getHomePage() {
        return HomePage;
    }
    

    public BorderPane getRoot() {
        return root;
    }
    
    public void returnHomePage() {
        
        root.setCenter(HomePage);
        root.setTop(HomePage.getHomePageHeader());
    }

    public InputAppFrame getInputPage(){
        return inputPage;
    }

    public RecipeDisplayAppFrame getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(RecipeDisplayAppFrame dp) {
        this.displayPage = dp;
    }
    
}
