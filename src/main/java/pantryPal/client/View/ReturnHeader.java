package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class ReturnHeader extends BorderPane {
    private Button backButton, logoutButton;

    public ReturnHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 40;");

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_CENTER);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        
        this.setCenter(pantryPal);

        backButton = new Button("Back");
        backButton.setPrefSize(100,50);
        logoutButton = new Button("Log out");
        logoutButton.setPrefSize(100,50);
        
        this.setRight(backButton);
        this.setLeft(logoutButton);

    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
