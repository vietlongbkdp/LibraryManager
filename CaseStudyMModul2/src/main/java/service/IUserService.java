package service;

import model.User;

import java.util.List;

public interface IUserService {
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(long id, User user);
    User findUser(long id);
    List<User> getAllUser();
}
