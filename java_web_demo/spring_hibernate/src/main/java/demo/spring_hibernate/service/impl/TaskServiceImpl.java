package demo.spring_hibernate.service.impl;

import demo.spring_hibernate.entity.Task;
import demo.spring_hibernate.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> getTaskList(long userId) {
        return hibernateTemplate.find("from Task where userId = ? order by created desc", userId);
    }

    @Override
    @Transactional
    public void createTask(long userId, String todo) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTodo(todo);
        task.setCreated(System.currentTimeMillis());
        hibernateTemplate.save(task);
    }

    @Override
    @Transactional
    public void updateTask(long taskId, String todo) {
        Task task = hibernateTemplate.get(Task.class, taskId);
        if (task != null) {
            task.setTodo(todo);
            hibernateTemplate.update(task);
        }
    }

    @Override
    @Transactional
    public void deleteTask(long taskId) {
        Task task = hibernateTemplate.get(Task.class, taskId);
        if (task != null) {
            hibernateTemplate.delete(task);
        }
    }
}
