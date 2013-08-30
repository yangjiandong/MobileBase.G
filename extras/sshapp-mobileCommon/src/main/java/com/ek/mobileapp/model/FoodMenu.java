package com.ek.mobileapp.model;

//菜谱
public class FoodMenu {

    private Long id;
    private Long foodId;
    private String foodName;
    private Long dinnerTimeId;
    private String dinnerTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getDinnerTimeId() {
        return dinnerTimeId;
    }

    public void setDinnerTimeId(Long dinnerTimeId) {
        this.dinnerTimeId = dinnerTimeId;
    }

    public String getDinnerTime() {
        return dinnerTime;
    }

    public void setDinnerTime(String dinnerTime) {
        this.dinnerTime = dinnerTime;
    }

}