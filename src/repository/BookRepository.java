package repository;

import model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    Book findById(int id);

    List<Book> findAll();

    Book update(Book book);

    void delete(int id);
}
