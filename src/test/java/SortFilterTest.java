import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.Backend.AccountManager;
import pantryPal.client.Backend.SortAlphabetically;
import pantryPal.client.Backend.SortReverseAlphabetically;
import pantryPal.client.View.HomePageFooter;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.RecipeTitle;

public class SortFilterTest {

    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    public void testSortButton() throws InterruptedException {
        HomePageFooter hpf = new HomePageFooter();
        assertNotNull(hpf.getSortButton(), "Should not be null");
    }

    @Test
    public void testFilterButton() throws InterruptedException {
        HomePageFooter hpf = new HomePageFooter();
        assertNotNull(hpf.getFilterButton(), "Should not be null");
    }

    @Test
    public void testSortAlphabetically() {
        RecipeTitle titleA = new RecipeTitle("Apple Pie", "dinner");
        RecipeTitle titleB = new RecipeTitle("Banana Bread", "lunch");
        
        Node nonRecipeNode = new Pane();

        SortAlphabetically sortAlphabetically = new SortAlphabetically();

        assertEquals(-1, sortAlphabetically.compare(titleA, titleB), "Apple Pie should come before Banana Bread");
        assertEquals(1, sortAlphabetically.compare(titleB, titleA), "Banana Bread should come after Apple Pie");
        assertEquals(0, sortAlphabetically.compare(new RecipeTitle(null,null), titleB), "Null title should be handled properly");
        assertEquals(0, sortAlphabetically.compare(nonRecipeNode, titleB), "Non-RecipeTitle object should be handled properly");
    }



    @Test
    public void testSortReverseAlphabetically() {
        RecipeTitle titleA = new RecipeTitle("Apple Pie", "dinner");
        RecipeTitle titleB = new RecipeTitle("Banana Bread", "lunch");
        
        Node nonRecipeNode = new Pane();

        SortReverseAlphabetically ReversAlphabetically = new SortReverseAlphabetically();

        assertEquals(1, ReversAlphabetically.compare(titleA, titleB), "Apple Pie should come before Banana Bread");
        assertEquals(-1, ReversAlphabetically.compare(titleB, titleA), "Banana Bread should come after Apple Pie");
        assertEquals(0, ReversAlphabetically.compare(new RecipeTitle(null,null), titleB), "Null title should be handled properly");
        assertEquals(0, ReversAlphabetically.compare(nonRecipeNode, titleB), "Non-RecipeTitle object should be handled properly");
    }
    
}
