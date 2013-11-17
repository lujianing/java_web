package demo.smart.entity;

import com.smart.framework.base.BaseEntity;

public class Task extends BaseEntity {

    private long userId;

    private String todo;

    private long created;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
