package org.example.domain;


import org.example.util.ValidationUtils;

public class Service {

    private static final int MAX_ID = 10;
    private static final int MAX_VARIATION_ID = 3;

    private final Integer id;
    private final Integer variationId;

    public Service(Integer id, Integer variationId) {
        this.id = ValidationUtils.validateId(id, MAX_ID, "Service ID");
        this.variationId = ValidationUtils.validateId(variationId, MAX_VARIATION_ID, "Service Variation ID");
    }

    public Integer getId() {
        return id;
    }

    public Integer getVariationId() {
        return variationId;
    }
}

