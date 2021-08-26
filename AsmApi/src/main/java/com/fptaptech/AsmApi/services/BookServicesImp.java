package com.fptaptech.AsmApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptaptech.AsmApi.models.Book;
import com.fptaptech.AsmApi.repository.BookRepository;

@Service
public class BookServicesImp implements BookServices{

	@Autowired
	BookRepository bookRepository;
	
	public BookServicesImp(BookRepository theBookRepository) {
		this.bookRepository = theBookRepository;
	}
	
	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		
		this.bookRepository.findAll().forEach(books::add);
		return books;
	}

	@Override
	public Book getBookById(Long id) {
		return this.bookRepository.findById(id).get();
	}

	@Override
	public Book insert(Book Book) {
		return this.bookRepository.save(Book);
	}

	@Override
	public void updateBook(Long id, Book Book) {
		Book bookFromDb = this.bookRepository.findById(id).get();
		bookFromDb.setAuthor(Book.author);
		bookFromDb.setName(Book.name);
		bookFromDb.setPrice(Book.price);
		this.bookRepository.save(bookFromDb);
	}

	@Override
	public void deleteBook(Long BookId) {
		this.bookRepository.deleteById(BookId);
	}

}
