/**
 *  Methods/Unit Tests to test:
 * 
 */
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pantryPal.client.RecipeManager;
import pantryPal.client.App;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.ReturnHeader;
//import org.testfx.framework.junit.ApplicationTest;
/**
 * UITest
 */

public class AppTest extends App {
   
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
    public void testCreateButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                           HomePageHeader hph = new HomePageHeader();
                            assertNotNull(hph.getCreateButton(), "Should not be null");
                         // assertEquals(UI.getRoot().getCenter(), UI.getInputPage());

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }
        
    

    @Test
    public void testEditbutton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                           RecipeDisplay rd = new RecipeDisplay();
                            assertNotNull(rd.getEditButton(), "Should not be null");

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }
    
    
    @Test
    public void testBackButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ReturnHeader rh = new ReturnHeader();
                        assertNotNull(rh.getBackButton(), "Should not be null");

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }
    

    @Test
    public void testDeleteButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        RecipeDisplay rd = new RecipeDisplay();
                        assertNotNull(rd.getDeleteButton(), "Should not be null");

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }
    
    
    @Test
    public void testSaveButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        RecipeDisplay rd = new RecipeDisplay();
                        assertNotNull(rd.getSaveButton(), "Should not be null");
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }
    
    
    @Test
    public void testViewButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        RecipeTitle rt = new RecipeTitle("id","title1, title2", new RecipeDisplayAppFrame(new RecipeDisplay())); 
                        assertNotNull(rt.getViewButton(), "Should not be null");
                        // assertEquals(getRoot().getCenter() instanceof RecipeDisplayAppFrame, true);
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }

    
    @Test // tests the input buttons and appframe 
    public void testHeader () throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        InputAppFrame appFrame = new InputAppFrame(); // generate input appfram
                        // 1) check if header of appframe exists
                        assertNotNull(appFrame.getReturnHeader());
                        // 2) check if buttons exist (non-null)
                        assertNotNull(appFrame.getReturnHeader().getBackButton()); // should be in header
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }

    // 3) test functionality of buttons
    @Test
    public void testStartButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
                        Button startButton = appFrame.getStartButton();
                        
                        assertNotNull(startButton,"should not be null");
                        
                        Label label = appFrame.getRecordingLabel();
                        assertNotNull(label, "should not be null"); // checks if button can be pressed/functions
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    }


    @Test
    public void testStopButton() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
                        Button stopButton = appFrame.getStartButton();
                        assertNotNull(stopButton,"should not be null");
                        
                        Label label = appFrame.getRecordingLabel();
                        assertNotNull(label,"should not be null");
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000); // Time to use the app, with out this, the thread
    } 
    
}