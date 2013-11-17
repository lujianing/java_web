package demo.spring_hibernate.service.impl;

import demo.spring_hibernate.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public long login(String username, String password) {
        long userId = 0;

        String hql = "select id from User where username = ? and password = ?";
        List results = hibernateTemplate.find(hql, username, password);
        if (results != null && results.size() == 1) {
            userId = (Long) results.get(0);
        }

        return userId;
    }
}
