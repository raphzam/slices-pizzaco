package com.example.demo.model;

import com.example.demo.authentication.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Pizza> pizzas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double total;

    private boolean complete;

    private LocalDateTime localTime;

    private String timeSubmitted;


    public Order() {
        this.complete=false;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime() {
        this.localTime = LocalDateTime.now();
    }

    public String getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        setLocalTime();
        String formattedTime = localTime.format(formatter);
        this.timeSubmitted = formattedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotal() {
        return total;
    }

    public void addPizza(Pizza pizza) {
        if (this.pizzas == null) {
            this.pizzas = new HashSet<Pizza>();
        }
        this.pizzas.add(pizza);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double calculateTotal() {
        double orderTotal = 0;
        for (Pizza pie : pizzas) {
            orderTotal += pie.getPrice();
        }
        return orderTotal;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
