package com.example.MasjidBackend.MasjidBackend.controller;

import org.springframework.http.HttpHeaders;
import com.example.MasjidBackend.MasjidBackend.model.*;
import com.example.MasjidBackend.MasjidBackend.service.masjidService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class masjidController {

    @Autowired
    private masjidService masjidService;

    private static final Logger logger = LoggerFactory.getLogger(masjidController.class);

    @PostMapping("/api/register")
    public Member registration(@RequestBody Member member){
        System.out.print(member.getMemberName()+"welcome");
//        member = new Member("sush", "John Doe", 33,32,34,3);
        masjidService.saveMembers(member);
        return member;
    }

    @GetMapping("/home")
    public List<Member> searchMembers(@RequestParam int id) {
        return masjidService.findMembersByName(id);
    }

    @PostMapping("/api/payment")
    public Payment payment(@RequestBody Payment payment) {
        Payment pay=masjidService.savePayment(payment);
         return pay;
    }

    @GetMapping("/api")
    public Optional<Member> findId(@RequestParam Long id){
        return masjidService.findMembersById(id);
    }

    @Transactional
    @PostMapping("api/addExpense")
    public Expense addExpense(@RequestBody Expense expense){
        logger.info("expense value"+String.valueOf(expense));
        logger.info("expense value"+String.valueOf(expense.isRamdan()));
        return masjidService.saveExpense(expense);
    }

    @PostMapping("/donation")
    public RamdanExpense addRamdanExpense(@RequestBody RamdanExpense ramdanExpense){

         RamdanExpense ramdan=masjidService.ramdanDonation(ramdanExpense);
        logger.info(ramdan.toString());
        return ramdan;
//        return "success";
    }

    @PostMapping("/incomeToMasjid")
    public ResponseEntity<String> incomeToMasjidByMembers(@RequestBody IncomeToMasjid incomeToMasjid){
        try{
            masjidService.incomeToMasjidByMembers(incomeToMasjid);
            return ResponseEntity.ok("Data inserted successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error inserting data");
        }
    }

    @DeleteMapping("/api/items/{id}")
    public ResponseEntity<String> deleteRegisteredMembers(@PathVariable Long id){
        try {
            masjidService.deleteMember(id);
            return ResponseEntity.ok("Deleted Successfully"+id);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error deleting data");
        }
    }

    @GetMapping("/api/memberDeatils")
    public List<Object[]> memberDeatils(){
        return masjidService.memberDeatils();
    }

    @GetMapping("/api/TotalDonationPerMonth/{month}&{year}")
    public List<Object[]> TotalDonationPerMonth(@PathVariable int month,@PathVariable int year){
        List<Object[]> masjid=masjidService.TotalDonationPerMonth(month,year);
        return masjid;
    }

    @GetMapping("api/ExpenditureReport/{month}&{year}")
    public List<Object[]> ExpenditureReport(@PathVariable int month,@PathVariable int year){
        return masjidService.ExpenditureReport(month,year);
    }

    @GetMapping("api/TotalDonationPerYear/{year}")
    public List<Object[]> TotalDonationPerYear(@PathVariable int year){
        return masjidService.TotalDonationPerYear(year);
    }

    @GetMapping("api/ExpenditureReportPerYear/{year}")
    public List<Object[]> ExpenditureReportPerYear(@PathVariable int year){
        return masjidService.ExpenditureReportPerYear(year);
    }

    @GetMapping("api/RamdanDonationReport")
    public List<Object[]> RamdanDonationReport(){
        return masjidService.RamdanDonationReport();
    }

    @GetMapping("api/NonMemberRamdanDonationReport")
    public List<Object[]> NonMemberRamdanDonationReport(){
        return masjidService.NonMemberRamdanDonationReport();
    }

//    @GetMapping("api/RamdanExpenseReport")
//    public List<Object[]> RamdanExpenseReport(){
//        return masjidService.RamdanExpenseReport();
//    }

    @GetMapping("api/RamdanBalanceAmount/{year}")
    public int RamdanBalanceAmount(@PathVariable int year){
        return masjidService.RamdanBalanceAmount(year);
    }

    @GetMapping("api/SumOfRamdanDonationReport/{year}")
    public int SumOfRamdanDonationReport(@PathVariable int year){
        return masjidService.SumOfRamdanDonationReport(year);
    }

    @GetMapping("api/SumOfNonMemberRamdanDonationReport/{year}")
    public int SumOfNonMemberRamdanDonationReport(@PathVariable int year){
        return masjidService.SumOfNonMemberRamdanDonationReport(year);
    }

    //GRand total of donation per month
    @GetMapping("api/GrandTotalDonationPerMonth/{month}&{year}")
    public int GrandTotalDonationPerMonth(@PathVariable int month,@PathVariable int year){
        return masjidService.GrandTotalDonationPerMonth(month,year);
    }

    //grand total of donation per year
    @GetMapping("api/GrandTotalDonationPerYear/{year}")
    public int GrandTotalDonationPerYear(@PathVariable int year){
        return masjidService.GrandTotalDonationPerYear(year);
    }

    //grand total of expenditure per month
    @GetMapping("api/GrandExpenditureReport/{month}&{year}")
    public int GrandExpenditureReport(@PathVariable int month,@PathVariable int year){
        return masjidService.GrandExpenditureReport(month,year);
    }

    //grand total of expenditure per year
    @GetMapping("api/GrandExpenditureReportPerYear/{year}")
    public int GrandExpenditureReportPerYear(@PathVariable int year){
        return masjidService.GrandExpenditureReportPerYear(year);
    }

    //member outstanding
    @GetMapping("api/MemberOutstanding/{month}")
    public List<Object[]> MemberOutstanding(@PathVariable int month){
        return masjidService.MemberOutstanding(month);
    }


//    @GetMapping("/download-pdf")
//    public ResponseEntity<byte[]> downloadPdf() throws IOException {
//        // Generate a sample PDF
//        Document document = new Document();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, baos);
//
//        document.open();
//        document.add(new com.itextpdf.text.Paragraph("Hello, this is a PDF file generated from Spring Boot!"));
//        document.close();
//
//        // Convert PDF to byte array
//        byte[] pdfBytes = baos.toByteArray();
//
//        // Set response headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=sample.pdf");
//        headers.add("Content-Type", "application/pdf");
//
//        // Return the PDF as a response entity
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(pdfBytes);
//    }
}
