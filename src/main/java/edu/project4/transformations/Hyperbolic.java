package edu.project4.transformations;

import edu.project4.entity.Point;

public class Hyperbolic implements Transformation {

    @Override
    public Point apply(Point point) {

        double theta = Math.atan(point.getX() / point.getY());

        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());

        double newX = Math.sin(theta) / r;

        double newY = Math.cos(theta) * r;

        return new Point(newX, newY);
    }
}
