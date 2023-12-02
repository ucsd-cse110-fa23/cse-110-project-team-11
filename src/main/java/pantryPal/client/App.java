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
import java.io.File;

//for the adding new branch 

public class App extends Application {

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
        
        //root.setCenter(lp);
        // root.setTop(hp.getHomePageHeader());
        ui.setLoginPage();
        
        Model model = new Model();
        Controller controller = new Controller(ui, model);
        controller.loadRecipes();
        // controller.loadImagesAtStartup();

        stage.setTitle("PantryPal");
        stage.setResizable(true);
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        stage.show(); // Show the app
    }
}
