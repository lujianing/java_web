package demo.spring_hibernate.controller;

import demo.spring_hibernate.Constant;
import demo.spring_hibernate.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
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
            return "redirect:/welcome";
        } else {
            // 登录失败
            request.setAttribute(Constant.LOGIN_FAILURE, true);

            // 停留在 login 页面
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // 清空 Session
        HttpSession session = request.getSession();
        session.invalidate();

        // 重定向到首页
        return "redirect:/";
    }
}
