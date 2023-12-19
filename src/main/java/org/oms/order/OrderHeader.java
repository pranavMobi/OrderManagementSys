package org.oms.order;

import java.util.Date;

public class OrderHeader {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private String orderStatus;
    private String paymentMode;
    private Date paymentDate;
    private Date orderShipmentDate;
    private int shipperId;

    public OrderHeader(int orderId, int customerId, Date orderDate, String orderStatus, String paymentMode, Date paymentDate, Date orderShipmentDate, int shipperId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.orderShipmentDate = orderShipmentDate;
        this.shipperId = shipperId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getOrderShipmentDate() {
        return orderShipmentDate;
    }

    public void setOrderShipmentDate(Date orderShipmentDate) {
        this.orderShipmentDate = orderShipmentDate;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

}
