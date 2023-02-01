package com.increff.invoice.dto;

import com.increff.invoice.model.InvoiceForm;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.Base64;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

@Service
public class InvoiceMasterDto {
    public String get(InvoiceForm orderDetailsData) throws Exception{
        DtoHelper.createTemplate(orderDetailsData);
        DtoHelper.createPDF();
        byte[] byteData = readFileToByteArray(new File("invoice.pdf"));
        return(Base64.getEncoder().encodeToString(byteData));
    }
}
