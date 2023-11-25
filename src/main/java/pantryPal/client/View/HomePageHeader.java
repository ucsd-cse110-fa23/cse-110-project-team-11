package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class HomePageHeader extends BorderPane {
    private Button createButton;

    public HomePageHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 40;");

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_CENTER);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        
        this.setCenter(pantryPal);

        createButton = new Button("create");
        createButton.setPrefSize(100,50);

        VBox buttonBox = new VBox(createButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);

        this.setRight(createButton);

        VBox placeholder = new VBox();
        placeholder.setSpacing(15);
        placeholder.setPrefWidth(100);
        this.setLeft(placeholder);
    }

    public Button getCreateButton() {
        return createButton;
    }
}
