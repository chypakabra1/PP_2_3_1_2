package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {

        this.userDao = userDao;

    }

    @Transactional
    @Override
    public List<User> index() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public User show(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    public void save(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void update(User user, int id) {
        userDao.updateUser(user, id);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.deleteUser(id);
    }
}
