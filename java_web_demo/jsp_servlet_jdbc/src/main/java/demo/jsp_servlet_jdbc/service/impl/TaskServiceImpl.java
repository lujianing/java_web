package demo.jsp_servlet_jdbc.service.impl;

import demo.jsp_servlet_jdbc.Constant;
import demo.jsp_servlet_jdbc.entity.Task;
import demo.jsp_servlet_jdbc.service.TaskService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    @Override
    public List<Task> getTaskList(long userId) {
        List<Task> taskList = new ArrayList<Task>();

        Connection conn = null;
        try {
            // 连接数据库
            Class.forName(Constant.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);

            // 创建 SQL 语句
            String sql = "select * from task where user_id = ? order by created desc";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);

            // 执行 SQL 查询
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String todo = rs.getString("todo");
                long created = rs.getLong("created");

                Task task = new Task();
                task.setId(id);
                task.setTodo(todo);
                task.setCreated(created);

                taskList.add(task);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return taskList;
    }

    @Override
    public void createTask(long userId, String todo) {
        Connection conn = null;
        try {
            // 连接数据库
            Class.forName(Constant.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);
            conn.setAutoCommit(false);

            // 创建 SQL 语句
            String sql = "insert into task (user_id, todo, created) values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.setString(2, todo);
            pstmt.setLong(3, System.currentTimeMillis());

            // 执行 SQL 查询
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                // 提交事务
                conn.commit();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateTask(long taskId, String todo) {
        Connection conn = null;
        try {
            // 连接数据库
            Class.forName(Constant.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);
            conn.setAutoCommit(false);

            // 创建 SQL 语句
            String sql = "update task set todo = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, todo);
            pstmt.setLong(2, taskId);

            // 执行 SQL 查询
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                // 提交事务
                conn.commit();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteTask(long taskId) {
        Connection conn = null;
        try {
            // 连接数据库
            Class.forName(Constant.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);
            conn.setAutoCommit(false);

            // 创建 SQL 语句
            String sql = "delete from task where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, taskId);

            // 执行 SQL 查询
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                // 提交事务
                conn.commit();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
