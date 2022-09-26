package com.example.cartdata.service;

import com.example.cartdata.dto.CartDTO;
import com.example.cartdata.entity.CartEntity;
import com.example.cartdata.exception.CartException;
import com.example.cartdata.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    CartRepo repository;

    @Override
    public CartEntity saveData(CartDTO cartDTO) {
        CartEntity cart = new CartEntity(cartDTO);
        repository.save(cart);
        return cart;
    }

    @Override
    public List<CartEntity> recieveList() {
        List<CartEntity> cartEntities = repository.findAll();
        return cartEntities;
    }

    @Override
    public Optional<CartEntity> getById(int id) {
        Optional<CartEntity> cart = repository.findById(id);
        if(cart.isPresent()){
            return cart;
        }
        else {
            throw new CartException("Order id not found");
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<CartEntity> cart = repository.findById(id);
        if(cart.isPresent()){
            repository.deleteById(id);
        }else {
            throw new CartException("Order not found");
        }
    }

    @Override
    public CartEntity updateData(CartDTO cartDTO, int id) {
        CartEntity cart = repository.findById(id).orElse(null);
        if (cart!=null){
            cart.setUserId(cartDTO.getUserId());
            cart.setBookId(cartDTO.getBookId());
            cart.setQuantity(cartDTO.getQuantity());
            return cart;
        }else {
            throw new CartException("Order not found......!");
        }
    }

    @Override
    public CartEntity updateQuantity(CartDTO cartDTO, int id) {
        CartEntity cart = repository.findById(id).orElse(null);
        if(cart!=null){
            cart.setQuantity(cartDTO.getQuantity());
            return cart;
        }else {
            throw new CartException("Order not found......!");
        }
    }
}
