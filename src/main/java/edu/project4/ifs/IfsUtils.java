package edu.project4.ifs;


import java.awt.Color;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.entity.Point;
import edu.project4.entity.Rectangle;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Rotation;
import edu.project4.transformations.Transformation;
import org.apache.commons.math3.util.Pair;

public class IfsUtils {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private IfsUtils() {}

    public static <T> T peekRandom(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static <T, M> Pair<T, M> peekRandomPair(T[] arr1, M[] arr2) {

        int index = RANDOM.nextInt(
            Math.min(arr1.length, arr2.length));

        return new Pair<>(arr1[index], arr2[index]);
    }

    public static void setPixelColor(Pixel pixel, Color color) {

        if (pixel.getHitCount() == 0) {

            pixel.setRed(color.getRed());
            pixel.setGreen(color.getGreen());
            pixel.setBlue(color.getBlue());

        } else {

            pixel.setRed((pixel.getRed() + color.getRed()) / 2);
            pixel.setGreen((pixel.getGreen() + color.getGreen()) / 2);
            pixel.setBlue((pixel.getBlue() + color.getBlue()) / 2);

        }

    }


    public static Point getRandomPoint(Rectangle drawArea) {

        double x = drawArea.getX() + RANDOM.nextDouble() * drawArea.getWidth();
        double y = drawArea.getY() + RANDOM.nextDouble() * drawArea.getHeight();

        return new Point(x, y);

    }

    public record Affine(
        AffineTransformation transformation,
        Color color
    ) {
        public Affine(Pair<AffineTransformation, AffineColor> pair) {
            this(pair.getFirst(), pair.getSecond().color());
        }
    }


    public static void subIterate(
        List<Transformation> fractalTransformation,
        int iterationPerSample, int symmetry,
        Rectangle drawArea, FractalImage canvas,
        AffineTransformation[] affineTransformations,
        AffineColor[] affineColors,
        int start, CountDownLatch latch
    ) {
        Point pw = IfsUtils.getRandomPoint(drawArea);

        for (int step = start; step < iterationPerSample; step++) {

            IfsUtils.Affine affine =
                new IfsUtils.Affine(IfsUtils.peekRandomPair(affineTransformations, affineColors));


            Transformation transformation = IfsUtils.peekRandom(fractalTransformation);

            pw = affine.transformation.apply(pw);
            pw = transformation.apply(pw);

            if (step <= 0) {
                continue;
            }

            double theta = 0;

            for (int s = 0; s < symmetry; s++) {

                theta += 2 * Math.PI / symmetry;
                Rotation rotation = new Rotation(theta);
                Point pwr = rotation.apply(pw);

                if (!drawArea.contains(pwr.getX(), pwr.getY())) {
                    continue;
                }

                Pixel pixel = canvas.pixel(
                    (int) ((pwr.getX() - drawArea.getX()) * canvas.getWidth() / drawArea.getWidth()),
                    (int) ((pwr.getY() - drawArea.getY()) * canvas.getHeight() / drawArea.getHeight())
                );

                if (pixel == null) {
                    continue;
                }

                IfsUtils.setPixelColor(pixel, affine.color);

                pixel.incrementHitCount();
            }

            if (latch != null) {
                latch.countDown();
            }
        }
    }
}
