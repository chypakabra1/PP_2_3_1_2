package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Service
@Service
//@Component
@Repository
public class UserServiceImp implements UserService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {

        this.userDao = userDao;

    }

    public static int USERS;
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

    private List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++USERS, "User1", "Lastname1", "user1@mail.ru"));
        users.add(new User(++USERS, "User2", "Lastname2", "user2@mail.ru"));
        users.add(new User(++USERS, "User3", "Lastname3", "user3@mail.ru"));
        users.add(new User(++USERS, "User4", "Lastname4", "user4@mail.ru"));
        users.add(new User(++USERS, "User5", "Lastname5", "user5@mail.ru"));
    }

    public List<User> index() {
        return users;
    }

    @Override
    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        //return userDao.getUser(id); Удобнее примеры вводить тут, поэтому комменчу
    }

    public void save(User user) {
        /*userDao.saveUser(user);*/// Примеры тут, поэтому комменчу
        user.setId(++USERS);
        users.add(user);
    }

    @Override
    public void update(User user, int id) {
        /*userDao.updateUser(user, id);*/ // Примеры тут, поэтому комменчу
        User userToBeUpdated = show(id);

        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastname(user.getLastname());
        userToBeUpdated.setEmail(user.getEmail());
    }

    @Override
    public void delete(int id) {
        //userDao.deleteUser(id); // Примеры тут, поэтому комменчу
        users.removeIf(user -> user.getId() == id);
    }
}
