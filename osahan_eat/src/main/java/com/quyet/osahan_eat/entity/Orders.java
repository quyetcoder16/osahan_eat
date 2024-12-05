package com.quyet.osahan_eat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "Orders")
@Entity(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> listOrderItems;

    public List<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(List<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
