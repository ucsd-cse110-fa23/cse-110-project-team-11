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
package pantryPal.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pantryPal.Controller;
import pantryPal.Model;
import javafx.scene.layout.*;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame hp = new HomePageAppFrame(ip);
        RecipeDisplayAppFrame dp = new RecipeDisplayAppFrame(new RecipeDisplay());
        UI ui = new UI(root, hp, ip, dp);

        root.setCenter(hp);
        root.setTop(hp.getHomePageHeader());
        
        Model model = new Model();
        Controller controller = new Controller(ui, model);
        controller.loadRecipes();

        // Set the title of the app
        stage.setTitle("Recipe Details");
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        stage.setResizable(false);
        // Show the app

        stage.show();
    }
}
