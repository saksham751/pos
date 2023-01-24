package com.increff.invoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InvoiceForm {
    private Integer orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY hh:mm:ss", timezone = "UTC")
    private Date time;
    private List<OrderItemData> orderItemList;
}
