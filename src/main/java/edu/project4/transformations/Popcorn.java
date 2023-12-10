package edu.project4.transformations;

import edu.project4.entity.Point;

public class Popcorn implements Transformation {

    private double c;
    private double f;
    private static final int POPCORN_VALUE = 3;

    public Popcorn(double c, double f) {
        this.c = c;
        this.f = f;
    }

    @Override
    public Point apply(Point point) {

        double newX = point.getX() + c * Math.sin(Math.tan(POPCORN_VALUE * point.getY()));

        double newY = point.getY() + f * Math.sin(Math.tan(POPCORN_VALUE * point.getX()));

        return new Point(newX, newY);

    }
}
