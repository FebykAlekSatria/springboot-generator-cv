package string_sout.restful_jpa.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import string_sout.restful_jpa.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class DownloadPdfService {

    private static final Logger log = LoggerFactory.getLogger(DownloadPdfService.class);
    @Autowired
    private DataSource dataSource;

    private Connection getConection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public JasperPrint generateJasperPrint(User user) throws IOException, JRException {
        InputStream fileTemplate = new ClassPathResource("templates/gcv.jasper" ).getInputStream();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("user_id", user.getId());
        log.info("user_id: {}", user.getId());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileTemplate);
        return JasperFillManager.fillReport(jasperReport, parameters, getConection());

    }




}
