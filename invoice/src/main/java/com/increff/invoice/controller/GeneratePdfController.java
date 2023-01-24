package com.increff.invoice.controller;

import com.increff.invoice.dto.GeneratePdfDto;
import com.increff.invoice.model.InvoiceForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Api
@RestController
public class GeneratePdfController {
    @Autowired
    private GeneratePdfDto generatePdfDto;
    @ApiOperation(value = "Returns base64Encoded string")
    @RequestMapping(path = "/api/get-invoice", method = RequestMethod.POST)
    public String getEncodedPdf(@RequestBody InvoiceForm orderData) throws IOException, FOPException, TransformerException, ParserConfigurationException {
        return generatePdfDto.getEncodedPdf(orderData);
    }



}
