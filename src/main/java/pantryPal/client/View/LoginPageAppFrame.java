package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javax.swing.GroupLayout.Alignment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import pantryPal.client.UserAccount.Password;

public class LoginPageAppFrame extends BorderPane {

    private Button loginButton, createAccButton;

    private VBox titleBox;
    private VBox loginBox;
    private TextField username;
    private PasswordField password;
    private Label message;
    private CheckBox auto;  

    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 18 arial; -fx-pref-width: 165; -fx-pref-height: 30;";
    String defaultTitleStyle = "-fx-font: 100px Tahoma; -fx-font-weight: bold; \r\n" + //
            "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, white 50%);\r\n" + //
            "    -fx-stroke: black;\r\n" + //
            "    -fx-stroke-width: 1;";
    String defaultTextStyle = "-fx-font: 18 arial; -fx-pref-width: 175px; -fx-pref-height: 100px; -fx-text-fill: black;";
    String defaultHeaderStyle = "-fx-font: 50 Tahoma;  -fx-text-fill: black;";

    public LoginPageAppFrame(){

        Label introText = new Label("Welcome to ");
        introText.setStyle(defaultHeaderStyle);

        VBox top = new VBox(introText);
        top.setAlignment(Pos.TOP_CENTER);

        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle(defaultTitleStyle);
        VBox center = new VBox(titleText);
        center.setAlignment(Pos.BOTTOM_CENTER);

        titleBox = new VBox(top, center);
        titleBox.setSpacing(10);
        //titleBox.setAlignment(Pos.CENTER)

        Text usernameText = new Text("Username: ");
        usernameText.setStyle(defaultTextStyle);
        Text passwordText = new Text("Password: ");
        passwordText.setStyle(defaultTextStyle);

        username = new TextField();
        username.setEditable(true);
        username.setPrefSize(175, 30);
        password = new PasswordField();
        password.setEditable(true);
        password.setPrefSize(175, 30);

        HBox usernameBox = new HBox(usernameText, username);
        usernameBox.setSpacing(5);
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox(passwordText, password);
        passwordBox.setSpacing(5);
        passwordBox.setAlignment(Pos.CENTER);

        loginButton = new Button("Login");
        loginButton.setStyle(defaultButtonStyle);

        createAccButton = new Button("Create Account");
        createAccButton.setStyle(defaultButtonStyle);

        message = new Label("");
        message.setStyle("-fx-text-alignment: center; -fx-font: 16 arial; -fx-pref-width: 175px; -fx-pref-height: 100px; -fx-text-fill: red;");
        message.setWrapText(true);
        
        HBox buttonBox = new HBox(loginButton, createAccButton);
        buttonBox.setAlignment(Pos.CENTER);

        auto = new CheckBox("Auto Log-In?");
        auto.setAlignment(Pos.CENTER);
        

        loginBox = new VBox(usernameBox, passwordBox, message, buttonBox, auto);
        loginBox.setSpacing(10);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(10));


        this.setTop(titleBox);
        this.setCenter(loginBox);

    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getCreateButton() {
        return createAccButton;
    }
    

    public void setLoginButtonAction(EventHandler<ActionEvent> eventHandler) {
        loginButton.setOnAction(eventHandler);
    }

    public void setCreateAccButtonAction(EventHandler<ActionEvent> eventHandler) {
        createAccButton.setOnAction(eventHandler);
    }

    
    public VBox getTitleBox(){
        return titleBox;
    }

    public VBox getLogin(){
        return loginBox;
    }

    public void setMessage(String s){
        message.setText(s);
    }

    public Label getMessage() {
        return message;
    }

    public CheckBox getAuto(){
        return auto;
    }

    public void setUsername(String name) {
        username.setText(name);
    }
    
    public void setPassword(String pw) {
        password.setText(pw);
    }




}

