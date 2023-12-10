package edu.project4;

import edu.project4.correction.GammaCorrection;
import edu.project4.correction.ImageProcessor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rectangle;
import edu.project4.ifs.IFS;
import edu.project4.ifs.IfsMultiThread;
import edu.project4.ifs.IfsSingleThread;
import edu.project4.painter.Painter;
import edu.project4.transformations.Diamond;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Ex;
import edu.project4.transformations.Heart;
import edu.project4.transformations.Horseshoe;
import edu.project4.transformations.Hyperbolic;
import edu.project4.transformations.Popcorn;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final int X = -4;

    private static final int Y = -3;

    private static final int WIDTH_AREA = 8;

    private static final int HEIGHT_AREA = 6;

    private static final int IMAGE_WIDTH = 1920;

    private static final int IMAGE_HEIGHT = 1080;

    private static final int SAMPLES = 8;

    private static final int ITER_PER_SAMPLE = 1000_000;

    private static final int SYMMETRY = 3;

    private Main() {}

    private static final Logger LOGGER = LogManager.getLogger();


    public static void main(String[] args) {

        Painter painter = new Painter();

        Rectangle rect = new Rectangle(X, Y, WIDTH_AREA, HEIGHT_AREA);

        ImageProcessor processor = new GammaCorrection();

        FractalImage fractalImage = FractalImage.create(IMAGE_WIDTH, IMAGE_HEIGHT);

        IFS renderer = new IfsSingleThread();

        fractalImage = renderer.iterate(
            List.of(
                new Popcorn(0.0, 1.0),
                new Spherical(),
                new Swirl(),
                new Horseshoe(),
                new Disc(),
                new Heart(),
                new Hyperbolic(),
                new Diamond(),
                new Ex()
            ),
        SAMPLES,
        ITER_PER_SAMPLE,
        SYMMETRY,
        rect,
        fractalImage
        );

        try {

            painter.save(fractalImage, Path.of("src/main/resources/image.png"));

            processor.process(fractalImage);

            painter.save(fractalImage, Path.of("src/main/resources/image-corrected.png"));


        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }


        FractalImage fractalImage2 = FractalImage.create(IMAGE_WIDTH, IMAGE_HEIGHT);

        IFS rendererMt = new IfsMultiThread();

        fractalImage2 = rendererMt.iterate(
            List.of(
                new Popcorn(0.0, 1.0),
                new Spherical(),
                new Swirl(),
                new Horseshoe(),
                new Disc(),
                new Heart(),
                new Hyperbolic(),
                new Diamond(),
                new Ex()
            ),
            SAMPLES,
            ITER_PER_SAMPLE,
            SYMMETRY,
            rect,
            fractalImage2
        );

        try {

            painter.save(fractalImage2, Path.of("src/main/resources/image-mt.png"));

            processor.process(fractalImage2);

            painter.save(fractalImage2, Path.of("src/main/resources/image-mt-corrected.png"));

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
