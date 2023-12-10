package edu.project4.ifs;

import edu.project4.entity.AffineColor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rectangle;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IfsMultiThread implements IFS {

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

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        if (affineTransformations == null) {
            affineTransformations = AffineTransformation.getRandomAffineTransformations(samples);
        }

        if (affineColors == null) {
            affineColors = AffineColor.getRandomAffineColors(samples);
        }

        CountDownLatch latch = new CountDownLatch(samples);

        var startTime = System.nanoTime();

        for (int num = 0; num < samples; num++) {
            executor.submit(() -> {
                IfsUtils.subIterate(
                    fractalTransformations,
                    iterationPerSample, symmetry,
                    drawArea, canvas,
                    affineTransformations, affineColors,
                    START, latch
                );
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        var endTime = System.nanoTime();

        executor.shutdownNow();

        LOGGER.info("Many threads have completed the generation in: {}", (endTime - startTime) / SEC);

        return canvas;
    }


}
