package demo.spring_hibernate.controller;

import demo.spring_hibernate.Constant;
import demo.spring_hibernate.service.TaskService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/task_create", method = RequestMethod.GET)
    public String create(HttpServletRequest request) {
        // 从 Session 中获取 userId
        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(Constant.SESSION_USER_ID);

        // 创建 Task
        taskService.createTask(userId, "");

        // 重定向到 welcome 首页
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/task_update", method = RequestMethod.POST)
    public String update(HttpServletRequest request) {
        // 获取请求参数
        long taskId = Long.parseLong(request.getParameter("id"));
        String todo = request.getParameter("todo");

        // 更新 Task
        taskService.updateTask(taskId, todo);

        // 重定向到 welcome 首页
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/task_delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        // 获取请求参数
        long taskId = Long.parseLong(request.getParameter("id"));

        // 删除 Task
        taskService.deleteTask(taskId);

        // 重定向到 welcome 首页
        return "redirect:/welcome";
    }
}
