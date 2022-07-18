// Name: Kylee Fields
// Description: orderedList class


import java.util.List;

// Constructor class for ordered list
// Returns boolean if list is sorted
public class orderedList {
    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
        boolean isSorted = true;
        for (int i = list.size() - 1; i > 0; i--) {
            T current = list.get(i);
            if (!checkSorted(list, current)) {
                isSorted = false;
            }
        }
        return isSorted;
    }

    private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
        T currentValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) {
            // Check for null values when comparing
            return currentValue.compareTo(nextValue) >= 0;
            // Return false if next index is larger
        }
        return true;
    }
}