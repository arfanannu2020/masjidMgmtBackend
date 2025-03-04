package com.example.MasjidBackend.MasjidBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RamdanExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto increment primary key
    private Long id;
    private String bookNo;
    private String receiptNo;
    private Date receiptDate;
    private int amount;

    // @Column(unique = true)
//    private int memberId;
    private String memberName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId", referencedColumnName = "id")
    @JsonBackReference
    private Member member;

    public RamdanExpense() {
    }
    public RamdanExpense(Long id, String bookNo, String receiptNo, Date receiptDate, int amount, int memberId, String memberName,Member member) {
        this.id = id;
        this.bookNo = bookNo;
        this.receiptNo = receiptNo;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.memberName = memberName;
        this.member=member;
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
//
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
//    public int getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(int memberId) {
//        this.memberId = memberId;
//    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "RamdanExpense{" +
                "id=" + id +
                ", bookNo='" + bookNo + '\'' +
                ", receiptNo='" + receiptNo + '\'' +
                ", receiptDate=" + receiptDate +
                ", amount=" + amount +
                ", memberName='" + memberName + '\'' +
                ", member=" + member +
                '}';
    }
}
