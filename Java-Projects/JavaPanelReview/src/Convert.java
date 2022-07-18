// Name: Kylee Fields
// Description:
// This file contains the Convert class
// which is responsible for tokenizing expressions
// and formatting the conversions

import java.io.IOException;
public class Convert {

    // Class for converting pre to post fix
    // tokenizes the string containing the prefix expression based on pseudo code
    // EX: + A B -> A B +
    public static String fromPrefixToPostfix(String expression) throws SyntaxError, IOException {
        if(!expression.equals("")){
            // Tokenize user input string and create expression stack
            List<String> expressionToArray = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();
            // Reverse user input expression
            Collections.reverse(expressionToArray);
            // Loop through array & check for operands
            for (String token:expressionToArray) {
                // Check each char for operands
                if (isOperator(token)){
                    operandStack.push(token + " ");
                } else {
                    try {
                        // Try & except to throw custom error & build stack
                        String operandOne = operandStack.pop();
                        String operandTwo = operandStack.pop();
                        String innerExpression = operandOne + operandTwo + token + " ";
                        operandStack.push(innerExpression);
                    } catch (EmptyStackException ex){
                        // Error thrown in case expression does not apply to conversion
                        throw new SyntaxError("Please check expression. Cannot call pop on empty stack.");
                    }
                }
            }
            String result = operandStack.pop();
            // Once at end of expression, return the result
            if (operandStack.empty()){
                return result;
            } else {
                // If not empty by this point, there is an error with the function
                // The stack is not empty yet it has gotten to this point.
                throw new SyntaxError("Please check expression. Stack isn't empty!");
            }

        } else {
            // Error for when there is no user input
            throw new SyntaxError("Please check expression. Expression cannot be blank.");
        }
    }

    // Class for converting post to pre fix
    // tokenizes the string containing the prefix expression based on pseudo code
    public static String fromPostfixToPrefix(String expression) throws IOException, SyntaxError {
        if (!expression.equals("")){
            // Tokenize user input string and create expression stack
            List<String> expressionToArray = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();
            // Loop for tokens containing prefix expression
            for (String token:expressionToArray) {
                // If it is an operand
                if (isOperator(token)){
                    // Push operand onto operand stack
                    operandStack.push(token + " "); }
                else {
                    // Else if it is an operator
                    try {
                        // Try & except to throw custom error & build stack with characters
                        String operandTwo = operandStack.pop();
                        String operandOne = operandStack.pop();
                        String innerExpression = token + " " + operandOne + operandTwo;
                        operandStack.push(innerExpression);
                    } catch (EmptyStackException ex){
                        // Error thrown in case expression does not apply to conversion
                        throw new SyntaxError("Trying to call pop on an empty stack! Please check expression.");
                    }
                }
            }
            String result = operandStack.pop();
            // Once at end of expression, return the result
            if (operandStack.empty()){
                return result;
            } else {
                // If not empty by this point, there is an error with the function
                // The stack is not empty yet it has gotten to this point.
                throw new SyntaxError("Please check expression. Stack isn't empty!");
            }
        } else {
            // Error for when there is no user input
            throw new SyntaxError("Please check expression. Expression cannot be blank.");
        }
    }

    // Helper method for tokenizing expressions, used by both conversion method classes
    private static List<String> tokenizeExpression(String userInput) throws IOException {

        String expression = addWhitespace(userInput);

        StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));
        // Treat the following as normal chars
        tokenizeExpression.ordinaryChar('-');
        tokenizeExpression.ordinaryChar('/');
        List<String> tokenList = new ArrayList<>();
        // Match tokens until end of stream
        while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF){
            // Number handling
            if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER){
                tokenList.add(String.valueOf((int)tokenizeExpression.nval));
            // Word handling
            } else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD) {
                tokenList.add(tokenizeExpression.sval);
            } else {
                // Handle operators
                tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
            }
        }
        return tokenList;
    }

    // description: takes a String (AKA char array) and looks at the first element and compares
    // against cases of known operators
    // Helper function for tokenizing operands from characters, returns boolean
    private static boolean isOperator(String term){
        return !switch (term.charAt(0)) {
            case '+', '-', '/', '*', '^' -> true;
            default -> false;
        };
    }

    // Helper function for adding whitespace is there is none
    private static String addWhitespace(String userInput) {
        if (!userInput.contains(" ")) {
            return userInput.replaceAll("..", "$0 ");
        }
        else {
            return userInput;
        }
    }
}