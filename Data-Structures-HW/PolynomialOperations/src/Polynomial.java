// Name: Kylee Fields
// Project: CMSC 350 Project 2
// Date: 11/14/2021
// Description: Polynomial class

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Polynomial implements Iterable<Object>, Comparable<Polynomial> {

    Comparator<Polynomial> compare;
    private Term head;

    // Class constructor for setting fields in Polynomial
    // Takes a string of coefficients and exponents from file
    // Splits string into individual nodes and creates linked list
    public Polynomial(String fromFile) {
        head = null;
        Scanner termScanner = new Scanner(fromFile);
        try {
            while (termScanner.hasNext()) {
                addTerm(termScanner.nextDouble(), termScanner.nextInt());
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new invalidPolynomialSyntax("Syntax error, please check your polynomials!");
        }
    }

    // Class for adding terms to linked list
    // If head is null or new term is null, sets new term to next null term.next
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) {
            throw new invalidPolynomialSyntax("No negative exponents, please check inputs!");
        }
        Term current = head;
        if (current == null) {
            head = new Term(coefficient, exponent);
            head.next = null;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Term(coefficient, exponent);
        }

    }

    // Override method to compare polynomials
    // First compares exponents, then coefficients.
    // Positive value indicates the argument polynomial i larger
    @Override
    public int compareTo(Polynomial otherPoly) {
        Term thisCurrent = this.head;
        Term otherCurrent = otherPoly.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else {
                    return +1;
                }
            }
            // Resetting the values outside of the loop
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        if (thisCurrent == null && otherCurrent == null) {
            return 0;
        }
        if (thisCurrent == null) {
            return -1;
        } else {
            return +1;
        }
    }

    // Class method for comparing exponents in two polynomials, checks order
    public int compareExponents(Polynomial poly2) {
        Term thisPolyTerm = this.head;
        Term otherPolyTerm = poly2.head;
        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }
        if (thisPolyTerm == null && otherPolyTerm == null) {
            return 0;
        }
        if (otherPolyTerm == null) {
            return +1;
        } else {
            return -1;
        }
    }

    public Polynomial() {
        compare = Polynomial::compareExponents;
    }

    public Polynomial(Comparator<Polynomial> compare) {
        this.compare = compare;
    }

    // Override class method to traverse polynomial via iterator
    @Override
    public Iterator<Object> iterator() {
        return new Iterator() {

            private Term current = getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Term next() {
                Term data = current;
                current = current.next;
                return data;
            }
        };
    }


    // Override class method to print string polynomial
    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();
        if (head.coefficient > 0) {
            expressionBuilder.append(head);
        } else {
            expressionBuilder.append(" - ").append(head);
        }
        for (Term tmp = head.next; tmp != null; tmp = tmp.next) {
            if (tmp.coefficient < 0) {
                expressionBuilder.append(" - ").append(tmp);
            } else {
                expressionBuilder.append(" + ").append(tmp);
            }
        }
        return expressionBuilder.toString();

    }

    static class Term {
        private final double coefficient;
        private final int exponent;
        private Term next;

        private Term(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;
        }

        private int getExponent() {
            return this.exponent;
        }

        private double getCoefficient() {
            return this.coefficient;
        }

        private Term getNext() {
            return next;
        }

        @Override
        public String toString() {
            String termString = String.format("%.1f", Math.abs(coefficient));
            if (exponent == 0) {
                return termString;
            } else if (exponent == 1) {
                return termString + "x";
            } else {
                return termString + "x^" + exponent;
            }
        }
    }

    private Term getHead() {
        return head;
    }
}