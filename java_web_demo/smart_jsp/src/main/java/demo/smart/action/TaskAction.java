package demo.smart.action;

import com.smart.framework.DataContext;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Inject;
import com.smart.framework.annotation.Request;
import com.smart.framework.base.BaseAction;
import com.smart.framework.bean.Page;
import com.smart.framework.util.CastUtil;
import demo.smart.Constant;
import demo.smart.service.TaskService;
import java.util.Map;

@Bean
public class TaskAction extends BaseAction {

    @Inject
    private TaskService taskService;

    @Request("get:/task_create")
    public Page create() {
        // 从 Session 中获取 userId
        Long userId = DataContext.Session.get(Constant.SESSION_USER_ID);

        // 创建 Task
        taskService.createTask(userId, "");

        // 重定向到 welcome 首页
        return new Page("/welcome");
    }

    @Request("post:/task_update")
    public Page update(Map<String, Object> fieldMap) {
        // 获取请求参数
        long taskId = CastUtil.castLong(fieldMap.get("id"));
        String todo = CastUtil.castString(fieldMap.get("todo"));

        // 更新 Task
        taskService.updateTask(taskId, todo);

        // 重定向到 welcome 首页
        return new Page("/welcome");
    }

    @Request("get:/task_delete")
    public Page delete(Map<String, Object> fieldMap) {
        // 获取请求参数
        long taskId = CastUtil.castLong(fieldMap.get("id"));

        // 删除 Task
        taskService.deleteTask(taskId);

        // 重定向到 welcome 首页
        return new Page("/welcome");
    }
}
