package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台显示图书
 *
 * @param
 * @return
 */
public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 进：获取用户请求参数：当前页码pageNo、每页图书记录数pageSize
        Integer pageNo = WebUtils.switchToInt(req.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.switchToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.page(pageNo, pageSize);

        bookPage.setUrl("client/bookServlet?action=page");
        // 出：将Page对象保存到request域中,转发到前端页面
        req.setAttribute("bookPage", bookPage);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }


    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer pageNo = WebUtils.switchToInt(req.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.switchToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 获取用户在页面中填写的最小价格、最大价格
        int minPrice = WebUtils.switchToInt(req.getParameter("min"), 0);
        int maxPrice = WebUtils.switchToInt(req.getParameter("max"), Integer.MAX_VALUE);

        // StringBuilder是线程不安全的，但这里由于在一个方法内调用，因此不需要线程安全。？
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        // 判断min与max参数是否为空，是否需要追加
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }

        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }

        Page<Book> bookPage = bookService.pageByPrice(pageNo, pageSize, minPrice, maxPrice);
        bookPage.setUrl(sb.toString());
        req.setAttribute("bookPage", bookPage);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
