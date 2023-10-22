package edu.hw2;

public class Task2 {

    public static class Rectangle {
        protected int width;
        protected int height;

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        protected void setWidth(int width) {
            this.width = width;
        }

        protected void setHeight(int height) {
            this.height = height;
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        public Square(int side) {
            super(side, side);
        }
    }
}


