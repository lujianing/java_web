package demo.smart.service.impl;

import com.smart.framework.DataSet;
import com.smart.framework.annotation.Bean;
import com.smart.framework.base.BaseService;
import demo.smart.entity.User;
import demo.smart.service.UserService;

@Bean
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public long login(String username, String password) {
        long userId = 0;

        User user = DataSet.select(User.class, "username= ? and password = ?", username, password);
        if (user != null) {
            userId = user.getId();
        }

        return userId;
    }
}
