package demo.smart.service.impl;

import com.smart.framework.DataSet;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Transaction;
import com.smart.framework.base.BaseService;
import demo.smart.entity.Task;
import demo.smart.service.TaskService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Bean
public class TaskServiceImpl extends BaseService implements TaskService {

    @Override
    public List<Task> getTaskList(long userId) {
        return DataSet.selectList(Task.class, "user_id = ?", "created desc", userId);
    }

    @Override
    @Transaction
    public void createTask(long userId, String todo) {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("user_id", userId);
        fieldMap.put("todo", todo);
        fieldMap.put("created", System.currentTimeMillis());
        DataSet.insert(Task.class, fieldMap);
    }

    @Override
    @Transaction
    public void updateTask(long taskId, String todo) {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("todo", todo);
        DataSet.update(Task.class, fieldMap, "id = ?", taskId);
    }

    @Override
    @Transaction
    public void deleteTask(long taskId) {
        DataSet.delete(Task.class, "id = ?", taskId);
    }
}
