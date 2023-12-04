import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import pantryPal.client.RecipeManager;
import pantryPal.client.App;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeList;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.ReturnHeader;
import pantryPal.client.View.UI;


import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFX extends FxRobot{

    @BeforeEach
    void setup() throws Exception {
        App.setTest(false);
        ApplicationTest.launch(App.class);
    }


    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    //@Test
    void should_contain_button() {
        Label top = (Label) App.getUI().getRoot().getTop();
        // expect:
        assertEquals(top.getText(), "Welcome to ");
        VBox login = (VBox) App.getUI().getRoot().getBottom();
        HBox usernameBox = (HBox) login.getChildren().get(0);
        Text usernameText = (Text) usernameBox.getChildren().get(0);
        assertEquals(usernameText.getText(), "Username: ");
        HBox buttonBox = (HBox) login.getChildren().get(2);
        //Button loginButton = (Button) buttonBox.getChildren().get(0);
        clickOn((Button) buttonBox.getChildren().get(0));
        assertTrue(App.getUI().getRoot().getCenter() instanceof HomePageAppFrame);
        HomePageAppFrame hp = (HomePageAppFrame) App.getUI().getRoot().getCenter();
        RecipeList rl = hp.getRecipeList();
        RecipeTitle first = (RecipeTitle)rl.getChildren().get(0);
        assertEquals(first.getTitle().getText(), "Fried Rice");   

    }
    // static Button button;

    // public static class DemoApplication extends Application {
    //     @Override
    //     public void start(Stage stage) {
    //         button = new Button("click me!");
    //         button.setOnAction(actionEvent -> button.setText("clicked!"));
    //         stage.setScene(new Scene(new StackPane(button), 100, 100));
    //         stage.show();
    //         stage.setAlwaysOnTop(true);
    //     }
    // }

    // @BeforeEach
    // void setup() throws Exception {
    //     ApplicationTest.launch(DemoApplication.class);
    // }

    // @AfterEach
    // void cleanup() throws Exception {
    //     FxToolkit.cleanupStages();
    // }

    // @Test
    // void should_contain_button() {
    //     // expect:
    //     assertThat(lookup(".button").queryButton()).hasText("click me!");
    //     assertThat(button).hasText("click me!");
    //     verifyThat(".button", hasText("click me!"), informedErrorMessage(this));
    // }

    // @Test
    // void should_click_on_button() {
    //     // when:
    //     clickOn(".button");

    //     // then:
    //     assertThat(lookup(".button").queryButton()).hasText("clicked!");
    //     assertThat(button).hasText("clicked!");
    //     verifyThat(".button", hasText("clicked!"), informedErrorMessage(this));
    // }
}
