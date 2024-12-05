package com.quyet.osahan_eat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table
@Entity(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_cate", length = 100)
    private String nameCate;
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private List<Food> listFood;

    @OneToMany(mappedBy = "category")
    private List<MenuRestaurant> listMenuRestaurant;

    public List<MenuRestaurant> getListMenuRestaurant() {
        return listMenuRestaurant;
    }

    public void setListMenuRestaurant(List<MenuRestaurant> listMenuRestaurant) {
        this.listMenuRestaurant = listMenuRestaurant;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
