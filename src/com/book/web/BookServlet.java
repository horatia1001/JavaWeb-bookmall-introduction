package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();


    protected void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询全部图书
        List<Book> books = bookService.queryBooks();

        // 把图书数据保存到req域中(到目标jsp中使用request域)
        // request域对象：可以保存数据在同一个request对象中使用。经常用于在转发的时候传递数据。
        req.setAttribute("books", books);
        // 请求转发到 /pages/manager/book_manager.jsp 中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int pageNo = WebUtils.switchToInt(req.getParameter("pageNo"),1);
        pageNo+=1;

        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = WebUtils.copyParaToBean(parameterMap, new Book());

        if (bookService.addBook(book) > 0) {
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" +pageNo);
            /*
            如果不请求重定向而使用请求转发就会重复添加图书
            当用户提交表单后浏览器会保存最后一次请求的全部信息，当用户按下F5时，浏览器又会重新提交信息
            重定向已经是另一次请求，跟上一次请求浏览器保存的信息就无关了
            */
        }

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String idStr = req.getParameter("id");
        int id = 0;
        id = WebUtils.switchToInt(idStr,1);
        bookService.deleteBookById(id);

        try {
            int pageNo = WebUtils.switchToInt(req.getParameter("pageNo"),1);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" +pageNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void queryBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = 0;
        id = Integer.parseInt(idStr);
        Book book = bookService.queryBookById(id);

        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = WebUtils.copyParaToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);

        try {
            int pageNo = WebUtils.switchToInt(req.getParameter("pageNo"),1);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" +pageNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取用户请求参数：当前页码pageNo、每页图书记录数pageSize
        Integer pageNo = WebUtils.switchToInt(req.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.switchToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.page(pageNo, pageSize);

        // 设置分页条的请求地址
        bookPage.setUrl("manager/bookServlet?action=page");

        // 将Page对象保存到request域中,转发到前端页面
        req.setAttribute("bookPage", bookPage);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }
}
