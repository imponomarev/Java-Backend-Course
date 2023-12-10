package edu.project4.transformations;

import edu.project4.entity.Point;


public class Horseshoe implements Transformation {

    @Override
    public Point apply(Point point) {

        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());

        double newX = ((point.getX() - point.getY()) * (point.getX() + point.getY())) / r;

        double newY = 2.0 * point.getX() * point.getY() / r;

        return new Point(newX, newY);

    }
}
