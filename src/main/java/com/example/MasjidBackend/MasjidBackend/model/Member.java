package com.example.MasjidBackend.MasjidBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto increment primary key
    private Long id;
    private String memberName;
    private String member_address;
    private int mobile_no;
    private int adhar_card_no;
    private int fixed_monthly_donation;
    private int old_donation_balance;
    private int No_Of_Month_Donation_Paid;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RamdanExpense> ramdanExpenses;

    public Member() {
    }

    public Member(int no_Of_Month_Donation_Paid) {
        No_Of_Month_Donation_Paid = no_Of_Month_Donation_Paid;
    }


    public Member(String member_address, int mobile_no, int adhar_card_no, int fixed_monthly_donation, int old_donation_balance, List<RamdanExpense> ramdanExpense, List<Expense> expense, List<Payment> payments, List<RamdanExpense> ramdanExpenses, String memberName, int no_Of_Month_Donation_Paid) {
        this.id=id;
        this.member_address = member_address;
        this.mobile_no = mobile_no;
        this.adhar_card_no = adhar_card_no;
        this.fixed_monthly_donation = fixed_monthly_donation;
        this.old_donation_balance = old_donation_balance;
//        this.Expense= expense;
        this.payments = payments;
        this.ramdanExpenses=ramdanExpenses;
        this.memberName=memberName;
        this.No_Of_Month_Donation_Paid=no_Of_Month_Donation_Paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMember_address() {
        return member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public int getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(int mobile_no) {
        this.mobile_no = mobile_no;
    }

    public int getAdhar_card_no() {
        return adhar_card_no;
    }

    public void setAdhar_card_no(int adhar_card_no) {
        this.adhar_card_no = adhar_card_no;
    }

    public int getFixed_monthly_donation() {
        return fixed_monthly_donation;
    }

    public void setFixed_monthly_donation(int fixed_monthly_donation) {
        this.fixed_monthly_donation = fixed_monthly_donation;
    }

    public int getNo_Of_Month_Donation_Paid() {
        return No_Of_Month_Donation_Paid;
    }

    public void setNo_Of_Month_Donation_Paid(int no_Of_Month_Donation_Paid) {
        No_Of_Month_Donation_Paid = no_Of_Month_Donation_Paid;
    }

    public int getOld_donation_balance() {
        return old_donation_balance;
    }

    public void setOld_donation_balance(int old_donation_balance) {
        this.old_donation_balance = old_donation_balance;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<RamdanExpense> getRamdanExpenses() {
        return ramdanExpenses;
    }

    public void setRamdanExpenses(List<RamdanExpense> ramdanExpenses) {
        this.ramdanExpenses = ramdanExpenses;
    }
//    public List<Expense> getExpense() {
//        return Expense;
//    }
//
//    public void setExpense(List<Expense> expense) {
//        Expense = expense;
//    }

    @Override
    public String toString() {
        return "Member{" +
                ", member_address='" + member_address + '\'' +
                ", mobile_no=" + mobile_no +
                ", adhar_card_no=" + adhar_card_no +
                ", fixed_monthly_donation=" + fixed_monthly_donation +
                ", old_donation_balance=" + old_donation_balance +
                '}';
    }
}
