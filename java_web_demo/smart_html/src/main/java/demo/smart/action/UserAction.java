package demo.smart.action;

import com.smart.framework.DataContext;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Inject;
import com.smart.framework.annotation.Request;
import com.smart.framework.base.BaseAction;
import com.smart.framework.bean.Result;
import demo.smart.Constant;
import demo.smart.service.UserService;
import java.util.Map;

@Bean
public class UserAction extends BaseAction {

    @Inject
    private UserService userService;

    @Request("post:/login")
    public Result login(Map<String, Object> fieldMap) {
        // 获取请求参数
        String username = (String) fieldMap.get("username");
        String password = (String) fieldMap.get("password");

        // 验证用户身份
        boolean success = false;
        long userId = userService.login(username, password);
        if (userId != 0) {
            // 将 userId 放入 Session 中
            DataContext.Session.put(Constant.SESSION_USER_ID, userId);
            success = true;
        }

        // 返回数据
        return new Result(success);
    }

    @Request("get:/logout")
    public Result logout() {
        // 清空 Session
        DataContext.Session.removeAll();

        // 返回数据
        return new Result(true);
    }
}
