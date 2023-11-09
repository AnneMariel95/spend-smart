package com.annemariel.backend.expense;

import com.annemariel.backend.user.User;
import com.annemariel.backend.wallet.Wallet;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "expenses")
public class Expense {
    public Expense() {
    }

    public Expense(String category, double amount, String date, Wallet wallet, User user) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.wallet = wallet;
        this.user = user;
    }

    @Id
    @UuidGenerator
    @Column
    private String id;

    @Column
    private String category;

    @Column
    private double amount;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "amount: " + amount + "; category: " + category + "; date: " + date;
    }

}
