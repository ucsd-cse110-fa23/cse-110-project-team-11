package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;

public class LoginPageAppFrame extends BorderPane {

    private Button loginButton;

    Text introText;
    Text titleText;
    VBox loginBox;

    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 25 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultTitleStyle = "-fx-font: 100px Tahoma; -fx-font-weight: bold; \r\n" + //
            "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, white 50%);\r\n" + //
            "    -fx-stroke: black;\r\n" + //
            "    -fx-stroke-width: 1;";
    String defaultTextStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 100px; -fx-text-fill: black;";

    public LoginPageAppFrame(){

        introText = new Text("Welcome to ");
        titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle(defaultTitleStyle);

        Text usernameText = new Text("Username: ");
        Text passwordText = new Text("Password");

        TextField username = new TextField();
        username.setEditable(true);
        TextField password = new TextField();
        password.setEditable(true);

        HBox usernameBox = new HBox(usernameText, username);
        usernameBox.setSpacing(5);

        HBox passwordBox = new HBox(passwordText, password);
        passwordBox.setSpacing(5);

        loginButton = new Button("Login");
        loginButton.setStyle(defaultTextStyle);

        loginBox = new VBox(usernameBox, passwordBox, loginButton);
        loginBox.setSpacing(10);


        this.setTop(introText);
        this.setCenter(titleText);
        this.setBottom(loginBox);

    }
    

    public void setLoginButtonAction(EventHandler<ActionEvent> eventHandler) {
        loginButton.setOnAction(eventHandler);
    }

    public Text getIntro(){
        return introText;
    }

    public Text getTitle(){
        return titleText;
    }

    public VBox getLogin(){
        return loginBox;
    }

}

