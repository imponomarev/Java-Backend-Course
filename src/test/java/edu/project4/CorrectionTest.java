package edu.project4;

import edu.project4.correction.GammaCorrection;
import edu.project4.correction.ImageProcessor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CorrectionTest {

    private static final int WIDTH = 1920;

    private static final int HEIGHT = 1080;



    @Test
    void gammaCorrectionTest() {

        Pixel[][] pixelsBefore = createPixels();

        FractalImage afterCorrection = FractalImage.create(WIDTH, HEIGHT);

        ImageProcessor processor = new GammaCorrection();

        processor.process(afterCorrection);

        var pixelsAfter = afterCorrection.getPixelData();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                assertThat(pixelsBefore[i][j].getRed() >= pixelsAfter[i][j].getRed()).isTrue();

                assertThat(pixelsBefore[i][j].getGreen() >= pixelsAfter[i][j].getGreen()).isTrue();

                assertThat(pixelsBefore[i][j].getBlue() >= pixelsAfter[i][j].getBlue()).isTrue();

            }
        }
    }

    private Pixel[][] createPixels() {

        Pixel[][] pixels = new Pixel[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                pixels[i][j] = new Pixel(50, 50, 50, 1);
            }
        }
        return pixels;
    }
}
