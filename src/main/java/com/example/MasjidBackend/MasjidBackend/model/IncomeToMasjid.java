package com.example.MasjidBackend.MasjidBackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class IncomeToMasjid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String memberName;
   private String bookNo;
   private int receiptNo;
   private Date receiptDate;
   private int amount;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "memberId")
   private Member member;

    public IncomeToMasjid() {
    }

    public IncomeToMasjid(Long id, String memberName, String bookNo, int receiptNo, Date receiptDate, int amount, Member member) {
        this.id = id;
        this.memberName = memberName;
        this.bookNo = bookNo;
        this.receiptNo = receiptNo;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.member = member;
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

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public int getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(int receiptNo) {
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
