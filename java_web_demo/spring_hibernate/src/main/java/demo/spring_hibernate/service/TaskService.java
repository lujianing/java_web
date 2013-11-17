package demo.spring_hibernate.service;

import demo.spring_hibernate.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getTaskList(long userId);

    void createTask(long userId, String todo);

    void updateTask(long taskId, String todo);

    void deleteTask(long taskId);
}
