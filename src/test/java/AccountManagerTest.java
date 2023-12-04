import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.bson.Document;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import pantryPal.client.App;
import pantryPal.client.Backend.AccountManager;

public class AccountManagerTest {
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
    }

    @Test
    public void testInsertAccount() {
        AccountManager.insertAccount("testing","testing");
        int result = AccountManager.deleteAccount("testing","testing");
        assertEquals(result,1);
    }

    @Test
    public void testSearchAccount() {
        AccountManager.insertAccount("testing","testing");
        Document searchResult = AccountManager.searchAccount("testing");
        AccountManager.deleteAccount("testing","testing");
        assertNotEquals(searchResult,null);
    }
}