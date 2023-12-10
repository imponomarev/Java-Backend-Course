package edu.project4.transformations;

import edu.project4.entity.Point;


public class Offset implements Transformation {

    private final Point delta;

    public Offset(double x, double y) {
        this.delta = new Point(x, y);
    }

    public Offset(Point delta) {
        this.delta = delta;
    }

    @Override
    public Point apply(Point point) {

        double newX = point.getX() + delta.getX();
        double newY = point.getY() + delta.getY();

        return new Point(newX, newY);

    }

}
