package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int updateBook(Book book) {
//        int updateFlag = bookDao.updateBook(book);
//        if (updateFlag > 0) {
//            System.out.println("修改成功");
//        } else {
//            System.out.println("修改失败");
//        }
//        return updateFlag;
        return bookDao.updateBook(book);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();

        /**
         * 以下操作与数据库交互获取
         */
        // 获取总图书记录数
        int pageItemTotalCount = bookDao.queryForPageItemTotalCount();

        // 计算图书总页数
        Integer pageTotalCount = pageItemTotalCount / pageSize;
        if (pageItemTotalCount % pageSize > 0) {
            pageTotalCount += 1;         // 余数大于0，即最后一页没有满
        }


        if (pageNo < 1) {
            bookPage.setPageNo(1);
        } else if (pageNo > pageTotalCount) {
            bookPage.setPageNo(pageTotalCount);
        } else {
            bookPage.setPageNo(pageNo);
        }

        bookPage.setPageSize(pageSize);

        // 计算当前页图书的索引范围:开头
        int begin = (bookPage.getPageNo() - 1) * pageSize;

        // 获取当前页的图书记录项
        List<Book> pageItems = bookDao.queryForPageItems(begin, Page.PAGE_SIZE);

        bookPage.setPageItemTotalCount(pageItemTotalCount);
        bookPage.setPageTotalCount(pageTotalCount);
        bookPage.setPageItems(pageItems);

        return bookPage;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> bookPage = new Page<>();
        /**
         * 以下操作与数据库交互获取
         */
        // 获取总图书记录数
        int pageItemTotalCount = bookDao.queryForPageItemTotalCountByPrice(minPrice, maxPrice);

        // 计算图书总页数
        Integer pageTotalCount = pageItemTotalCount / pageSize;
        if (pageItemTotalCount % pageSize > 0) {
            pageTotalCount += 1;         // 余数大于0，即最后一页没有满
        }

        if (pageNo < 1) {
            bookPage.setPageNo(1);
        } else if (pageNo > pageTotalCount) {
            bookPage.setPageNo(pageTotalCount);
        } else {
            bookPage.setPageNo(pageNo);
        }

        bookPage.setPageSize(pageSize);

        // 计算当前页图书的索引范围:开头
        int begin = (bookPage.getPageNo() - 1) * pageSize;

        // 查询当前价格区间的图书
        List<Book> pageItems = bookDao.queryForPageItemsByPrice(minPrice, maxPrice, begin, pageSize);

        bookPage.setPageItemTotalCount(pageItemTotalCount);
        bookPage.setPageTotalCount(pageTotalCount);
        bookPage.setPageItems(pageItems);

        return bookPage;
    }
}
