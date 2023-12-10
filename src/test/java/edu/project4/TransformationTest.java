package edu.project4;

import edu.project4.entity.Point;
import edu.project4.transformations.Diamond;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Ex;
import edu.project4.transformations.Heart;
import edu.project4.transformations.Horseshoe;
import edu.project4.transformations.Hyperbolic;
import edu.project4.transformations.Popcorn;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TransformationTest {

    @Test
    void heartTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation heart = new Heart();

        Point after = heart.apply(before);

        assertThat(after.getX()).isEqualTo(1.2671621313307992);
        assertThat(after.getY()).isEqualTo(-0.6279332232978174);

    }

    @Test
    void sphericalTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation spherical = new Spherical();

        Point after = spherical.apply(before);

        assertThat(after.getX()).isEqualTo(0.5);
        assertThat(after.getY()).isEqualTo(0.5);

    }


    @Test
    void diskTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation disk = new Disc();

        Point after = disk.apply(before);

        assertThat(after.getX()).isEqualTo(-0.24097563321246931);
        assertThat(after.getY()).isEqualTo(-0.06656383551035391);

    }

    @Test
    void diamondTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation diamond = new Diamond();

        Point after = diamond.apply(before);

        assertThat(after.getX()).isEqualTo(0.11026884405188132);
        assertThat(after.getY()).isEqualTo(0.6984559986366085);

    }


    @Test
    void exTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation ex = new Ex();

        Point after = ex.apply(before);

        assertThat(after.getX()).isEqualTo(1.4960542625438735);
        assertThat(after.getY()).isEqualTo(6.280369834735101E-16);

    }

    @Test
    void hyperbolicTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation hyperbolic = new Hyperbolic();

        Point after = hyperbolic.apply(before);

        assertThat(after.getX()).isEqualTo(0.49999999999999994);
        assertThat(after.getY()).isEqualTo(1.0000000000000002);

    }

    @Test
    void horseshoeTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation horseshoe = new Horseshoe();

        Point after = horseshoe.apply(before);

        assertThat(after.getX()).isEqualTo(0.0);
        assertThat(after.getY()).isEqualTo(1.414213562373095);

    }

    @Test
    void popcornTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation popcorn = new Popcorn(0.0, 1.0);

        Point after = popcorn.apply(before);

        assertThat(after.getX()).isEqualTo(1.0);
        assertThat(after.getY()).isEqualTo(0.8579357128540236);

    }


    @Test
    void swirlTransformationTest() {

        Point before = new Point(1.0, 1.0);

        Transformation swirl = new Swirl();

        Point after = swirl.apply(before);

        assertThat(after.getX()).isEqualTo(1.325444263372824);
        assertThat(after.getY()).isEqualTo(0.4931505902785393);

    }



}

