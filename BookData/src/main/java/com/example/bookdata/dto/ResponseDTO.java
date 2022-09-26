package com.example.bookdata.dto;

import com.example.bookdata.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object obj;

    public ResponseDTO(String string, BookEntity response) {
        this.message = string;
        this.obj = response;
    }

    public ResponseDTO(String string1, List<BookEntity> response) {
        this.message = string1;
        this.obj = response;
    }

    public ResponseDTO(String string2, Optional<BookEntity> response) {
        this.message = string2;
        this.obj = response;
    }

    public ResponseDTO(String string3, String s1) {
        this.message = string3;
        this.obj = s1;
    }

}
