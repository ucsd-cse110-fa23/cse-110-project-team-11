import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.bson.Document;


import javafx.application.Application;
import pantryPal.client.App;
import pantryPal.client.MockChatGPT;
import pantryPal.client.TranscriptionService;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.UserAccount.AccountManager;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    Application.launch(App.class,new String[0]); 
                }
            });
            thread.setDaemon(true);
            thread.start();// Initialize the thread
            Thread.sleep(500);
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