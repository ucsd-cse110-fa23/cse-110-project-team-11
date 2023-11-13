package pantryPal.View;
import javafx.scene.layout.*;

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
