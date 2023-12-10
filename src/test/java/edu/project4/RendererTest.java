package edu.project4;

import edu.project4.correction.GammaCorrection;
import edu.project4.correction.ImageProcessor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.entity.Rectangle;
import edu.project4.ifs.IFS;
import edu.project4.ifs.IfsMultiThread;
import edu.project4.ifs.IfsSingleThread;
import edu.project4.transformations.Popcorn;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class RendererTest {

    private static final int SAMPLES = 8;

    private static final int ITER_PER_SAMPLES = 100_000;

    private static final int SYMMETRY = 3;

    private static final List<Transformation> VARIATIONS = List.of(
        new Popcorn(0.0, 1.0),
        new Spherical(),
        new Swirl()
    );


    @Test
    void singleThreadRenderTest() {

        Rectangle rect = new Rectangle(-4, -3, 8, 6);

        ImageProcessor processor = new GammaCorrection();

        FractalImage fractalImage = FractalImage.create(1920, 1080);

        FractalImage oldFractalImage = fractalImage;

        assertThat(calculatePixels(oldFractalImage)).isFalse();

        IFS renderer = new IfsSingleThread();


        fractalImage = renderer.iterate(
            VARIATIONS,
            SAMPLES,
            ITER_PER_SAMPLES,
            SYMMETRY,
            rect,
            fractalImage
        );

        processor.process(fractalImage);

        assertThat(oldFractalImage.getPixelData().length).isEqualTo(fractalImage.getPixelData().length);
        assertThat(calculatePixels(fractalImage)).isTrue();

    }


    @Test
    void multiThreadRenderTest() {

        Rectangle rect = new Rectangle(-4, -3, 8, 6);

        ImageProcessor processor = new GammaCorrection();

        FractalImage fractalImage = FractalImage.create(1920, 1080);

        FractalImage oldFractalImage = fractalImage;

        assertThat(calculatePixels(oldFractalImage)).isFalse();

        IFS renderer = new IfsMultiThread();


        fractalImage = renderer.iterate(
            VARIATIONS,
            SAMPLES,
            ITER_PER_SAMPLES,
            SYMMETRY,
            rect,
            fractalImage
        );

        processor.process(fractalImage);

        assertThat(oldFractalImage.getPixelData().length).isEqualTo(fractalImage.getPixelData().length);
        assertThat(calculatePixels(fractalImage)).isTrue();

    }





    private boolean calculatePixels(FractalImage fractalImage) {

        for (Pixel[] pixels : fractalImage.getPixelData()) {
            for (Pixel pixel : pixels) {
                if ((pixel.getRed() != 0) || (pixel.getGreen() != 0) || (pixel.getBlue() != 0)) {
                    return true;
                }
            }
        }
        return false;

    }
}
