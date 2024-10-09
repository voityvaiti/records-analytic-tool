package org.example.domain;


import org.example.util.ValidationUtils;

public record Service(Integer id, Integer variationId) {

    private static final int MAX_ID = 10;
    private static final int MAX_VARIATION_ID = 3;

    public Service(Integer id, Integer variationId) {
        this.id = ValidationUtils.validateId(id, MAX_ID, "Service ID");
        this.variationId = ValidationUtils.validateId(variationId, MAX_VARIATION_ID, "Service Variation ID");
    }
}

