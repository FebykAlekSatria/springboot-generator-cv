package string_sout.restful_jpa.controller;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import string_sout.restful_jpa.entity.User;

import string_sout.restful_jpa.service.DownloadPdfService;

import java.io.IOException;

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
