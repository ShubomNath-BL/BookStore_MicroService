package com.example.cartdata.controller;

import com.example.cartdata.dto.CartDTO;
import com.example.cartdata.dto.ResponseDTO;
import com.example.cartdata.entity.CartEntity;
import com.example.cartdata.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@RequestBody CartDTO cart){
        CartEntity response = cartService.saveData(cart);
        ResponseDTO responseCartDTO = new ResponseDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAllData(){
        List<CartEntity> response = cartService.recieveList();
        ResponseDTO responseCartDTO = new ResponseDTO("All data in the cart", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        Optional<CartEntity> response = cartService.getById(id);
        ResponseDTO responseCartDTO = new ResponseDTO("Books related to id are:- ", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id){
        cartService.deleteById(id);
        ResponseDTO responseCartDTO = new ResponseDTO("Data hsa been deleted:- ", "Deleted id: "+id);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.GONE);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> updateByID(@RequestBody CartDTO cartDTO, @PathVariable int id){
        CartEntity cartEntity = cartService.updateData(cartDTO, id);
        ResponseDTO responseCartDTO = new ResponseDTO("Data after updated are:- ", cartEntity);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @PutMapping("/editquantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestBody CartDTO cartDTO, @PathVariable int id){
        CartEntity cartEntity = cartService.updateQuantity(cartDTO, id);
        ResponseDTO responseCartDTO = new ResponseDTO("Data after updated are:- ", cartEntity);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
}
