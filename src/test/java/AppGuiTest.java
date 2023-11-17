import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import static org.testfx.assertions.api.Assertions.assertThat;

 class AppGui extends Application {
    public static Label message;

    @Override
    public void start(Stage stage) {
        message = new Label("Welcome!");

        stage.setScene(new Scene(new StackPane(message)));
        stage.show();
    }

    public static Label getLabel() {
        return message;
    }
}

@ExtendWith(ApplicationExtension.class)
public class AppGuiTest {

    private Label message;

    @Start
    protected void start(Stage stage) {
        message = new Label("Welcome!");

        stage.setScene(new Scene(new StackPane(message)));
        stage.show();
    }

    @Test
    public void testMessage() {
        assertThat(message).hasText("Welcome!");
    }

    @Test
    public void testChangeMessage(FxRobot robot) {
        robot.interact(() -> message.setText("Bye!"));
        assertThat(message).hasText("Bye!");
    }

}
