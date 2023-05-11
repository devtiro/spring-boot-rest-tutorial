package com.devtiro.books.services;

import com.devtiro.books.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

  boolean isBookExists(Book book);

  Book save(Book book);

  Optional<Book> findById(String isbn);

  List<Book> listBooks();

  void deleteBookById(String isbn);
}
