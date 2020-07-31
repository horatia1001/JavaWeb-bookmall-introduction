package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    /**
     * 登录模块
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1.获取请求的参数
         * 2.查询用户名与密码是否正确
         * 3.正确：跳转到登录成功页面
         *   不正确：跳转回登录页面
         * */
        // 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 获取请求的参数的Map
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 将请求参数封装到User对象中
        User user = WebUtils.copyParaToBean(parameterMap, new User());

        User loginUser = userService.login(user);

        // 验证用户名与密码是否正确：在数据库中查找是否存在对应此用户名和密码的记录
        if (loginUser == null) {
            req.setAttribute("loginMsg", "用户名或密码错误！");    // 页面回显错误信息
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);    // 跳转回登录页面
        } else {
            // 设置session属性，使用户保持登录状态
            req.getSession().setAttribute("loginUser",loginUser);
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);   // 跳转到登录成功页面
        }
    }

    /**
     * 及时验证用户名是否可用
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取请求参数
        String username = req.getParameter("username");

        // 查询数据库是否存在此用户名
        boolean existUsername = userService.existUsername(username);

        // 把返回结果封装为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);

        // 通过json对象转发到前端
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);


    }

        /**
         * 注销模块
         * @param req
         * @param resp
         */
    protected void logout(HttpServletRequest req, HttpServletResponse resp){
        // 清除session中的用户信息：使session失效
        req.getSession().invalidate();
        // 重定向回首页
        try {
            resp.sendRedirect(req.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 注册模块
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1.获取请求的参数
         * 2.查看参数（用户名）是否可用
         * 3.可用：跳转到注册成功页面
         *   不可用：跳转到注册页面重新注册
         * */

        // 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 获取请求的参数的Map
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 将请求参数封装到User对象中
        User user = WebUtils.copyParaToBean(parameterMap, new User());

        // 获取session中的验证码
        String checkCode = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 立即删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 验证用户码是否正确（忽略大小写）
        if (checkCode != null && checkCode.equalsIgnoreCase(code)) {
            // 验证用户名是否已存在
            if (userService.existUsername(username)) {
                req.setAttribute("RegisterUsernameMsg", "用户名已存在！");
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);     // 跳转回注册页面
            } else {
                userService.register(user);       // 注册用户
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req, resp);  // 跳转到注册成功页面
            }
        } else {
            System.out.println("验证码错误");
            req.setAttribute("RegisterCodeMsg", "验证码错误！");
            // 转发用户提交的用户名和邮箱（它们暂时保存在req域中），用于跳转页面后回显在输入框中
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);   // 跳转回注册页面
        }

    }
}
