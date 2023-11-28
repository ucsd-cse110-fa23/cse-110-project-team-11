package pantryPal.client;

import java.util.Comparator;

public class SortReverseAlphabetically implements Comparator<String[]> {
    @Override
    public int compare(String[] a, String[] b) {
        // Assuming title is at index 1
        return b[1].compareToIgnoreCase(a[1]);
    }
}