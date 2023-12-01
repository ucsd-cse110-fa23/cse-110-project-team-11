package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HomePageAppFrame extends BorderPane{
    private HomePageHeader homePageHeader;
    private HomePageFooter homePageFooter;
    private RecipeList recipeList;
    private Button createButton;
    private Button logoutButton;
    private ComboBox<String> sortButton;
    private ComboBox<String> filterButton;


    public HomePageAppFrame(InputAppFrame InputPage) {
        homePageHeader = new HomePageHeader();
        homePageFooter = new HomePageFooter();
        recipeList = new RecipeList();

        filterButton = homePageFooter.getFilterButton();
        sortButton = homePageFooter.getSortButton();
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
    
    public HomePageFooter getHomePageFooter() {
        return homePageFooter;
    }

    public void setFilterButtonAction(EventHandler<ActionEvent> eventHandler) {
        filterButton.setOnAction(eventHandler);
    }

    public void setSortButtonAction(EventHandler<ActionEvent> eventHandler){
        sortButton.setOnAction(eventHandler);
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
