package pantryPal.client.View;
import javafx.scene.layout.*;

public class UI  {

    private BorderPane root;
    private HomePageAppFrame HomePage;
    private InputAppFrame inputPage;
    private RecipeDisplayAppFrame displayPage;
    private LoginPageAppFrame loginPage;

    public UI(BorderPane r, HomePageAppFrame hp, InputAppFrame ip, RecipeDisplayAppFrame dp, LoginPageAppFrame lp){

        this.root = r;
        this.HomePage = hp; 
        this.inputPage = ip;
        this.displayPage = dp;
        this.loginPage = lp;
    }

    public HomePageAppFrame getHomePage() {
        return HomePage;
    }
    

    public BorderPane getRoot() {
        return root;
    }
    
    public void returnHomePage() {
        root.setTop(HomePage.getHomePageHeader());
        root.setCenter(HomePage);
        root.setBottom(null);
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
    public LoginPageAppFrame getLoginPage(){
        return loginPage;
    }

    public void setLoginPage(){

        root.setTop(this.loginPage.getIntro());
        root.setCenter(this.loginPage.getTitle());
        root.setBottom(this.loginPage.getLogin());
    }
}
