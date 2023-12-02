package pantryPal.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import java.util.Collections;

public class SortTask extends Task<ObservableList<Node>>{
    
    private String sortState;

    public ObservableList<Node> call(){
        
        // alphabetical order
        if (sortState.equals("A-Z")) {
            // Create a copy of the children list
            ObservableList<Node> sortedList = new ObservableList<>(hp.getRecipeList().getChildren());
    
            // Sort the copy
            Collections.sort(sortedList, new SortAlphabetically());
    
            // Clear the original list and add the sorted elements
            hp.getRecipeList().getChildren().clear();
            hp.getRecipeList().getChildren().addAll(sortedList);
        }
        else if (sortState.equals("Z-A")) {
            // Create a copy of the children list
            ObservableList<Node> sortedList = new ObservableList<Node>(hp.getRecipeList().getChildren());
    
            // Sort the copy
            Collections.sort(sortedList, new SortReverseAlphabetically());
    
            // Clear the original list and add the sorted elements
            hp.getRecipeList().getChildren().clear();
            hp.getRecipeList().getChildren().addAll(sortedList);
        }
        else if (sortState.equals("Default")){
            FXCollections.reverse(hp.getRecipeList().getChildren());
        }
    }

    public String getSortState(){
        return this.sortState;
    }

    public void setSortState(String sortState){
        this.sortState = sortState;
    }
}
