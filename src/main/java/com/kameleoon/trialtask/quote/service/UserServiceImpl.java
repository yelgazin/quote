package com.kameleoon.trialtask.quote.service;

import com.kameleoon.trialtask.quote.dao.UserDAO;
import com.kameleoon.trialtask.quote.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User createUser(User user) {
        return userDAO.createUser(user);
    }
}
