package com.example.bookdata.controller;

import com.example.bookdata.dto.BookDTO;
import com.example.bookdata.dto.ResponseDTO;
import com.example.bookdata.entity.BookEntity;
import com.example.bookdata.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService bookService;
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO books){
        BookEntity response = bookService.saveData(books);
        ResponseDTO responseBookDTO = new ResponseDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBook(){
        List<BookEntity> response = bookService.getAllData();
        ResponseDTO responseBookDTO = new ResponseDTO("List of all books: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable int id){
        Optional<BookEntity> response = bookService.getById(id);
        ResponseDTO responseBookDTO = new ResponseDTO("Books related to id are:- ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public BookEntity getDetailsById(@PathVariable int id){
        BookEntity book = bookService.getDetailsById(id);
        return book;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable int id){
        bookService.deleteData(id);
        ResponseDTO responseBookDTO = new ResponseDTO("Books details has been deleted: ", "Deleted id: "+id);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.GONE);
    }
    @GetMapping("/searchbookbyname/{bookName}")
    public ResponseEntity<ResponseDTO> searchBooksByName(@PathVariable String bookName){
        Optional<BookEntity> response = bookService.getBooksByName(bookName);
        ResponseDTO responseBookDTO = new ResponseDTO("Information related to required book is:- ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> updateBookbyID(@RequestBody BookDTO bookDTO, @PathVariable int id) {
        BookEntity bookEntity = bookService.editData(bookDTO, id);
        ResponseDTO responseBookDTO = new ResponseDTO("Edit book details by id: ", bookEntity);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingDesc/{field}")
    public ResponseEntity<ResponseDTO> sortingDesc(@PathVariable String field){
        List<BookEntity> response = bookService.getAllDataInDescendingOrder(field);
        ResponseDTO responseBookDTO = new ResponseDTO("List of all books as per higher to lower order: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingAsc/{field}")
    public ResponseEntity<ResponseDTO> sortingAsc(@PathVariable String field){
        List<BookEntity> response = bookService.getAllDataInAscendingOrder(field);
        ResponseDTO responseBookDTO = new ResponseDTO("List of all books as per lower to higher order: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestBody BookDTO bookDTO,
                                                          @RequestParam(value = "bookName", defaultValue = "") String bookName,
                                                          @RequestParam(value = "authorName", defaultValue = "") String authorName){
        BookEntity response = bookService.editQuantityOfBook(bookDTO,bookName,authorName);
        ResponseDTO responseBookDTO = new ResponseDTO("Book quantity updated: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
}
