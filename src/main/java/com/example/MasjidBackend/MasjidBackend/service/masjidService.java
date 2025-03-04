package com.example.MasjidBackend.MasjidBackend.service;

import com.example.MasjidBackend.MasjidBackend.model.*;
import com.example.MasjidBackend.MasjidBackend.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class masjidService {

    private static final Logger logger = LoggerFactory.getLogger(masjidService.class);

    @Autowired
    private memberRepository memberrepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private masjidRepository masjidrepository;

    @Autowired
    private ramdanExpenseRepository ramdanexpenseRepository;

    @Autowired
    private IncomeToMasjidRepository incomeToMasjidRepository;

    public void saveMembers(Member member){
        member.setNo_Of_Month_Donation_Paid(0);
        memberrepository.save(member);
    }

    public List<Member> findMembersByName(int id) {
        return memberrepository.findById(id);
    }

    public Optional<Member> findMembersById(Long id){
        return memberrepository.findById(id);
    }

    public Payment savePayment(Payment payment){
//        if(paymentRepository.existsByMemberId(payment.getMemberId())){
//            return "MemberId is already present";
//        }

//        Payment savedPayment = paymentRepository.save(payment);
//        logger.debug(String.valueOf(savedPayment));
//        if (savedPayment.getId() == null) {
//            logger.info("payement saved");
//            return payment;
//        }
        Member Pmember = memberrepository.findById(payment.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + payment.getMember().getId()));

        logger.info("pmember" + Pmember);

        payment.setMember(Pmember);
        paymentRepository.save(payment);

        Long memId= payment.getMember().getId();

        Optional<Member> member= memberrepository.findAllById(payment.getMember().getId());
        if(Objects.isNull(member)) {
            System.out.println(member);
        }
        logger.debug(String.valueOf(member));

        Member member1=member.get();

        payment.setMember(member1);
        paymentRepository.save(payment);

        int UpdatedAmount=member1.getOld_donation_balance();
        UpdatedAmount+=payment.getAmount();
        logger.info("payement saved",UpdatedAmount);

        member1.setOld_donation_balance(UpdatedAmount);

        int updatedMonthDonationCount=member1.getNo_Of_Month_Donation_Paid();
        updatedMonthDonationCount+=payment.getAmount()/member1.getFixed_monthly_donation();
        member1.setNo_Of_Month_Donation_Paid(updatedMonthDonationCount);

        Member members = memberrepository.save(member1);
        if(Objects.isNull(member)){
            logger.debug(String.valueOf(member));
            System.out.println(member);
        }
        return payment;
    }

    public Expense saveExpense(Expense expense){
//        Optional<Member> eMember = memberrepository.findById(expense.getMember().getId());
//        expense.setMember(eMember.get());
        return masjidrepository.save(expense);
    }

    public RamdanExpense ramdanDonation(RamdanExpense ramdanExpense){
//        Member Rmember = memberrepository.findById(ramdanExpense.getMember().getId())
//                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + ramdanExpense.getMember().getId()));
//
        Member Pmember = memberrepository.findById(ramdanExpense.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + ramdanExpense.getMember().getId()));

        logger.info("pmember" + Pmember);

        ramdanExpense.setMember(Pmember);
        ramdanexpenseRepository.save(ramdanExpense);
        logger.info(ramdanExpense.getMember().toString());
        return ramdanExpense;

//       Optional<Member> Rmember = memberrepository.findById(ramdanExpense.getMember().getId());
//        ramdanExpense.setMember(Rmember.get());
//        return ramdanexpenseRepository.save(ramdanExpense);
    }

    @Transactional
    public IncomeToMasjid incomeToMasjidByMembers(IncomeToMasjid incomeToMasjid){
        try{
            IncomeToMasjid savedEntity = incomeToMasjidRepository.save(incomeToMasjid);
            logger.info("saved data"+ savedEntity.getMemberName());
            return savedEntity;
        }
        catch (Exception e){
            logger.error("error saving data",e);
            throw e;
        }
    }

    public void deleteMember(Long id){
        try{
            memberrepository.deleteById(id);
            logger.info("success");
        }
        catch (Exception e){
            logger.error("error in deleting");
        }
    }

    public List<Object[]> memberDeatils(){
        return memberrepository.memberDeatils();
    }

    public List<Object[]> TotalDonationPerMonth(int month,int year){
        return memberrepository.TotalDonationPerMonth((long) month,(long) year);
    }

    public List<Object[]> TotalDonationPerYear(int year){
        return memberrepository.TotalDonationPerYear((long) year);
    }

    public List<Object[]> ExpenditureReport(int month,int year){
        return masjidrepository.ExpenditureReport((long) month,(long) year);
    }

    public List<Object[]> ExpenditureReportPerYear(int year){
        return masjidrepository.ExpenditureReportPerYear((long) year);
    }

    public List<Object[]> RamdanDonationReport(){
        return ramdanexpenseRepository.RamdanDonationReport();
    }

    public List<Object[]> NonMemberRamdanDonationReport(){
        return ramdanexpenseRepository.NonMemberRamdanDonationReport();
    }

//    public List<Object[]> RamdanExpenseReport(){
//        return masjidrepository.RamdanExpenseReport();
//    }

    public int SumOfRamdanDonationReport(int year){
        return ramdanexpenseRepository.SumOfRamdanDonationReport(year);
    }

    public int SumOfNonMemberRamdanDonationReport(int year){
        return ramdanexpenseRepository.SumOfNonMemberRamdanDonationReport(year);
    }

    public int RamdanBalanceAmount(int year){
        int list1=SumOfRamdanDonationReport(year);
        int list2=SumOfNonMemberRamdanDonationReport(year);
        int balance= masjidrepository.RamdanBalanceAmount();
//        logger.info("success"+Arrays.toString(list1.get(0))+","+list2.get(0)+","+balance.get(0));
//        Double sumValue=List.of(list1.get(0));
//        Object[] result = new Object[list1.get(0) + list2.get(0)];
//        List<String> result = new ArrayList<>();
//        result.add(String.valueOf(List.of(list1.get(0)))); // Add the first element of list1
//        result.add(String.valueOf(List.of(list2.get(0))));
//        result.add(String.valueOf(List.of(balance.get(0))));
//        String s = result.get(0) + result.get(1);
//        s=s-String.valueOf(balance.get(0));
        logger.info(list1+","+list2+","+balance);
            int l=list1+list2;
            l=l-balance;
        return l;
    }

    public int GrandTotalDonationPerMonth(int month, int year) {
        return paymentRepository.GrandTotalDonationPerMonth(month,year);
    }

    public int GrandTotalDonationPerYear(int year) {
        return paymentRepository.GrandTotalDonationPerYear(year);
    }

    public int GrandExpenditureReport(int month, int year) {
        return masjidrepository.GrandExpenditureReport((long)month,(long)year);
    }

    public int GrandExpenditureReportPerYear(int year) {
        return masjidrepository.GrandExpenditureReportPerYear((long)year);
    }

    public List<Object[]> MemberOutstanding(int month) {
        List<Object[]> ans=new ArrayList<>();
        List<Object[]> list= memberrepository.MemberOutstanding((long) month);
        for (Object[] objectArray : list) {
            int num = Integer.parseInt(objectArray[3].toString());
            if(num!=month){
                    ans.add(objectArray);
            }
        }
        return list;
    }
}
