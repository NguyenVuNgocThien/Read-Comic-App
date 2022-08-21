package com.example.greedapp.Model;

public class Category {
    String CategoryName;
    int CategoryId;

    public Category(int categoryId, String categoryName) {
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    public Category(String categoryName) {
        CategoryName = categoryName;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
