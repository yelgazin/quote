package com.kameleoon.trialtask.quote.dao;

import com.kameleoon.trialtask.quote.entity.User;

public interface UserDAO {

    User createUser(User user);

    User getUserByName(String userName);
}
