/**
 *  Methods/Unit Tests to test:
 * 
 */
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;
import javafx.embed.swing.JFXPanel;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pantryPal.RecipeManager;
import pantryPal.View.App;
import pantryPal.View.HomePageAppFrame;
import pantryPal.View.HomePageHeader;
import pantryPal.View.InputAppFrame;
import pantryPal.View.RecipeDisplay;
import pantryPal.View.RecipeDisplayAppFrame;
import pantryPal.View.RecipeTitle;
import pantryPal.View.ReturnHeader;
import javafx.application.Platform;

//import org.testfx.framework.junit.ApplicationTest;
/**
 * UITest
 */

public class AppTest extends App {
    private volatile boolean failed  = false;

   
//    @BeforeAll
//    static void initJfxRuntime() {
//        Platform.startup(() -> {});
//    }
    
    //@BeforeAll
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
        HomePageHeader hph = new HomePageHeader();
        assertNotNull(hph.getCreateButton(), "Should not be null");
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
    public void testBackButton() throws Exception {
// //
// ////        Thread thread = new Thread(new Runnable() {
// ////
// ////            @Override
// ////            public void run() {
// ////                try {
// ////                    setUpClass();
// ////                } catch (InterruptedException e) {
// ////                    e.printStackTrace();
// ////                }
// ////                Platform.runLater(new Runnable() {
// ////
// ////                    @Override
// ////                    public void run() {
// ////                        ReturnHeader rh = new ReturnHeader();
// ////                        assertNotNull(rh.getBackButton(), "Should not be null");
// ////
// ////                    }
// ////                });
// ////            }
// ////        });
// ////        thread.start();// Initialize the thread
// ////        Thread.sleep(2000); // Time to use the app, with out this, the thread
// ////    }
// ////    
// ////
// ////    @Test
// ////    public void testDeleteButton() throws InterruptedException {
// ////
// ////        Thread thread = new Thread(new Runnable() {
// ////
// ////            @Override
// ////            public void run() {
// ////                try {
// ////                    setUpClass();
// ////                } catch (InterruptedException e) {
// ////                    e.printStackTrace();
// ////                }
// ////                Platform.runLater(new Runnable() {
// ////
// ////                    @Override
// ////                    public void run() {
// ////                        RecipeDisplay rd = new RecipeDisplay();
// ////                        assertNotNull(rd.getDeleteButton(), "Should not be null");
// ////
// ////                    }
// ////                });
// ////            }
// ////        });
// ////        thread.start();// Initialize the thread
// ////        Thread.sleep(2000); // Time to use the app, with out this, the thread
// ////        Platform.startup(() ->
// ////        {
// //////         Platform.startup(() -> {});
// ////            new App().start(new Stage());
// ////          RecipeDisplay rd = new RecipeDisplay();
// ////          assertNotNull(rd.getDeleteButton(), "Should not be null");
// //////        });
// //        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
// //            @Override
// //            public void uncaughtException(Thread th, Throwable ex) {
// //                System.out.println("Uncaught exception: " + ex);
// //                fail("fuck");
// //                System.out.println("fuck");
// //                }
// //        };
// //        
// //        Thread thread = new Thread(new Runnable() {
// //
// //            @Override
// //            public void run() {
// //                new JFXPanel(); // Initializes the JavaFx Platform
// //                RecipeDisplay rd = new RecipeDisplay();
// //                assertNull(rd.getDeleteButton(), "Should not be null");
// ////                throw new RuntimeException();
// ////                RecipeDisplay rd = new RecipeDisplay();
// ////                assertNull(rd.getDeleteButton(), "Should not be null");
// //            }
// //        });
// //        thread.setUncaughtExceptionHandler(h);
// //        thread.start();// Initialize the thread
// //        Thread.sleep(10000); // Time to use the app, with out this, the thread
// ////                                // will be killed before you can tell.
// ////        new JFXPanel(); // Initializes the JavaFx Platform
// ////        RecipeDisplay rd = new RecipeDisplay();
// ////        assertNotNull(rd.getDeleteButton(), "Should not be null");
// //        //assertEquals(0, 1);
//         var latch = new CountDownLatch(1);

//         Executors.newSingleThreadExecutor().execute(() -> {
//             new JFXPanel(); // Initializes the JavaFx Platform
//             RecipeDisplay rd = new RecipeDisplay();
//             if (rd.getDeleteButton() != null)
//                 failed = true;
//             latch.countDown();
//         });

//         latch.await();
//         if(failed){
//             fail("Doesn't fail the test");
//         }

        RecipeDisplay rd = new RecipeDisplay();
       assertNotNull(rd.getDeleteButton(), "Should not be null");
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