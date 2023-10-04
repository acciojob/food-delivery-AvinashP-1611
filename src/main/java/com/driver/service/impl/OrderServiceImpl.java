package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setCost(orderDto.getCost());
        orderEntity.setItems(orderDto.getItems());
        orderEntity.setStatus(true);
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setId(orderDto.getId());

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        OrderDto response = new OrderDto();

        response.setCost(savedOrder.getCost());
        response.setItems(savedOrder.getItems());
        response.setUserId(savedOrder.getUserId());
        response.setOrderId(savedOrder.getOrderId());
        response.setStatus(savedOrder.isStatus());
        response.setId(savedOrder.getId());

        return response;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if(order == null){
            return new OrderDto();
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setStatus(order.isStatus());
        orderDto.setUserId(order.getUserId());
        orderDto.setCost(order.getCost());
        orderDto.setItems(order.getItems());

        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {

        OrderEntity oldOrder = orderRepository.findByOrderId(orderId);
        if(oldOrder == null){
            return new OrderDto();
        }

        oldOrder.setStatus(order.isStatus());
        oldOrder.setItems(order.getItems());
        oldOrder.setCost(order.getCost());
        oldOrder.setUserId(order.getUserId());

        OrderEntity updatedOrder = orderRepository.save(oldOrder);

        OrderDto response = new OrderDto();

        response.setOrderId(updatedOrder.getOrderId());
        response.setStatus(updatedOrder.isStatus());
        response.setId(updatedOrder.getId());
        response.setItems(updatedOrder.getItems());
        response.setCost(updatedOrder.getCost());
        response.setUserId(updatedOrder.getUserId());

        return response;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {

        OrderEntity order = orderRepository.findByOrderId(orderId);
        orderRepository.delete(order);

    }

    @Override
    public List<OrderDto> getOrders() {

        Iterable<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderDto> ans = new ArrayList<>();

        for (OrderEntity orderEntity: orderEntities) {

            OrderDto orderDto = new OrderDto();

            orderDto.setUserId(orderEntity.getUserId());
            orderDto.setStatus(orderEntity.isStatus());
            orderDto.setOrderId(orderEntity.getOrderId());
            orderDto.setItems(orderEntity.getItems());
            orderDto.setId(orderEntity.getId());
            orderDto.setCost(orderEntity.getCost());

            ans.add(orderDto);

        }

        return ans;
    }
}