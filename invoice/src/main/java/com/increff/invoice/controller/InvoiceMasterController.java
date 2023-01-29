package com.increff.invoice.controller;

import com.increff.invoice.dto.InvoiceMasterDto;
import com.increff.invoice.model.InvoiceForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class InvoiceMasterController {
    @Autowired
    private InvoiceMasterDto invoiceDto;
    @ApiOperation(value = "Get Invoice")
    @RequestMapping(path = "/api/get-invoice", method = RequestMethod.POST)
    public String getInvoice(@RequestBody InvoiceForm orderData) throws Exception {
        return invoiceDto.get(orderData);
    }
}
