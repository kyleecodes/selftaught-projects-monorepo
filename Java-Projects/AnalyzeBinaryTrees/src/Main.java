// Name: Kylee Fields

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {

    private final JTextField input = new JTextField(20);
    private final JTextField output = new JTextField(30);
    private static BinaryTree inputTree;

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }

    // Main class constructor (creates GUI)
    public Main() {
        super("Kylee's Binary Tree Categorizer");
        setSize(715, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        JComponent[] inputComponents = {new JLabel("Enter Tree:"), input};
        JComponent[] outputComponents = {new JLabel("Output:"), output};
        JButton[] buttonComponents = {new JButton("Make Tree"), new JButton("Is Balanced?"), new JButton("Is Full?"), new JButton("Is Proper?"), new JButton("Height"), new JButton("Nodes"), new JButton("Inorder")};
        makePanel(inputComponents);
        makePanel(buttonComponents);
        makePanel(outputComponents);
        addActionListeners(buttonComponents);
        output.setEditable(false);
        setResizable(false);
    }

    // Creates a JPanel from array of JComponents.
    private void makePanel(JComponent[] components) {
        JPanel panel = new JPanel(new FlowLayout());
        for (Component component : components) {
            panel.add(component);
        }
        add(panel);
    }

    // Creates ActionListener from array of JButtons
    private void addActionListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(treeListener);
        }
    }

    // ActionListener utilizes switch statement to set output from getActionCommand
    private final ActionListener treeListener = event -> {
        try {
            switch ((event.getActionCommand())) {
                case "Make Tree" -> {
                    inputTree = new BinaryTree(input.getText());
                    output.setText(inputTree.toString());
                }
                case "Is Balanced?" -> output.setText(String.valueOf(inputTree.isBalanced()));
                case "Is Full?" -> output.setText(String.valueOf(inputTree.isFull()));
                case "Is Proper?" -> output.setText(String.valueOf(inputTree.isProper()));
                case "Height" -> output.setText(String.valueOf(inputTree.height()));
                case "Nodes" -> output.setText(String.valueOf(inputTree.nodes()));
                case "Inorder" -> output.setText(inputTree.inOrder());
                default -> {
                }
            }
        } catch (InvalidTreeSyntax except) {
            JOptionPane.showMessageDialog(getParent(), except.getMessage());
        } catch (IndexOutOfBoundsException indexExcept) {
            JOptionPane.showMessageDialog(getParent(), "No input given. Please try again!");
        }
    };
}