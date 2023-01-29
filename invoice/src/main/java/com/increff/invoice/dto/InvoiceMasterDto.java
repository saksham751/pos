package com.increff.invoice.dto;

import com.increff.invoice.model.InvoiceForm;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Base64;

@Component
public class InvoiceMasterDto {
    public String get(InvoiceForm orderDetailsData) throws Exception{
        DtoHelper.createXml(orderDetailsData);
        DtoHelper.createPDF();
        byte[] encodedBytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File("invoice.pdf"));
        String b64PDF = Base64.getEncoder().encodeToString(encodedBytes);
        return b64PDF;
    }
}
