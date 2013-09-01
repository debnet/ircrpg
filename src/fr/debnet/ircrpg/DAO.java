package fr.debnet.ircrpg;

import fr.debnet.ircrpg.interfaces.IEntity;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

    static {
        try {
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            
            AnnotationConfiguration config = new AnnotationConfiguration().configure();
            for (Map.Entry<String, String> entry : Config.HIBERNATE_CONFIG.entrySet()) 
                config.setProperty(entry.getKey(), entry.getValue());

            sessionFactory = config.buildSessionFactory();
        } catch (Exception ex) {
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
        Session session = sessionFactory.openSession();
        try {
            object = (T)session.get(_class, id);
            Hibernate.initialize(object);
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
        } finally {
            session.close();
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
    public static <T extends IEntity> T getObject(String sql, Object... args) {
        return DAO.getObject(sql, false, args);
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
    public static <T extends IEntity> T getObject(String sql, boolean limit, Object... args) {
        T object = null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(sql);
            if (args != null)
                for (int i = 0; i < args.length; i++)
                    query.setParameter(sql, args[i]);
            if (limit) query.setMaxResults(1);
            object = (T)query.uniqueResult();
            Hibernate.initialize(object);
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
        } finally {
            session.close();
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
    public static <T extends IEntity> List<T> getObjectList(String sql, Object... args) {
        return DAO.getObjectList(sql, 0, args);
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
    public static <T extends IEntity> List<T> getObjectList(String sql, int limit, Object... args) {
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(sql);
            if (args != null)
                for (int i = 0; i < args.length; i++)
                    query.setParameter(sql, args[i]);
            if (limit > 0) query.setMaxResults(limit);
            list = query.list();
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
        } finally {
            session.close();
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
        synchronized (object) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            try {
                session.update(object);
                transaction.commit();
                b = true;
            } catch (HibernateException e) {
                Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
            } finally {
                if (!transaction.wasCommitted()) transaction.rollback();
                session.close();
            }
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
        synchronized (object) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            try {
                id = (Long) session.save(object);
                object.setId(id);
                transaction.commit();
            } catch (HibernateException e) {
                Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
            } finally {
                if (!transaction.wasCommitted()) transaction.rollback();
                session.close();
            }
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
        synchronized (object) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            try {
                session.delete(object);
                transaction.commit();
                b = true;
            } catch (HibernateException e) {
                Logger.getLogger(DAO.class.getName()).severe(e.getLocalizedMessage());
            } finally {
                if (!transaction.wasCommitted()) transaction.rollback();
                session.close();
            }
        }
        return b;
    }
}
