package com.book.dao;

import com.book.pojo.Book;

import java.util.List;

public interface BookDao {

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
     * 返回总记录数
     * @return
     */
    Integer queryForPageItemTotalCount();

    /**
     * 返回当前图书页面数据
     * @return
     */
    List<Book> queryForPageItems(int begin,int pageSize);

    /**
     * 返回价格区间的总记录数
     * @return
     */
    Integer queryForPageItemTotalCountByPrice(int minPrice,int maxPrice);

    /**
     * 根据价格查询图书
     * @return
     */
    List<Book> queryForPageItemsByPrice(int minPrice, int maxPrice,int begin,int pageSize);
}
