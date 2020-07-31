package com.book.test;

import com.book.pojo.Book;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "MySQL必知必会", "Ben Forta", new BigDecimal(24.5), 47, 12, null));
    }

    @Test
    public void updateBook() {
        // 这里必须给出id值，是根据id值定位记录的。所以函数名或许可以修改为updateBookById?但web层实现了就不用我们手动输入id了。
        bookService.updateBook(new Book(26, "MySQL必知必会", "Ben Forta", new BigDecimal(36.0), 47, 12, null));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(55);
        System.out.println(book);
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(12);
    }

    @Test
    public void page(){
        System.out.println(bookService.page(2,5));
    }
}