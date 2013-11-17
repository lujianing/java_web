package demo.jsp_servlet_jdbc.servlet;

import demo.jsp_servlet_jdbc.Constant;
import demo.jsp_servlet_jdbc.entity.Task;
import demo.jsp_servlet_jdbc.service.TaskService;
import demo.jsp_servlet_jdbc.service.impl.TaskServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从 Session 中获取 userId
        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(Constant.SESSION_USER_ID);

        // 获取 Task 列表
        List<Task> taskList = taskService.getTaskList(userId);
        request.setAttribute("taskList", taskList);

        // 转发到 welcome 页面
        request.getRequestDispatcher(Constant.JSP_PATH + "welcome.jsp").forward(request, response);
    }
}
