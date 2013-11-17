package demo.smart.action;

import com.smart.framework.DataContext;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Inject;
import com.smart.framework.annotation.Request;
import com.smart.framework.base.BaseAction;
import com.smart.framework.bean.Page;
import com.smart.framework.util.CastUtil;
import demo.smart.Constant;
import demo.smart.service.UserService;
import java.util.Map;

@Bean
public class UserAction extends BaseAction {

    @Inject
    private UserService userService;

    @Request("post:/login")
    public Page login(Map<String, Object> fieldMap) {
        // 获取请求参数
        String username = CastUtil.castString(fieldMap.get("username"));
        String password = CastUtil.castString(fieldMap.get("password"));

        // 验证用户身份
        long userId = userService.login(username, password);
        if (userId != 0) {
            // 将 userId 放入 Session 中
            DataContext.Session.put(Constant.SESSION_USER_ID, userId);

            // 重定向到 welcome 页面
            return new Page("/welcome");
        } else {
            // 登录失败
            DataContext.Request.put(Constant.LOGIN_FAILURE, true);

            // 停留在 login 页面
            return new Page("login.jsp");
        }
    }

    @Request("get:/logout")
    public Page logout() {
        // 清空 Session
        DataContext.Session.removeAll();

        // 重定向到首页
        return new Page("/");
    }
}
