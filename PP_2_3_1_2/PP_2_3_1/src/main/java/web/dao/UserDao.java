package web.dao;

import web.model.User;

import java.util.List;


public interface UserDao {

    /*<User> get(long id);
    List<User> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);*/

    List<User> getAllUsers();

    User getUser(int id);

    void saveUser(User user);

    void updateUser(User user, int id);

    void deleteUser(int id);
}
