package com.example.cartdata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {
    private int userId;
    private int bookId;
    private int quantity;
}
