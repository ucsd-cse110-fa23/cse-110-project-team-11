package pantryPal.client;

import java.util.Comparator;

public class SortAlphabetically implements Comparator<String[]> {
    @Override
    public int compare(String[] a, String[] b) {
        // Assuming title is at index 1
        System.out.println(a[1] + ", " + b[1] + " is " + a[1].compareToIgnoreCase(b[1]));
        return a[1].compareToIgnoreCase(b[1]);
    }
}