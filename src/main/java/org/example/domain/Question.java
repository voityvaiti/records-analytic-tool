package org.example.domain;


import org.example.util.ValidationUtils;

public class Question {

    private static final int MAX_TYPE_ID = 10;
    private static final int MAX_CATEGORY_ID = 20;
    private static final int MAX_SUBCATEGORY_ID = 5;

    private final Integer typeId;
    private final Integer categoryId;
    private final Integer subCategoryId;

    public Question(Integer typeId, Integer categoryId, Integer subCategoryId) {
        this.typeId = ValidationUtils.validateId(typeId, MAX_TYPE_ID, "Question Type ID");
        this.categoryId = ValidationUtils.validateId(categoryId, MAX_CATEGORY_ID, "Category ID");
        this.subCategoryId = ValidationUtils.validateId(subCategoryId, MAX_SUBCATEGORY_ID, "Sub-Category ID");
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }
}

