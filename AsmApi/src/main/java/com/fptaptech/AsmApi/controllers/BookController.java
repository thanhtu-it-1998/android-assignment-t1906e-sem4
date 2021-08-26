package com.fptaptech.AsmApi.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.fptaptech.AsmApi.models.Book;
import com.fptaptech.AsmApi.services.BookServices;

@RestController
@RequestMapping("/api")
public class BookController {
	private BookServices bookServices;

	public BookController(BookServices theBookServices) {
		this.bookServices = theBookServices;
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> Books = this.bookServices.getBooks();
		return new ResponseEntity<>(Books, HttpStatus.OK);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		return new ResponseEntity<>(this.bookServices.getBookById(id), HttpStatus.OK);
	}

	@PostMapping("/book")
	public ResponseEntity<Book> saveBook(@RequestBody Book Book) {
		Book Book1 = this.bookServices.insert(Book);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Book", "/api/v1/book/" + Book1.getId().toString());
		return new ResponseEntity<>(Book1, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("BookId") Long BookId, @RequestBody Book Book) {
		this.bookServices.updateBook(BookId, Book);
		return new ResponseEntity<>(this.bookServices.getBookById(BookId), HttpStatus.OK);
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("BookId") Long BookId) {
		this.bookServices.deleteBook(BookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
