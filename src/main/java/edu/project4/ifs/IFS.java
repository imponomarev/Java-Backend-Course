package edu.project4.ifs;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Rectangle;
import edu.project4.transformations.Transformation;
import java.util.List;


public interface IFS {

    FractalImage iterate(
        List<Transformation> fractalTransformation,
        int samples,
        int iterationPerSample,
        int symmetry,
        Rectangle drawArea,
        FractalImage canvas
    );

}
