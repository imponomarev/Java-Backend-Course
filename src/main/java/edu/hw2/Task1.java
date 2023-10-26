package edu.hw2;

public class Task1 {
    sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {
            @Override
            public double evaluate() {
                return value;
            }

            @Override
            public String toString() {
                return String.valueOf(value);
            }

        }

        record Negate(Expr operand) implements Expr {
            @Override
            public double evaluate() {
                return -operand.evaluate();
            }

            @Override
            public String toString() {
                return "-(" + operand + ")";
            }

        }

        record Exponent(Expr base, double power) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(base().evaluate(), power);
            }

            @Override
            public String toString() {
                return "(" + base + ")^" + power;
            }
        }

        record Addition(Expr operand1, Expr operand2) implements Expr {
            @Override
            public double evaluate() {
                return operand1.evaluate() + operand2.evaluate();
            }

            @Override
            public String toString() {
                return "(" + operand1 + " + " + operand2 + ")";
            }
        }

        record Multiplication(Expr operand1, Expr operand2) implements Expr {
            @Override
            public double evaluate() {
                return operand1.evaluate() * operand2.evaluate();
            }

            @Override
            public String toString() {
                return "(" + operand1 + " * " + operand2 + ")";
            }
        }
    }
}
