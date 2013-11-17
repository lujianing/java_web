package demo.jsp_servlet_jdbc.servlet;

import demo.jsp_servlet_jdbc.Constant;
import demo.jsp_servlet_jdbc.service.TaskService;
import demo.jsp_servlet_jdbc.service.impl.TaskServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/task_create")
public class TaskCreateServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从 Session 中获取 userId
        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(Constant.SESSION_USER_ID);

        // 创建 Task
        taskService.createTask(userId, "");

        // 重定向到 welcome 首页
        response.sendRedirect(request.getContextPath() + "/welcome");
    }
}
