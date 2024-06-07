package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserService {
    //public List<User> getAllUsers();

    public List<User> index();

    public User show(int id);

    public void save(User user);

    public void update(User user, int id);

    public void delete(int id);
    //List<User> users(Integer count);
}
