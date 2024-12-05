package com.quyet.osahan_eat.entity;

import jakarta.persistence.*;

@Table(name = "menurestaurant")
@Entity(name = "MenuRestaurant")
public class MenuRestaurant {
    @Id
    @Column(name = "cate_id")
    private int cateId;

    @Id
    @Column(name = "res_id")
    private int resId;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
