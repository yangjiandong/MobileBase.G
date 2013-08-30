package com.ek.mobileapp.model;

import java.math.BigDecimal;

//营养套餐
public class FoodGroup {

    private Long id;
    private Long groupId;
    private String groupName;
    private Long foodId;
    private String foodName;
    private Long dinnerTimeId;
    private String dinnerTime;
    private int value;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}