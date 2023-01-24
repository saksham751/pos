package com.increff.invoice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForm {

    private Integer orderId;
    private String barcode;
    private Integer quantity;

}
