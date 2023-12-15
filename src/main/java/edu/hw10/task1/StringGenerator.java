package edu.hw10.task1;

import edu.hw10.task1.annotation.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringGenerator implements FieldGenerator {

    private static final Random random = new Random();

    private static final Logger LOGGER = LogManager.getLogger();

    public Object generate(Annotation[] annotations) {

        boolean isNotNull = false;

        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            }
        }

        if (!isNotNull) {
            return null;
        }

        try {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                char c = (char) (random.nextInt(26) + 'a');
                sb.append(c);
            }

            return sb.toString();

        } catch (Exception e) {
            LOGGER.error("Error generating random string: {}", e.getMessage());
            return "";
        }

    }
}

