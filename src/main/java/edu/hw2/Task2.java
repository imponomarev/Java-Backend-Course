package edu.hw2;

public class Task2 {

    public static class Rectangle {
        protected int width;
        protected int height;

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Rectangle() {

        }

        public Rectangle setWidth(int width) {
            return new Rectangle(width, height);
        }

        public Rectangle setHeight(int height) {
            return new Rectangle(width, height);
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        public Square(int side) {
            super(side, side);
        }

        public Square() {

        }

        @Override
        public Rectangle setWidth(int width) {
            if (width != getHeight()) {
                return new Rectangle(width, getHeight());
            }
            return new Square(width);
        }

        @Override
        public Rectangle setHeight(int height) {
            if (height != getWidth()) {
                return new Rectangle(getWidth(), height);
            }
            return new Square(height);
        }
    }
}


