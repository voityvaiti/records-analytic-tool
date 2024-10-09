package org.example.domain;


import org.example.util.ValidationUtils;

public record Question(Integer typeId, Integer categoryId, Integer subCategoryId) {

    private static final int MAX_TYPE_ID = 10;
    private static final int MAX_CATEGORY_ID = 20;
    private static final int MAX_SUBCATEGORY_ID = 5;

    public Question(Integer typeId, Integer categoryId, Integer subCategoryId) {
        this.typeId = ValidationUtils.validateId(typeId, MAX_TYPE_ID, "Question Type ID");
        this.categoryId = ValidationUtils.validateId(categoryId, MAX_CATEGORY_ID, "Category ID");
        this.subCategoryId = ValidationUtils.validateId(subCategoryId, MAX_SUBCATEGORY_ID, "Sub-Category ID");
    }
}

