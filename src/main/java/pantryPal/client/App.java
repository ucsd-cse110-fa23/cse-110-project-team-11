/**
 * This the file that handles the front end of the code. Should deal with the
 * app UI and all of the button features. Has X different pages:
 * 
 * 1. Main (browse) page
 * 2. Create recipe page
 * 3. Detailed recipe page
 * 
 * Each of these pages have their respective headers, bodies, buttons, etc.
 */
package pantryPal.client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pantryPal.client.Controller.Controller;
import pantryPal.client.Model.Model;
import pantryPal.client.Model.IModel;
import pantryPal.client.Model.MockModel;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.UI;
import javafx.scene.layout.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class App extends Application {
    private static boolean test = false;
    private static UI ui;
    public static void main(String[] args) {        
        launch(args);
    }

    public static UI getUI() {
        return ui;
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame hp = new HomePageAppFrame(ip);
        RecipeDisplayAppFrame dp = new RecipeDisplayAppFrame(new RecipeDisplay());
        LoginPageAppFrame lp = new LoginPageAppFrame();
        ui = new UI(root, hp, ip, dp, lp);
        
        File f = new File("src/main/resources/autologin.txt");
        String username = "";
        
        IModel model = new Model();
        if (test) {
            model = new MockModel();
        }

        Controller controller;
        if(f.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            username = bufferedReader.readLine();
            
            controller = new Controller(username, ui, model);
            ui.returnHomePage();
            controller.loadRecipes();
            bufferedReader.close();
        } 
        else{
            
            controller = new Controller(username, ui, model);
            ui.setLoginPage();
        }
        
        stage.setTitle("PantryPal");
        stage.setResizable(true);
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        stage.show(); // Show the app
    }

    public static void setTest(boolean b) {
        test = b;
    }
    public static boolean getTest() {
        return test;
    }
}