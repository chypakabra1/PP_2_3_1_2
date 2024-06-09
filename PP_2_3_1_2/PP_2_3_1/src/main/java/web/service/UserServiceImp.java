package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.dao.UserDao;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Component
@Repository
@Transactional(readOnly = true)
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableTransactionManagement
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {

        this.userDao = userDao;

    }

    //public static int USERS;
    /*@Override
    public List<User> users(Integer count) {
        List<User> users = new ArrayList<>();
        {

            users.add(new User(++USERS, "User1", "Lastname1", "user1@mail.ru"));
            users.add(new User(++USERS, "User2", "Lastname2", "user2@mail.ru"));
            users.add(new User(++USERS, "User3", "Lastname3", "user3@mail.ru"));
            users.add(new User(++USERS, "User4", "Lastname4", "user4@mail.ru"));
            users.add(new User(++USERS, "User5", "Lastname5", "user5@mail.ru"));
        }
        return users.stream().limit(count).collect(Collectors.toList());
    }*/

    /*private List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++USERS, "User1", "Lastname1", "user1@mail.ru"));
        users.add(new User(++USERS, "User2", "Lastname2", "user2@mail.ru"));
        users.add(new User(++USERS, "User3", "Lastname3", "user3@mail.ru"));
        users.add(new User(++USERS, "User4", "Lastname4", "user4@mail.ru"));
        users.add(new User(++USERS, "User5", "Lastname5", "user5@mail.ru"));
    }*/

    @Transactional
    @Override
    public List<User> index() {
        //return users;
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public User show(int id) {
        //return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        return userDao.getUser(id); //Удобнее примеры вводить тут, поэтому комменчу
    }

    @Transactional
    public void save(User user) {
        userDao.saveUser(user);// Примеры тут, поэтому комменчу
        /*user.setId(++USERS);
        users.add(user);*/
    }

    @Transactional
    @Override
    public void update(User user, int id) {
        userDao.updateUser(user, id); // Примеры тут, поэтому комменчу
        /*User userToBeUpdated = show(id);

        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastname(user.getLastname());
        userToBeUpdated.setEmail(user.getEmail());*/
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.deleteUser(id); // Примеры тут, поэтому комменчу
        //users.removeIf(user -> user.getId() == id);
    }
}
