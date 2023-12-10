package edu.project4.transformations;

import edu.project4.entity.Point;

public class Spherical implements Transformation {

    @Override
    public Point apply(Point point) {

        double rSquared = point.getX() * point.getX() + point.getY() * point.getY();
        double newX = point.getX() / rSquared;
        double newY = point.getY() / rSquared;

        return new Point(newX, newY);

    }
}
