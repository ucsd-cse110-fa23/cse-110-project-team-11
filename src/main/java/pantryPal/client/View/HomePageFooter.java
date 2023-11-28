package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class HomePageFooter extends BorderPane {
    private ComboBox<String> sortButton;
    private ComboBox<String> filterButton;

    public HomePageFooter() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");

        filterButton = new ComboBox<>();
        filterButton.getItems().add("All Recipes");
        filterButton.getItems().add("Breakfast");
        filterButton.getItems().add("Lunch");
        filterButton.getItems().add("Dinner");
        filterButton.setPrefSize(100,50);

        sortButton = new ComboBox<>();
        sortButton.getItems().add("Default");
        sortButton.getItems().add("Reverse");
        sortButton.getItems().add("A-Z");
        sortButton.getItems().add("Z-A");
        sortButton.setPrefSize(100,50);

        VBox buttonBox = new VBox(filterButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);

        this.setRight(filterButton);
        this.setLeft(sortButton);

        // VBox placeholder = new VBox();
        // placeholder.setSpacing(15);
        // placeholder.setPrefWidth(100);
        // this.setLeft(placeholder);
    }

    public ComboBox<String> getFilterButton() {
        return filterButton;
    }

    public ComboBox<String> getSortButton() {
        return sortButton;
    }
}
