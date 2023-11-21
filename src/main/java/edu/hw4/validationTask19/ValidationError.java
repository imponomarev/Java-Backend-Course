package edu.hw4.validationTask19;

import java.util.Objects;

public abstract class ValidationError {

    public abstract String getNameOfField();

    @Override
    public int hashCode() {
        return Objects.hashCode(getNameOfField());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ValidationError other = (ValidationError) obj;
        return Objects.equals(getNameOfField(), other.getNameOfField());
    }
}
