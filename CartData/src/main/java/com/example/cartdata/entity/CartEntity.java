package com.example.cartdata.entity;

import com.example.cartdata.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    private int userId;
    private int bookId;
    private int quantity;

    public CartEntity(CartDTO cartDTO) {
        this.userId=cartDTO.getUserId();
        this.bookId=cartDTO.getBookId();
        this.quantity=cartDTO.getQuantity();
    }
}
