package com.example.MasjidBackend.MasjidBackend.repository;

import com.example.MasjidBackend.MasjidBackend.model.RamdanExpense;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ramdanExpenseRepository extends JpaRepository<RamdanExpense,Long> {

//    @EntityGraph(attributePaths = {"members"})
    RamdanExpense save(RamdanExpense ramdanExpense);

    @Query(value="SELECT r.memberId,r.memberName,r.bookNo,r.receiptNo,r.receiptDate,r.amount AS RamdanExpense FROM RamdanExpense r WHERE r.memberId!=1",nativeQuery = true)
    List<Object[]> RamdanDonationReport();

    @Query(value="SELECT r.memberId,r.memberName,r.bookNo,r.receiptNo,r.receiptDate,r.amount AS RamdanExpense FROM RamdanExpense r WHERE r.memberId=1",nativeQuery = true)
    List<Object[]> NonMemberRamdanDonationReport();

    @Query(value="SELECT SUM(r.amount) FROM RamdanExpense r WHERE YEAR(receiptDate)=:year AND r.memberId!=1 GROUP BY YEAR(receiptDate)",nativeQuery = true)
    int SumOfRamdanDonationReport(int year);

    @Query(value="SELECT SUM(r.amount) FROM RamdanExpense r WHERE YEAR(receiptDate)=:year AND r.memberId=1 GROUP BY YEAR(receiptDate)",nativeQuery = true)
    int SumOfNonMemberRamdanDonationReport(int year);
}
