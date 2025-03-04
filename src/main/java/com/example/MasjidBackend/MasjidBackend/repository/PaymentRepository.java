package com.example.MasjidBackend.MasjidBackend.repository;

import com.example.MasjidBackend.MasjidBackend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    boolean existsByMemberId(int MemberId);

    @Query(value = "SELECT SUM(p.amount) FROM Payment p WHERE MONTH(p.receiptDate)=:month AND YEAR(receiptDate)=:year GROUP BY MONTH(p.receiptDate)", nativeQuery = true)
    int GrandTotalDonationPerMonth(int month, int year);

    @Query(value = "SELECT SUM(p.amount) FROM Payment p WHERE YEAR(receiptDate)=:year GROUP BY YEAR(p.receiptDate)", nativeQuery = true)
    int GrandTotalDonationPerYear(int year);
}
