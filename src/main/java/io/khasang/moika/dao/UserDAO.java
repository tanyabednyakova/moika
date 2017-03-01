package io.khasang.moika.dao;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);
    User getUserByLogin(String login);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    boolean containUser(long id);
    boolean containUserLogin(String login);
}
