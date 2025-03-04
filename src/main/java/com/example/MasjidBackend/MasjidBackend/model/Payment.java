package com.example.MasjidBackend.MasjidBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;



@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto increment primary key
    private Long id;
    private String bookNo;
    private String receiptNo;
    private Date receiptDate;
    private int amount;

   // @Column(unique = true)
  //  private int memberId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId", referencedColumnName="id",nullable = false)  // Foreign key column (member_id)
    @JoinColumn(name = "memberId")
    @JsonBackReference
    private Member member;

    public Payment(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Payment() {
    }

    public Payment(Long id, String bookNo, String receiptNo, Date receiptDate, int amount, int memberId) {
        this.id = id;
        this.bookNo = bookNo;
        this.receiptNo = receiptNo;
        this.receiptDate = receiptDate;
        this.amount = amount;
//        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    public int getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(int memberId) {
//      this.memberId = memberId;
//   }
}
