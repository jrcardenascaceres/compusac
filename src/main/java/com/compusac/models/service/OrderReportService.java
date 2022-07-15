package com.compusac.models.service;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderReportService {
    public String generateOrderReport(List<Order> orders, String reportFormat) {
        try {
            String reportPath = "./";
            // Load file and Compile the Jasper report from .jrxml to .japser
            File file = ResourceUtils.getFile("classpath:ReporteDeOrdenes.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Get your data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "JRCC");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    dataSource);

            // Export the report to
            if (reportFormat.equalsIgnoreCase("xls")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "ReporteDeOrdenes.html");
            }
            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "ReporteDeOrdenes.pdf");
            }

            System.out.println("Done");

            return reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String generateOrderDetailReport(List<OrderDetail> orderDetails, String reportFormat) {
        try {
            String reportPath = "./";

            // Load file and Compile the Jasper report from .jrxml to .japser
            File file = ResourceUtils.getFile("classpath:ReporteDetallesDeOrden.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orderDetails);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Compusac");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to
            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "ReporteDetalleOrden.html");
            }
            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "ReporteDetalleOrden.pdf");
            }
            System.out.println("Done");
            return reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
