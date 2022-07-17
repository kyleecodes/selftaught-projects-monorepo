// Name: Kylee Fields
// Project: CMSC 350 Project 3
// Date: 11/30/2021
// Description: Binary Tree class file

import org.jetbrains.annotations.NotNull;
import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {

    Node parent;
    Node child;

    // Constructor for BinaryTree, utilizes regex to create array from string input
    // parses input into a stack
    public BinaryTree(@NotNull String input) throws InvalidTreeSyntax {
        Stack<Node> nodeStack = new Stack<>();
        String[] inputArray = input.substring(1, input.length() - 1).split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        parent = new Node(inputArray[0]);
        for (int i = 1; i < inputArray.length - 1; i++) {
            if (inputArray[i].equals("(")) {
                nodeStack.push(parent);
                if (child != null) {
                    parent = child;
                }
            } else if (inputArray[i].equals(")")) {
                try {
                    child = parent;
                    parent = nodeStack.pop();
                } catch (EmptyStackException emptyStack) {
                    throw new InvalidTreeSyntax("Incorrect parenthesis!");
                }
            } else {
                child = new Node(inputArray[i]);
                if (parent != null) {
                    parent.addChild(child);
                }
            }
        }
        if (this.recNodes(parent) * 3 != input.length()) throw new InvalidTreeSyntax("Incorrect Syntax");
    }

    // Boolean method to determine if absolute difference b/t branches is at the most 1 via recursion
    public boolean isBalanced() {
        return recIsBalanced(this.parent);
    }

    private boolean recIsBalanced(Node root) {
        // Base case
        if (root == null) {
            return true;
        }
        // Return true if the absolute difference is at most 1
        return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) && (recIsBalanced(root.left) && recIsBalanced(root.right)); // and calls recursively
    }


    // Class boolean method to check if tree has max nodes for height via recursive method
    public boolean isFull() {
        return recIsFull(this.parent, recHeight(this.parent), 0);
    }

    private boolean recIsFull(Node root, int height, int index) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return (height == index + 1);
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return recIsFull(root.left, height, index + 1) && recIsFull(root.right, height, index + 1);

    }

    // Class boolean method to determine is every node in tree has either 2 or 0 children
    public boolean isProper() {
        return recIsProper(this.parent);
    }

    private boolean recIsProper(Node root) {
        if (root == null) {
            return true;
        }
        return ((root.left != null || root.right == null) && (root.left == null || root.right != null)) && (recIsProper(root.left) && recIsProper(root.right)); // and calling recursively
    }

    // Class method to find height of binary tree via recursion
    public int height() {
        return recHeight(this.parent) - 1;
    }

    // Class method to substract root from height
    private int recHeight(Node root) {
        return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
    }

    // Class methods for calculating number of nodes in a tree
    public int nodes() {
        return recNodes(this.parent);
    }

    private int recNodes(Node root) {
        return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
    }

    // Class methods to determine if binary tree is in order via recursion
    public String inOrder() {
        return recInOrder(this.parent);
    }

    private String recInOrder(Node root) {
        return (root == null) ? "" : "(" + recInOrder(root.left) + root.info + recInOrder(root.right) + ")";
    }

    // Class override method for printing strings
    @Override
    public String toString() {
        return parent.toString();
    }

    // Nested node class for storing info on nodes
    public static class Node {
        private final String info;
        private Node left;
        private Node right;

        public Node(String info) {
            this.info = info;
        }

        private void addChild(Node child) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.setLeft(child);
            } else if (this.right == null) {
                this.setRight(child);
            } else {
                throw new InvalidTreeSyntax("Nodes can only have 2 children!");
            }
        }

        // Setters and getters for nodes
        private void setLeft(Node newLeft) {
            left = newLeft;
        }

        private void setRight(Node newRight) {
            right = newRight;
        }

        // Print nodes
        @Override
        public String toString() {
            return toString(this);
        }

        private static String toString(Node root) {
            return (root == null) ? "" : "(" + root.info + toString(root.left) + toString(root.right) + ")";
        }
    }
}