package fr.debnet.ircrpg;

import fr.debnet.ircrpg.interfaces.IEntity;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @author Marc
 */
public class DAO {

    // Logger
    private static final Logger logger = Logger.getLogger(DAO.class.getName());
    
    static {
        try {
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            
            AnnotationConfiguration config = new AnnotationConfiguration().configure();
            for (Map.Entry<String, String> entry : Config.HIBERNATE_CONFIG.entrySet()) 
                config.setProperty(entry.getKey(), entry.getValue());
            sessionFactory = config.buildSessionFactory();
        } catch (IOException | HibernateException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private static final SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Get entity from database
     * @param <T> Type entity
     * @param _class Entity class
     * @param id Primary key value
     * @return Entity object
     */
    @SuppressWarnings("unchecked")
    public static <T extends IEntity> T getObject(Class<T> _class, Long id) {
        T object = null;
        Session session = sessionFactory.getCurrentSession(); 
        try {
            object = (T)session.get(_class, id);
            Hibernate.initialize(object);
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s] (id: %d) %s", 
                _class.getSimpleName(), id, ex.getLocalizedMessage()));
        } finally {
            //session.close();
        }
        return object;
    }
    
    /**
     * Get entity from database
     * @param <T> Type entity
     * @param sql HSQL Query
     * @param args Arguments
     * @return Entity object
     */
    public static <T extends IEntity> T getObject(String sql, Parameter... parameters) {
        return DAO.getObject(sql, false, parameters);
    }
    
    /**
     * Get entity from database
     * @param <T> Type entity
     * @param sql HSQL Query
     * @param limit Limit result ?
     * @param args Arguments
     * @return Entity object
     */
    @SuppressWarnings("unchecked")
    public static <T extends IEntity> T getObject(String sql, boolean limit, Parameter... parameters) {
        T object = null;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            Query query = session.createQuery(sql);
            if (parameters != null) {
                for (Parameter parameter : parameters) {
                    query.setParameter(parameter.getName(), parameter.getValue());
                }
            }
            if (limit) query.setMaxResults(1);
            object = (T)query.uniqueResult();
            Hibernate.initialize(object);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s] %s", 
                sql, ex.getLocalizedMessage()));
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            //session.close();
        }
        return object;
    }

    /**
     * Get entity list from database
     * @param <T> Entity type
     * @param sql HSQL Query
     * @param args Arguments
     * @return Entity list
     */
    public static <T extends IEntity> List<T> getObjectList(String sql, Parameter... parameters) {
        return DAO.getObjectList(sql, 0, parameters);
    }

    /**
     * Get entity list from database
     * @param <T> Entity type
     * @param sql HSQL Query
     * @param limit Number of returned elements
     * @param args Arguments
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static <T extends IEntity> List<T> getObjectList(String sql, int limit, Parameter... parameters) {
        List<T> list = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            Query query = session.createQuery(sql);
            if (parameters != null) {
                for (Parameter parameter : parameters) {
                    query.setParameter(parameter.getName(), parameter.getValue());
                }
            }
            if (limit > 0) query.setMaxResults(limit);
            list = query.list();
            for (T object : list) Hibernate.initialize(object);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s]", 
                sql, ex.getLocalizedMessage()));
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            //session.close();
        }
        return list;
    }

    /**
     * Update entity in database
     * @param <T> Entity type
     * @param object Entity object
     * @return True if successfully updated, false else
     */
    public static <T extends IEntity> boolean setObject(T object) {
        boolean b = false;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            session.update(object);
            transaction.commit();
            b = true;
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s] (id: %d) %s", 
                object.getModel(), object.getId(), ex.getLocalizedMessage()));
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            //session.close();
        }
        return b;
    }

    /**
     * Insert entity in database
     * @param <T> Entity type
     * @param object Entity object
     * @return Primary key value
     */
    public static <T extends IEntity> Long addObject(T object) {
        Long id = null;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            id = (Long) session.save(object);
            object.setId(id);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s] %s", 
                object.getModel(), ex.getLocalizedMessage()));
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            //session.close();
        }
        return id;
    }

    /**
     * Delete entity from database
     * @param <T> Entity type
     * @param object Entity object
     * @return True if successfully deleted, false else
     */
    public static <T extends IEntity> boolean delObject(T object) {
        boolean b = false;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            session.delete(object);
            transaction.commit();
            b = true;
        } catch (HibernateException ex) {
            logger.log(Level.SEVERE, String.format("[%s] (id: %d) %s", 
                object.getModel(), object.getId(), ex.getLocalizedMessage()));
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            //session.close();
        }
        return b;
    }
}
