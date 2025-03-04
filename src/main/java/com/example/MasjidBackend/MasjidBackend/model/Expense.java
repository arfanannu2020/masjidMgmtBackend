package com.example.MasjidBackend.MasjidBackend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private boolean Ramdan;

    @Column(length = 700)
    private String expenseDescription;
    private int expenseAmount;

//    @ManyToOne
//    @JoinColumn(name="memberId")
//    private Member member;

    public Expense() {
    }

    public Expense(boolean ramdan) {
        this.Ramdan = ramdan;
    }

    public Expense(Long id, Date date, String expenseDescription, int expenseAmount, Member member) {
        this.id = id;
        this.date = date;
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
//        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }

    public boolean isRamdan() {
        return Ramdan;
    }


    public void setRamdan(boolean ramdan) {
        this.Ramdan = ramdan;
    }
}
