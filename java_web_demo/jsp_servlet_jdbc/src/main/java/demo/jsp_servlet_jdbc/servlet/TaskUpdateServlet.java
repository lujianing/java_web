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

@WebServlet("/task_update")
public class TaskUpdateServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        long taskId = Long.parseLong(request.getParameter("id"));
        String todo = request.getParameter("todo");

        // 更新 Task
        taskService.updateTask(taskId, todo);

        // 重定向到 welcome 首页
        response.sendRedirect(request.getContextPath() + "/welcome");
    }
}
