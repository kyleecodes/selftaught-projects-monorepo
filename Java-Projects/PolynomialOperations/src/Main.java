// Name: Kylee Fields

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Main constructor class
public class Main {

    // Class list field
    private static final List<Polynomial> polyList = new ArrayList<>();

    // Main method
    public static void main(String[] args) {
        processPolyList();
    }

    // Class method for taking string from a file and tokenizing it into an ArrayList
    public static List<String> fromFile() {
        // Create ArrayList and JFileChooser
        ArrayList<String> expressionList = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        // Show both directories and files and use current directory for ease
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                // Try scanning file, throw exceptions if file not found or empty.
                Scanner fileIn;
                fileIn = new Scanner(file);
                if (file.isFile()) {
                    while (fileIn.hasNextLine()) {
                        String expression = fileIn.nextLine();
                        expressionList.add(expression);
                    }
                }
            } catch (NoSuchElementException nse) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File is empty!");
            } catch (FileNotFoundException fne) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File is not found!");
            }
        }
        return expressionList;
    }

    // Class method for checking if expression is a weak order or not, returns boolean
    public static boolean checkWeakOrder(List<Polynomial> polyList) {
        boolean isWeakOrder = true;
        Polynomial previous = polyList.get(polyList.size() - 1);
        for (int i = polyList.size() - 2; i > 0; i--) {
            // Calls helper function for comparing exponents in expression, return boolean
            if (previous.compareExponents(polyList.get(i)) < 0) {
                isWeakOrder = false;
            }
        }
        return isWeakOrder;
    }

    // Class function that calls fromFile to populate a list of polynomial objects, then checks list order.
    public static void processPolyList() {
        try {
            ArrayList<String> a = (ArrayList<String>) fromFile();
            for (String element : a) {
                Polynomial p = new Polynomial(element);
                System.out.println(p);
                polyList.add(p);
            }
        } catch (invalidPolynomialSyntax ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        // Call to check for strong order
        System.out.println("Strong Ordered: " + orderedList.checkSorted(polyList));
        // Call to check for weak order (exponents only)
        System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
    }
}