package edu.project4.ifs;

import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rectangle;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class IfsSingleThread implements IFS {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int START = -20;

    private static final int SEC = 1_000_000_000;

    private AffineTransformation[] affineTransformations = null;

    private AffineColor[] affineColors = null;

    @Override
    public FractalImage iterate(
        List<Transformation> fractalTransformations,
        int samples, int iterationPerSample, int symmetry,
        Rectangle drawArea, FractalImage canvas
    ) {
        if (affineTransformations == null) {
            affineTransformations = AffineTransformation.getRandomAffineTransformations(samples);
        }

        if (affineColors == null) {
            affineColors = AffineColor.getRandomAffineColors(samples);
        }

        var startTime = System.nanoTime();

        for (int num = 0; num < samples; num++) {
            IfsUtils.subIterate(
                fractalTransformations,
                iterationPerSample, symmetry,
                drawArea, canvas,
                affineTransformations, affineColors,
                START, null
            );
        }

        var endTime = System.nanoTime();

        LOGGER.info("Single thread completed the generation in: {}", (endTime - startTime) / SEC);

        return canvas;
    }
}
