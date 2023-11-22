package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class HomePageHeader extends HBox {
    private Button createButton;

    public HomePageHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.setAlignment(Pos.TOP_CENTER);
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        
        this.getChildren().add(titleText);

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_LEFT);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        this.getChildren().add(pantryPal);

        createButton = new Button("create");
        createButton.setPrefSize(100,50);
        this.getChildren().add(createButton);

        VBox buttonBox = new VBox(createButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);// prefWidth
        this.getChildren().add(buttonBox);
    }

    public Button getCreateButton() {
        return createButton;
    }
}
