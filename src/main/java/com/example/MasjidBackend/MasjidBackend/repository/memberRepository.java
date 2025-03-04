package com.example.MasjidBackend.MasjidBackend.repository;

import com.example.MasjidBackend.MasjidBackend.model.Member;
import com.example.MasjidBackend.MasjidBackend.model.Payment;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface memberRepository extends JpaRepository<Member,Long> {
//     int findById(int id);

     @Query(value = "SELECT * FROM Member WHERE id=:id", nativeQuery = true)
     List<Member> findById(int id);

     Optional<Member> findAllById(long memberId);

     void deleteAllById(Long id);

//     List<Member> findByMemberNameContainingIgnoreCase(String memberName);

//     @Query("SELECT MONTH(p.date) as month, SUM(p.amount) FROM Payment p WHERE p.member.id = :memberId GROUP BY MONTH(p.date)")
//     List<Object[]> TotalDonationPerMonth(@Param("memberId") Long memberId);
     @Query(value = "SELECT id,memberName,member_address FROM Member", nativeQuery = true)
     List<Object[]> memberDeatils();

     @Query(value = "SELECT m.id,m.memberName,p.bookNo,p.receiptNo,p.receiptDate,p.amount,MONTH(p.receiptDate) FROM Member m JOIN Payment p ON m.id=p.memberId WHERE MONTH(p.receiptDate)=:month AND YEAR(p.receiptDate)=:year", nativeQuery = true)
     List<Object[]> TotalDonationPerMonth(@Param("month") Long month,@Param("year") Long year);

     @Query(value = "SELECT m.id,m.memberName,p.bookNo,p.receiptNo,p.receiptDate,p.amount,YEAR(p.receiptDate) FROM Member m JOIN Payment p ON m.id=p.memberId WHERE YEAR(p.receiptDate)=:year",nativeQuery = true)
     List<Object[]> TotalDonationPerYear(@Param("year") Long year);

     @Query(value="SELECT m.id,m.memberName,m.member_address,m.No_Of_Month_Donation_Paid FROM Member m JOIN Payment p ON m.id=p.memberId WHERE MONTH(p.receiptDate)=:month",nativeQuery = true)
     List<Object[]> MemberOutstanding(long month);

}
