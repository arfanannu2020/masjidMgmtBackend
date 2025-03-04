package com.example.MasjidBackend.MasjidBackend.repository;

import com.example.MasjidBackend.MasjidBackend.model.Expense;
import com.example.MasjidBackend.MasjidBackend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface masjidRepository extends JpaRepository<Expense,Long> {

    @Query(value="SELECT e.expenseDescription,e.date,e.expenseAmount,MONTH(e.date) AS month FROM Expense e WHERE MONTH(e.date)=:month AND YEAR(e.date)=:year",nativeQuery = true)
    List<Object[]> ExpenditureReport(@Param("month") Long month, @Param("year") Long year);

    @Query(value="SELECT e.expenseDescription,e.date,e.expenseAmount,YEAR(e.date) AS year FROM Expense e WHERE YEAR(e.date)=:year",nativeQuery = true)
    List<Object[]> ExpenditureReportPerYear(@Param("year") Long year);

    @Query(value="SELECT SUM(e.expenseAmount) AS Expense FROM Expense e WHERE e.ramdan=1",nativeQuery = true)
    int RamdanBalanceAmount();

    @Query(value="SELECT SUM(e.expenseAmount) AS Expense FROM Expense e WHERE MONTH(e.date)=:month AND YEAR(e.date)=:year GROUP BY MONTH(e.date)",nativeQuery = true)
    int GrandExpenditureReport(@Param("month") Long month, @Param("year") Long year);

    @Query(value="SELECT SUM(e.expenseAmount) AS Expense FROM Expense e WHERE YEAR(e.date)=:year GROUP BY YEAR(e.date)",nativeQuery = true)
    int GrandExpenditureReportPerYear(long year);

}
