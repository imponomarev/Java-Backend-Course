package edu.project4.correction;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import java.util.HashMap;
import java.util.Map;

public class GammaCorrection implements ImageProcessor {

    private static final double GAMMA = 1.9;

    @Override
    public void process(FractalImage image) {

        double maximum = 0;
        Map<Pixel, Double> normalizations = new HashMap<>();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Pixel pixel = image.pixel(x, y);

                if (pixel.getHitCount() != 0) {
                    normalizations.put(pixel, Math.log10(pixel.getHitCount()));
                    maximum = Math.max(maximum, normalizations.get(pixel));
                }
            }
        }

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Pixel pixel = image.pixel(x, y);

                var normalization = normalizations.getOrDefault(pixel, 0.0) / maximum;

                pixel.setRed(
                    (int) (pixel.getRed() * Math.pow(normalization, (1.0 / GAMMA)))
                );

                pixel.setGreen(
                    (int) (pixel.getGreen() * Math.pow(normalization, (1.0 / GAMMA)))
                );

                pixel.setBlue(
                    (int) (pixel.getBlue() * Math.pow(normalization, (1.0 / GAMMA)))
                );
            }
        }

    }
}
