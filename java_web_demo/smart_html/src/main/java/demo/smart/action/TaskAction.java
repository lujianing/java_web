package demo.smart.action;

import com.smart.framework.DataContext;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Inject;
import com.smart.framework.annotation.Request;
import com.smart.framework.base.BaseAction;
import com.smart.framework.bean.Page;
import com.smart.framework.bean.Result;
import com.smart.framework.util.CastUtil;
import com.smart.framework.util.CollectionUtil;
import demo.smart.Constant;
import demo.smart.entity.Task;
import demo.smart.service.TaskService;
import java.util.List;
import java.util.Map;

@Bean
public class TaskAction extends BaseAction {

    @Inject
    private TaskService taskService;

    @Request("get:/task")
    public Result index() {
        // 从 Session 中获取 userId
        Long userId = DataContext.Session.get(Constant.SESSION_USER_ID);

        // 获取 Task 列表
        List<Task> taskList = taskService.getTaskList(userId);
        DataContext.Request.put("taskList", taskList);

        // 返回结果
        if (CollectionUtil.isNotEmpty(taskList)) {
            return new Result(true).data(taskList);
        } else {
            return new Result(false);
        }
    }

    @Request("post:/task")
    public Result create() {
        // 从 Session 中获取 userId
        Long userId = DataContext.Session.get(Constant.SESSION_USER_ID);

        // 创建 Task
        taskService.createTask(userId, "");

        // 返回结果
        return new Result(true);
    }

    @Request("put:/task")
    public Result update(Map<String, Object> fieldMap) {
        // 获取请求参数
        long taskId = CastUtil.castLong(fieldMap.get("id"));
        String todo = CastUtil.castString(fieldMap.get("todo"));

        // 更新 Task
        taskService.updateTask(taskId, todo);

        // 返回结果
        return new Result(true);
    }

    @Request("delete:/task")
    public Result delete(Map<String, Object> fieldMap) {
        // 获取请求参数
        long taskId = CastUtil.castLong(fieldMap.get("id"));

        // 删除 Task
        taskService.deleteTask(taskId);

        // 返回结果
        return new Result(true);
    }
}
