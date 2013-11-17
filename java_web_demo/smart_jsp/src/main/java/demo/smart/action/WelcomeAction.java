package demo.smart.action;

import com.smart.framework.DataContext;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Inject;
import com.smart.framework.annotation.Request;
import com.smart.framework.base.BaseAction;
import com.smart.framework.bean.Page;
import demo.smart.Constant;
import demo.smart.entity.Task;
import demo.smart.service.TaskService;
import java.util.List;

@Bean
public class WelcomeAction extends BaseAction {

    @Inject
    private TaskService taskService;

    @Request("get:/welcome")
    public Page index() {
        // 从 Session 中获取 userId
        Long userId = DataContext.Session.get(Constant.SESSION_USER_ID);

        // 获取 Task 列表
        List<Task> taskList = taskService.getTaskList(userId);
        DataContext.Request.put("taskList", taskList);

        // 转发到 welcome 页面
        return new Page("welcome.jsp");
    }
}
