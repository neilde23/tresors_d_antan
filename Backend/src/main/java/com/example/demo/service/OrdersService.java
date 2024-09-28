package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public OrdersService() {
    }

    public List<Orders> getAllOrders() {
        return this.ordersRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return (Orders)this.ordersRepository.findById(id).orElse((Orders) null);
    }

    public Orders createOrder(Orders orders) {
        return (Orders)this.ordersRepository.save(orders);
    }

    public void deleteOrder(Long id) {
        this.ordersRepository.deleteById(id);
    }

    public Orders updateOrder(Long id, Orders orders) {
        Orders order = (Orders)this.ordersRepository.findById(id).orElse((Orders) null);
        if (order != null) {
            order.setStatus(orders.getStatus());
            order.setTotalPrice(orders.getTotalPrice());
            order.setDeliveryAddress(orders.getDeliveryAddress());
            return (Orders)this.ordersRepository.save(order);
        } else {
            return (Orders) null;
        }
    }

    public List<Orders> getOrdersByUserId(Long userId) {
        return this.ordersRepository.findByUserId(userId);
    }
}