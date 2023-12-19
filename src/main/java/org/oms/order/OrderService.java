package org.oms.order;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService() {
        this.orderRepo = OrderRepository.getInstance();
    }

    public OrderHeader getOrderById(int orderId) {
        return orderRepo.getOrderById(orderId);
    }

    public List<OrderHeader> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    public void addOrder(OrderHeader order) {
        orderRepo.addOrder(order);
    }

    public void updateOrder(int orderId, OrderHeader order) {
        orderRepo.updateOrder(order);
    }

    public void deleteOrder(int orderId) {
        orderRepo.deleteOrder(orderId);
    }
}
