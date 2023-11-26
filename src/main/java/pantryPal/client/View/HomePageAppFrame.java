package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HomePageAppFrame extends BorderPane{
    private HomePageHeader homePageHeader;
    private RecipeList recipeList;
    private Button createButton;
    private Button logoutButton;


    public HomePageAppFrame(InputAppFrame InputPage) {
        homePageHeader = new HomePageHeader();
        recipeList = new RecipeList();

        createButton = homePageHeader.getCreateButton();    
        logoutButton = homePageHeader.getLogoutButton();

        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        this.setCenter(scrollPane);
    }

    public HomePageHeader getHomePageHeader() {
        return homePageHeader;
    }
    
    public void setCreateButtonAction(EventHandler<ActionEvent> eventHandler){
        createButton.setOnAction(eventHandler);
    }

    public void setLogoutButtonAction(EventHandler<ActionEvent> eventHandler){
        logoutButton.setOnAction(eventHandler);
    }
    
    public RecipeList getRecipeList() {
        return recipeList;
    }    
}
