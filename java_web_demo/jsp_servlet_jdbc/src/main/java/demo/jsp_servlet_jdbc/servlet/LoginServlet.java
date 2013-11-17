package demo.jsp_servlet_jdbc.servlet;

import demo.jsp_servlet_jdbc.Constant;
import demo.jsp_servlet_jdbc.service.UserService;
import demo.jsp_servlet_jdbc.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证用户身份
        long userId = userService.login(username, password);
        if (userId != 0) {
            // 将 userId 放入 Session 中
            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_USER_ID, userId);

            // 重定向到 welcome 页面
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else {
            // 登录失败
            request.setAttribute(Constant.LOGIN_FAILURE, true);

            // 停留在 login 页面
            request.getRequestDispatcher(Constant.JSP_PATH + "login.jsp").forward(request, response);
        }
    }
}
