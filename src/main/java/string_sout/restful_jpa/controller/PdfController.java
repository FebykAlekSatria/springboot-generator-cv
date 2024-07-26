package string_sout.restful_jpa.controller;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import string_sout.restful_jpa.entity.User;

import string_sout.restful_jpa.service.DownloadPdfService;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PdfController {

    private static final Logger log = LoggerFactory.getLogger(PdfController.class);
    @Autowired
    private DownloadPdfService downloadPdfService;

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HikariDataSource dataSource;


private Connection getConnection(){
    try {
        return dataSource.getConnection();
    }catch (SQLException e){
        e.printStackTrace();
        return null;
    }
}

//    @GetMapping("/download/pdf")
//    public ResponseEntity<byte[]> downloadInvoice(User user) throws JRException, IOException  {
//        log.info("start download");
//
//        String reportPath="src/main/resources/templates";
//
//        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(reportPath + "/gcv.jrxml"));
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("user", user.getId());
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,parameters, getConnection());
//        log.info("end create pdf");
//        log.info("create byte report");
//
//        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
//        log.info("data:{}",new String(data));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=invoice.pdf");
//
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
//
//
//    }
    @GetMapping("/download/pdf")
    public void  downloadEJournalFile(User user) throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Generator.pdf\"");
        log.info("Start download EJournal File");
        JasperPrint jasperPrint = downloadPdfService.generateJasperPrint(user);
        log.info("End download EJournal File");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

    }

}
