// Name: Kylee Fields
// Project: CMSC 350 Project 1
// Date: 11/4/2021

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Main class (to run)
public class Main extends JFrame implements ActionListener{

    // Constructor class fields
    private final JLabel enterExpression = new JLabel("Enter Expression");
    private final JTextField expressionField = new JTextField(20);
    private final JButton prefixToPostfix = new JButton("Prefix to Postfix");
    private final JButton postfixToPrefix = new JButton("Postfix to Prefix");
    private final JLabel resultLabel = new JLabel("Result");
    private final JTextField resultTextField = new JTextField(20);

    // Main method that sets class variables inside GUI
    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }

    // Main class constructor method for creates GUI
    public Main() {
        super("Expression Converter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 150);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel(), BorderLayout.NORTH);
        contentPane.add(middlePanel(), BorderLayout.CENTER);
        contentPane.add(bottomPanel(), BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    // Top panel for entering expression
    private JPanel topPanel(){
        JPanel topPanel = new JPanel();
        topPanel.add(enterExpression);
        topPanel.add(expressionField);
        return topPanel;
    }
    // Middle panel for converter buttons
    private JPanel middlePanel(){
        JPanel midPanel = new JPanel();
        midPanel.add(prefixToPostfix);
        midPanel.add(postfixToPrefix);
        prefixToPostfix.addActionListener(this);
        postfixToPrefix.addActionListener(this);
        return midPanel;
    }
    // Bottom panel for displaying result
    private JPanel bottomPanel(){
        JPanel botPanel = new JPanel();
        botPanel.add(resultLabel);
        botPanel.add(resultTextField) ;
        resultTextField.setEditable(false);
        return botPanel;
    }

    // Class for implementing conversions based on user input
    public void actionPerformed (ActionEvent e){
        try {
            String expressionString = expressionField.getText();
            if(e.getSource() == prefixToPostfix){
                String converted = Convert.fromPrefixToPostfix(expressionString);
                resultTextField.setText(converted);
            } else if (e.getSource() == postfixToPrefix) {
                String converted = Convert.fromPostfixToPrefix(expressionString);
                resultTextField.setText(converted);
            }
        } catch (NullPointerException | IOException | SyntaxError ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}