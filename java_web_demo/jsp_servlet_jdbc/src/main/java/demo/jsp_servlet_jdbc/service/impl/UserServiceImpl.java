package demo.jsp_servlet_jdbc.service.impl;

import demo.jsp_servlet_jdbc.Constant;
import demo.jsp_servlet_jdbc.entity.User;
import demo.jsp_servlet_jdbc.service.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public long login(String username, String password) {
        long userId = 0;

        Connection conn = null;
        try {
            // 连接数据库
            Class.forName(Constant.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);

            // 创建 SQL 语句
            String sql = "select id from user where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 执行 SQL 查询
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getLong("id");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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

        return userId;
    }
}
