package com.increff.invoice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemData{
    private Integer id;
    private Double sellingPrice;
    private Integer productId;
    private Integer quantity;
    private Integer orderId;

}
