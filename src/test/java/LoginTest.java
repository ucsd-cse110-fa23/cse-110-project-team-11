import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.bson.Document;


import javafx.application.Application;
import javafx.application.Platform;
import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.API.MockChatGPT;
import pantryPal.client.Backend.AccountManager;
import pantryPal.client.View.LoginPageAppFrame;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    MockApp mock = new MockApp();
    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        //FxToolkit.cleanupApplication(mock);
        //FxToolkit.cleanupStages();
        Platform.setImplicitExit(false);

    }
    @Test
    public void testLoginButton() {
        LoginPageAppFrame loginPage = new LoginPageAppFrame();
        assertNotNull(loginPage.getLoginButton(), "Should not be null");
    }

    @Test
    public void testCreateButton() {
        LoginPageAppFrame loginPage = new LoginPageAppFrame();
        assertNotNull(loginPage.getCreateButton(), "Should not be null");
    }


    //US10 BDD 
    @Test 
    public void testCreateUnusedUsername() {
        //check if there's login button 
        LoginPageAppFrame loginPage = new LoginPageAppFrame();
        assertNotNull(loginPage.getLoginButton(), "Should not be null");

        Document unUsedID = AccountManager.searchAccount("no id");
        assertNotEquals(unUsedID, "Id exists");
    }
}