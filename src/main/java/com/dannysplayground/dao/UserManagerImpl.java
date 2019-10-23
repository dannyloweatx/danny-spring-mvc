package com.dannysplayground.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dannysplayground.model.User;

@Service("userService")
@Transactional
public class UserManagerImpl implements UserManager {
    private static SessionFactory FACTORY;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);
    
    public UserManagerImpl() {
        try {
            FACTORY = new Configuration().configure().buildSessionFactory();
         } catch (Exception e) { 
             LOGGER.error("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e); 
         }
    }
    
    public List<User> getList() {
        Session session = FACTORY.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
           tx = session.beginTransaction();
           users = session.createQuery("FROM User").list(); 
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           LOGGER.error(e.getMessage()); 
        } finally {
           session.close(); 
        }
        
        return users;

    }

    public User getUser(Integer id) {
        Session session = FACTORY.openSession();
        Transaction tx = null;
        User user = null;
        try {
           tx = session.beginTransaction();
           user = session.get(User.class, id); 
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           LOGGER.error(e.getMessage()); 
        } finally {
           session.close(); 
        }
        
        return user;
    }

    public int saveUser(User value) {
        Session session = FACTORY.openSession();
        Transaction tx = null;
        Integer id = value.getId();
        
        try {
           tx = session.beginTransaction();
           if (id!=null) {
               session.update(value);
           } else {
               id = (Integer) session.save(value); 
           }
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           id = -1;
           LOGGER.error(e.getMessage()); 
        } finally {
           session.close(); 
        }
        return id;
    }
    
    public boolean deleteUser(Integer id) {
        Session session = FACTORY.openSession();
        Transaction tx = null;

        try {
           tx = session.beginTransaction();
           session.delete((User) session.get(User.class, id)); 
           tx.commit();
        } catch (Exception e) {
           if (tx!=null) tx.rollback();
           LOGGER.error(e.getMessage());
           return false;
        } finally {
           session.close(); 
        }
        
        return true;
    }

}
