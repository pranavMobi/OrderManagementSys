package org.oms.order;

import java.util.Date;

public class OrderHeader {
        private static OrderHeader instance;

        private int orderId;
        private int customerId;
        private Date orderDate;
        private String orderStatus;
        private String paymentMode;
        private Date paymentDate;
        private Date orderShipmentDate;
        private int shipperId;

        private OrderHeader() {
            // private constructor to prevent instantiation
        }

        public static OrderHeader getInstance() {
            if (instance == null) {
                instance = new OrderHeader();
            }
            return instance;
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
