package edu.hw4;

import edu.hw4.validationTask19.ValidationError;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task20 {

    public Map<String, String> beautifyValidation(Map<String, Set<ValidationError>> errorsMap) {
        Map<String, String> beautifullMap = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : errorsMap.entrySet()) {
            String key = entry.getKey();
            Set<ValidationError> value = entry.getValue();

            String errors = value
                .stream()
                .map(ValidationError::getNameOfField)
                .toList()
                .toString();
            beautifullMap.put(key, errors);
        }
        return beautifullMap;
    }
}
