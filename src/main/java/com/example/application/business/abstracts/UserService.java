package com.example.application.business.abstracts;

import com.example.application.model.user.User;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    void createUser(User user);

    void updateUser(long id,User user);
    void deleteUser(long id);

    // get
    User getUser(long id);
    ListDataProvider<User> getAllUsers();


    // search
    User getUserByUsername(String username);
    User getUserByEmail(String email);

    long getUserCount();
}
