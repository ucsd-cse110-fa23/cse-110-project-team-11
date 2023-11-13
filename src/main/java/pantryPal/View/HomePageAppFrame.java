package pantryPal.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
