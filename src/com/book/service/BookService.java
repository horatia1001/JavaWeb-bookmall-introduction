package com.book.service;

import com.book.pojo.Book;
import com.book.pojo.Page;

import java.util.List;

/**
 * 用户对图书进行管理的接口
 * @param
 * @return
 */
public interface BookService {

    /**
     * 添加图书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 修改图书信息
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 查找多本图书
     * @return 图书集合
     */
    List<Book> queryBooks();


    /**
     * 根据图书id查找图书
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 删除图书
     * @param id
     * @return
     */
    int deleteBookById(Integer id);


    /**
     * 返回当前页图书数据对象
     * @param pageNo
     * @return
     */
    Page<Book> page(int pageNo,int pageSize);

    /**
     * 返回当前价格区间图书数据
     * @param pageNo
     * @param pageSize
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize,int minPrice, int maxPrice);

}
