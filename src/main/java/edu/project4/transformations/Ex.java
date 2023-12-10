package edu.project4.transformations;

import edu.project4.entity.Point;

public class Ex implements Transformation {

    @Override
    public Point apply(Point point) {

        double theta = Math.atan(point.getX() / point.getY());

        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());

        double p0 = Math.sin(theta + r);

        double p1 = Math.cos(theta - r);

        double newX = r * (Math.pow(p0, 3) + Math.pow(p1, 3));

        double newY = r * (Math.pow(p0, 3) - Math.pow(p1, 3));

        return new Point(newX, newY);
    }
}
