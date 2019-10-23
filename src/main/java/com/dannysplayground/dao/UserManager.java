package com.dannysplayground.dao;

import java.util.List;
import com.dannysplayground.model.User;

public interface UserManager {
    public List<User> getList();
    public User getUser(Integer id);
    public int saveUser(User value);
}
