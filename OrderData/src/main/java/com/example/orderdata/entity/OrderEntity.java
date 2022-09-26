package com.example.orderdata.entity;

import com.example.orderdata.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    private LocalDate orderDate;
    private int price;
    private int quantity;
    private int userId;
    private int bookId;
    private boolean cancel;

    public OrderEntity(OrderDTO orderDTO) {
        this.orderDate = orderDTO.getOrderDate();
        this.price = orderDTO.getPrice();
        this.quantity = orderDTO.getQuantity();
        this.userId = orderDTO.getUserId();
        this.bookId = orderDTO.getBookId();
        this.cancel = orderDTO.isCancel();
    }
}
