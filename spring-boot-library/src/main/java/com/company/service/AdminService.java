package com.company.service;

import com.company.dao.BookRepository;
import com.company.entity.Book;
import com.company.requestModels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;

    @Autowired
    public AdminService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void postBook(AddBookRequest request){
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setCopies(request.getCopies());
        book.setCopiesAvailable(request.getCopies());
        book.setCategory(request.getCategory());
        book.setImg(request.getImg());
        bookRepository.save(book);
    }
}
