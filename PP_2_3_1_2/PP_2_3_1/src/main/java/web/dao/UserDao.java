package web.dao;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /*<User> get(long id);
    List<User> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);*/

    public List<User> getAllUsers();

    public User getUser(int id);

    public void saveUser(User user);

    public void updateUser(User user, int id);

    public void deleteUser(int id);
}
