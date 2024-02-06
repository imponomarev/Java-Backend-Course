package edu.project4.transformations;

import edu.project4.entity.Point;

public class Disc implements Transformation {

    @Override
    public Point apply(Point point) {

        double theta = Math.atan(point.getX() / point.getY());

        double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());

        double newX = (theta * Math.sin(Math.PI * r)) / Math.PI;

        double newY = (theta * Math.cos(Math.PI * r)) / Math.PI;

        return new Point(newX, newY);
    }
}
