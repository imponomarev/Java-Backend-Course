package edu.project4.transformations;

import edu.project4.entity.Point;

public class LinearTransformation implements Transformation {

    private final Point x;
    private final Point y;

    public LinearTransformation(double a, double b, double c, double d) {
        this.x = new Point(a, b);
        this.y = new Point(c, d);
    }

    @Override
    public Point apply(Point point) {

        double newX = point.getX() * x.getX() + point.getY() * y.getX();
        double newY = point.getX() * x.getY() + point.getY() * y.getY();

        return new Point(newX, newY);

    }
}


