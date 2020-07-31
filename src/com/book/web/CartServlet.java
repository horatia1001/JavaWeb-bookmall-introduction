package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /* 获取图书信息 */
        // 获取要添加到购物车的图书id
        int id = WebUtils.switchToInt(req.getParameter("id"), 0);
        // 根据id查询数据库得到图书
        Book book = bookService.queryBookById(id);

        // 将Book对象转换为CartItem对象
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 先获取req域中购物车属性，判断是否已有购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        // 将CartItem对象添加到购物车中
        cart.addItem(cartItem);
        System.out.println(cart.toString());

        // 添加成功后跳转回原页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取图书id
        Integer id = WebUtils.switchToInt(req.getParameter("id"), 0);

        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 删除图书
        if(cart != null){
            cart.deleteItem(id);
        }

        System.out.println(cart.toString());

        // 重定向到原页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 清空购物车
        if(cart != null) {
            cart.clearCart();
        }
        System.out.println(cart);
        resp.sendRedirect(req.getHeader("Referer"));

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 接收请求参数
        Integer id = WebUtils.switchToInt(req.getParameter("id"), 0);
        Integer updateCount = WebUtils.switchToInt(req.getParameter("count"),1);
        // 更新
        if(cart != null) {
            cart.updateCount(id, updateCount);
        }
        System.out.println(cart);

        resp.sendRedirect(req.getHeader("Referer"));
    }


}
