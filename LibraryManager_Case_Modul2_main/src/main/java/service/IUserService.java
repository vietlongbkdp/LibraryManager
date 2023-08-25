package service;

import model.User;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    void addUser(User user) throws IOException;

    void deleteUser(long id);

    void updateUser(long id, User user);

    User findUser(long id);

    List<User> getAllUser();

}
