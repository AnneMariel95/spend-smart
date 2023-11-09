package com.annemariel.backend.wallet;

import com.annemariel.backend.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "wallets")
public class Wallet {
    public Wallet() {
    }

    public Wallet(String name, double balance, User user) {
        this.name = name;
        this.balance = balance;
        this.user = user;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @UuidGenerator
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private double balance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
