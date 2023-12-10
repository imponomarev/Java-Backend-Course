package edu.project4.transformations;

import edu.project4.entity.Point;

public class Rotation implements Transformation {

    private final double sin;
    private final double cos;

    public Rotation(double alpha) {
        this.sin = Math.sin(alpha);
        this.cos = Math.cos(alpha);
    }

    @Override
    public Point apply(Point point) {

        double newX = cos * point.getX() - sin * point.getY();
        double newY = sin * point.getX() + cos * point.getY();

        return new Point(newX, newY);

    }
}
