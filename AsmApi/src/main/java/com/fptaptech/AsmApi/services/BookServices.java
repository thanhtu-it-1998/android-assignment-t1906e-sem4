package com.fptaptech.AsmApi.services;

import java.util.List;

import com.fptaptech.AsmApi.models.Book;

public interface BookServices {

	public List<Book> getBooks();

	public Book getBookById(Long id);

	public Book insert(Book Book);

	public void updateBook(Long id, Book Book);

	public void deleteBook(Long BookId);
}
