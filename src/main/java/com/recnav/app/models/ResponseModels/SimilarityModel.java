package com.recnav.app.models.ResponseModels;

public class SimilarityModel {

    private int categoryId;
    private double coefficent;
    private int userId;
    private String type;

    public SimilarityModel(){

    }
    public SimilarityModel(int categoryId, double coefficent, int userId, String  type) {
        this.categoryId = categoryId;
        this.coefficent = coefficent;
        this.userId = userId;
        this.type = type;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getCoefficent() {
        return coefficent;
    }

    public void setCoefficent(double coefficent) {
        this.coefficent = coefficent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
