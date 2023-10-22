package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();

        public record Constant(double value) implements Expr {
            @Override
            public double evaluate() {
                return value;
            }

            @Override
            public String toString() {
                return String.valueOf(value);
            }

        }
        public record Negate(Expr operand) implements Expr {
            @Override
            public double evaluate() {
                return -operand.evaluate();
            }

            @Override
            public String toString() {
                return "-(" + operand + ")";
            }

        }
        public record Exponent(Expr base, double power) implements Expr {
            @Override
            public double evaluate() {
                double result = base.evaluate();
                for (int i = 1; i < power; i++) {
                    result *= base.evaluate();
                }
                return result;
            }

            @Override
            public String toString() {
                return "(" + base + ")^" + power;
            }
        }
        public record Addition(Expr operand1, Expr operand2) implements Expr {
            @Override
            public double evaluate() {
                return operand1.evaluate() + operand2.evaluate();
            }

            @Override
            public String toString() {
                return "(" + operand1 + " + " + operand2 + ")";
            }
        }
        public record Multiplication(Expr operand1, Expr operand2) implements Expr {
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
