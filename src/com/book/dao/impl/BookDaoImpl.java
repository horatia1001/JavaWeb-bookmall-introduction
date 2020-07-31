package com.book.dao.impl;

import com.book.dao.BookDao;
import com.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(),
                book.getStock(), book.getImgPath());
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=?  " +
                "where `id`=?";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(),
                book.getImgPath(), book.getId());
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book ";
        return queryForList(Book.class,sql);
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);

    }

    @Override
    public Integer queryForPageItemTotalCount() {
        String sql = "select count(*) from t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin,int pageSize){
        String sql = "select id,name,price,author,sales,stock,img_path from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageItemTotalCountByPrice(int minPrice,int maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number) queryForSingleValue(sql,minPrice,maxPrice);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int minPrice, int maxPrice,int begin,int pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class,sql,minPrice,maxPrice,begin,pageSize);
    }
}
