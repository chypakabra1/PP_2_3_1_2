package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    /*@PersistenceContext
    private EntityManager em;

    public User update (User transientUser) {
        return em.merge(transientUser);
    }*/
    //@PersistenceContext
    @PersistenceContext(type= PersistenceContextType.EXTENDED)// теперь не получается удалить или создать
    //@PersistenceContext(type= PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user, int id) {
        /*user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setLastname(Objects.requireNonNull(params[1], "Lastname cannot be null"));
        user.setEmail(Objects.requireNonNull(params[2], "Email cannot be null"));
        executeInsideTransaction(em -> em.merge(user));*/
        /*User updateUser = em.find(User.class, id);
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setEmail(user.getEmail());*/
        em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(em.find(User.class, id));
        }
    }

    /*private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }*/

}
