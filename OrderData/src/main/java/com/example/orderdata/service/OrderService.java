package com.example.orderdata.service;

import com.example.orderdata.dto.OrderDTO;
import com.example.orderdata.dto.User;
import com.example.orderdata.entity.OrderEntity;
import com.example.orderdata.exception.OrderException;
import com.example.orderdata.repo.OrderRepo;
import com.example.orderdata.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EmailSenderService senderService;

    @Override
    public OrderEntity save(OrderDTO orderDTO) {
        User userData = restTemplate.getForObject("http://localhost:8083/user/id/"+orderDTO.getUserId(),User.class);
        Object bookData = restTemplate.getForObject("http://localhost:8081/books/id/"+orderDTO.getBookId(),Object.class);
        System.out.println(userData.getEmail());
        if(userData.equals(null) || bookData.equals(null)){
            throw new OrderException("User not found.....!");
        }else {
            OrderEntity order = new OrderEntity(orderDTO);
            orderRepo.save(order);
            senderService.sendEmail(userData.getEmail(), "Order placed", "Order details link....");
            return order;
        }
    }

    @Override
    public List<OrderEntity> getAllData() {
        List<OrderEntity> orderEntities = orderRepo.findAll();
        return orderEntities;
    }

    @Override
    public Optional<OrderEntity> getById(int id) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(id);
        if(orderEntity.isPresent()){
            return orderEntity;
        }
        else {
            throw new OrderException("Order id not found");
        }
    }

    @Override
    public void deleteData(int id) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(id);
        if(orderEntity.isPresent()){
            orderRepo.deleteById(id);
        }else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public OrderEntity updateOrder(OrderDTO orderDTO, int id) {
        OrderEntity orderEntity = orderRepo.findById(id).orElse(null);
        if (orderEntity!=null){
            orderEntity.setUserId(orderDTO.getUserId());
            orderEntity.setBookId(orderDTO.getBookId());
            orderEntity.setOrderDate(orderDTO.getOrderDate());
            orderEntity.setQuantity(orderDTO.getQuantity());
            orderEntity.setPrice(orderDTO.getPrice());
            orderEntity.setCancel(orderDTO.isCancel());
            orderRepo.save(orderEntity);
            return orderEntity;
        }else {
            throw new OrderException("Order not found......!");
        }
    }
}
