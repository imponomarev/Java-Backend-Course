package edu.project4.transformations;

import edu.project4.entity.Point;

public class Swirl implements Transformation {

    @Override
    public Point apply(Point point) {

        double rSquared = point.getX() * point.getX() + point.getY() * point.getY();

        double cos = Math.cos(rSquared);

        double sin = Math.sin(rSquared);

        double newX = point.getX() * sin - point.getY() * cos;

        double newY = point.getX() * sin + point.getY() * cos;

        return new Point(newX, newY);

    }
}



