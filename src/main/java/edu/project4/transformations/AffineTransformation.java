package edu.project4.transformations;

import edu.project4.entity.Point;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation implements Transformation {

    private final Transformation offset;

    private final Transformation linear;

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();


    public AffineTransformation(double a, double b, double c, double d,
        double e, double f) {
        this.linear = new LinearTransformation(a, b, d, e);
        this.offset = new Offset(c, f);
    }

    public static AffineTransformation create() {

        while(true) {
            double a = RANDOM.nextDouble(-1, 1);
            double b = RANDOM.nextDouble(-1, 1);
            double c = RANDOM.nextDouble(-1, 1);
            double d = RANDOM.nextDouble(-1, 1);
            double e = RANDOM.nextDouble(-1, 1);
            double f = RANDOM.nextDouble(-1, 1);

            if (
                (Math.pow(a, 2) + Math.pow(d, 2) < 1)
                    && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
                    && ((Math.pow(a, 2) + Math.pow(d, 2) + Math.pow(b, 2) + Math.pow(e, 2))
                    < 1 + (a * e - b * d) * (a * e - b * d))

            ) {
                return new AffineTransformation(a, b, c, d, e, f);
            }
        }
    }

    public static AffineTransformation[] getRandomAffineTransformations(int samples) {

        AffineTransformation[] transformations = new AffineTransformation[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = create();
        }
        return transformations;

    }

    @Override
    public Point apply(Point point) {

        Point transformed = linear.apply(point);
        return offset.apply(transformed);

    }



}
