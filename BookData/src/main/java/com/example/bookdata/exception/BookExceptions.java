package com.example.bookdata.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookExceptions extends RuntimeException{
    private String message;

    public BookExceptions(String message){
        this.message = message;
    }
}
