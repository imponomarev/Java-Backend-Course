package edu.project4.transformations;

import edu.project4.entity.Point;

public class Diamond implements Transformation {

    @Override
    public Point apply(Point point) {

        double theta = Math.atan(point.getX() / point.getY());

        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());

        double newX = Math.sin(theta) * Math.cos(r);

        double newY = Math.cos(theta) * Math.sin(r);

        return new Point(newX, newY);

    }
}

