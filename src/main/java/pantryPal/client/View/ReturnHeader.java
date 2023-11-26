package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class ReturnHeader extends BorderPane {
    private Button backButton, logoutButton;

    String defaultTitleStyle = "-fx-font: 50px Tahoma; -fx-font-weight: bold; \r\n" + //
            "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, white 50%);\r\n" + //
            "    -fx-stroke: black;\r\n" + //
            "    -fx-stroke-width: 1;";

    public ReturnHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle(defaultTitleStyle);

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
