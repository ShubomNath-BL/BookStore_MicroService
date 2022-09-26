package com.example.cartdata.service;

import com.example.cartdata.dto.CartDTO;
import com.example.cartdata.entity.CartEntity;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    CartEntity saveData(CartDTO cart);

    List<CartEntity> recieveList();

    Optional<CartEntity> getById(int id);

    void deleteById(int id);

    CartEntity updateData(CartDTO cartDTO, int id);

    CartEntity updateQuantity(CartDTO cartDTO, int id);


}
