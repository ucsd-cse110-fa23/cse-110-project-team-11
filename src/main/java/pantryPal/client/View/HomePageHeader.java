package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class HomePageHeader extends BorderPane {
    private Button createButton, logoutButton;

    String defaultTitleStyle = "-fx-font: 50px Tahoma; -fx-font-weight: bold; \r\n" + //
            "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, white 50%);\r\n" + //
            "    -fx-stroke: black;\r\n" + //
            "    -fx-stroke-width: 1;";

    public HomePageHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle(defaultTitleStyle);

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_CENTER);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        
        this.setCenter(pantryPal);

        createButton = new Button("Create");
        createButton.setPrefSize(100,50);

        logoutButton = new Button("Log out");
        logoutButton.setPrefSize(100,50);

        VBox buttonBox = new VBox(createButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);

        this.setRight(createButton);
        this.setLeft(logoutButton);
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
