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
import pantryPal.client.Controller;
import pantryPal.client.Model;
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
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame hp = new HomePageAppFrame(ip);
        RecipeDisplayAppFrame dp = new RecipeDisplayAppFrame(new RecipeDisplay());
        LoginPageAppFrame lp = new LoginPageAppFrame();
        UI ui = new UI(root, hp, ip, dp, lp);
        
        File f = new File("src/main/resources/autologin.txt");
        String username = "";
        
        Model model = new Model();

        if(f.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            username = bufferedReader.readLine();
            
            Controller controller = new Controller(username, ui, model);
            ui.returnHomePage();
            controller.loadRecipes();
        } 
        else{
            
            Controller controller = new Controller(username, ui, model);
            ui.setLoginPage();
        }
        
        

        stage.setTitle("PantryPal");
        stage.setResizable(true);
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        stage.show(); // Show the app
    }
}

