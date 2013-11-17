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

@WebServlet("/task_delete")
public class TaskDeleteServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        long taskId = Long.parseLong(request.getParameter("id"));

        // 删除 Task
        taskService.deleteTask(taskId);

        // 重定向到 welcome 首页
        response.sendRedirect(request.getContextPath() + "/welcome");
    }
}
