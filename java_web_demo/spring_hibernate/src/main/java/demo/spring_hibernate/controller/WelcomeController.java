package demo.spring_hibernate.controller;

import demo.spring_hibernate.Constant;
import demo.spring_hibernate.entity.Task;
import demo.spring_hibernate.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        // 从 Session 中获取 userId
        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(Constant.SESSION_USER_ID);

        // 获取 Task 列表
        List<Task> taskList = taskService.getTaskList(userId);
        request.setAttribute("taskList", taskList);

        // 转发到 welcome 页面
        return "welcome";
    }
}
