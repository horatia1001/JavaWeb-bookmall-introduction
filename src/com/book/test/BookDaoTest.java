package com.book.test;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "穹顶之下", "柴静", new BigDecimal(34.4), 4567, 1234, null));
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(null, "论语", "孟子", new BigDecimal(19.9), 1255, 546, null));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(14));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(3);
    }

    @Test
    public void queryForPageItemTotalCount(){
        System.out.println(bookDao.queryForPageItemTotalCount());
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(5, 4);
        for (Book book:books){
            System.out.println(book);
        }
    }
}