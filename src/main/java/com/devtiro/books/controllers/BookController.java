package com.devtiro.books.controllers;

import com.devtiro.books.domain.Book;
import com.devtiro.books.services.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(final BookService bookService) {
    this.bookService = bookService;
  }

  @PutMapping(path = "/books/{isbn}")
  public ResponseEntity<Book> createUpdateBook(
      @PathVariable final String isbn, @RequestBody final Book book) {
    book.setIsbn(isbn);
    final boolean isBookExists = bookService.isBookExists(book);
    final Book savedBook = bookService.save(book);

    return ResponseEntity.status(isBookExists ? HttpStatus.OK : HttpStatus.CREATED).body(savedBook);
  }

  @GetMapping(path = "/books/{isbn}")
  public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn) {
    final Optional<Book> foundBook = bookService.findById(isbn);
    return foundBook.map(book -> ResponseEntity.ok(book)).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(path = "/books")
  public ResponseEntity<List<Book>> listBooks() {
    return ResponseEntity.ok(bookService.listBooks());
  }

  @DeleteMapping(path = "/books/{isbn}")
  public ResponseEntity<Void> deleteBook(@PathVariable final String isbn) {
    bookService.deleteBookById(isbn);
    return ResponseEntity.noContent().build();
  }
}
