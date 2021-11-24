package com.example.Eindproject.controller;

import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.dto.RepairOperationDto;
import com.example.Eindproject.pdf.ReceiptPdfGenerator;
import com.example.Eindproject.service.RepairOperationService;
import com.example.Eindproject.service.RepairService;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReceiptController {

    private final RepairOperationService service;
    private final RepairService repairService;

    public  ReceiptController(RepairOperationService service, RepairService repairService){
        this.service = service;
        this.repairService = repairService;
    }

//    Deze functie roept de receiptPdfGenerator aan die met de reparatie data een bon genereerd en deze terug geeft in pdf formaat
    @GetMapping("/repair/generateReceipt/{id}")
    public void generateRepairReceipt(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException, ParseException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        RepairDto repairDto = repairService.getRepair(id);
        List<RepairOperationDto> repairOperations = service.getAllRepairOperationsById(id);

        ReceiptPdfGenerator pdf = new ReceiptPdfGenerator();
        pdf.setRepairData(repairOperations, repairDto);
        pdf.export(response);

    }

//    Deze functie roept de recieptPdfGenerator aan die met de inspectie gegevens een bon genereerd en deze teruggeeft als pdf
    @GetMapping("/inspection/generateReceipt/{id}")
    public void generateInspectionReceipt(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException, ParseException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        ReceiptPdfGenerator pdf = new ReceiptPdfGenerator();
        pdf.export(response);

    }
}
