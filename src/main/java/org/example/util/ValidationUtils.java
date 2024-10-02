package org.example.util;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static Integer validateId(Integer id, int max, String fieldName) {
        if (id != null && (id < 1 || id > max)) {
            throw new IllegalArgumentException(fieldName + " must be between " + 1 + " and " + max + ".");
        }
        return id;
    }
}

