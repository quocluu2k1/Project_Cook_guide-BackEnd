package com.project.cookguide.Cook.guide.project.dto;

public class IngredientDto {
    private Long ingId;
    private String name;
    private String amount;

    public IngredientDto(){}

    public IngredientDto(Long ingId, String name, String amount) {
        this.ingId = ingId;
        this.name = name;
        this.amount = amount;
    }

    public Long getIngId() {
        return ingId;
    }

    public void setIngId(Long ingId) {
        this.ingId = ingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
