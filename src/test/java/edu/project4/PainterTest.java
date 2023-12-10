package edu.project4;

import edu.project4.correction.GammaCorrection;
import edu.project4.correction.ImageProcessor;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rectangle;
import edu.project4.ifs.IFS;
import edu.project4.ifs.IfsSingleThread;
import edu.project4.painter.Painter;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Heart;
import edu.project4.transformations.Horseshoe;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class PainterTest {

    private static final Path PATH = Paths.get("src/main/resources/test-image.png");

    private static final int SAMPLES = 8;

    private static final int ITER_PER_SAMPLES = 1_000_000;

    private static final int SYMMETRY = 3;

    @Test
    void saveImageTest() throws IOException {

        if (Files.exists(PATH)) {
            Files.delete(PATH);
        }

        Painter painter = new Painter();

        Rectangle rect = new Rectangle(-4, -3, 8, 6);

        ImageProcessor processor = new GammaCorrection();

        FractalImage fractalImage = FractalImage.create(1920, 1080);

        IFS renderer = new IfsSingleThread();

        fractalImage = renderer.iterate(
            List.of(

                new Horseshoe(),
                new Disc(),
                new Heart()

            ),
            SAMPLES,
            ITER_PER_SAMPLES,
            SYMMETRY,
            rect,
            fractalImage
        );

        assertThat(Files.exists(PATH)).isFalse();

        processor.process(fractalImage);

        painter.save(fractalImage, PATH);

        assertThat(Files.exists(PATH)).isTrue();

    }
}
