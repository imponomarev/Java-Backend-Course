package edu.hw4.validationTask19;

import java.util.Objects;

public abstract class ValidatorError {

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

        ValidatorError other = (ValidatorError) obj;
        return Objects.equals(getNameOfField(), other.getNameOfField());
    }
}
