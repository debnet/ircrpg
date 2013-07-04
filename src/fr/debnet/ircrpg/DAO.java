/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg;

import fr.debnet.ircrpg.models.IEntity;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Marc
 */
public class DAO {

    private static final SessionFactory sessionFactory;
    
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
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static <T extends IEntity> T getObject(String sql, Object... args) {
        return DAO.getObject(sql, false, args);
    }

    public static <T extends IEntity> T getObject(String sql, boolean limit, Object... args) {
        IEntity object = null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(sql);
            if (args != null)
                for (int i = 0; i < args.length; i++)
                    query.setParameter(sql, args[i]);
            if (limit) query.setMaxResults(1);
            object = (IEntity)query.uniqueResult();
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).warning(e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return (T)object;
    }

    public static <T extends IEntity> List<T> getObjectList(String sql, Object... args) {
        return DAO.getObjectList(sql, 0, args);
    }

    public static <T extends IEntity> List<T> getObjectList(String sql, int limit, Object... args) {
        List<IEntity> list = new ArrayList<IEntity>();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(sql);
            if (args != null)
                for (int i = 0; i < args.length; i++)
                    query.setParameter(sql, args[i]);
            if (limit > 0) query.setMaxResults(limit);
            list = query.list();
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).warning(e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return (List<T>)list;
    }

    public static <T extends IEntity> boolean setObject(T object) {
        boolean b = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            session.update(object);
            transaction.commit();
            b = true;
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).warning(e.getLocalizedMessage());
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            session.close();
        }
        return b;
    }

    public static <T extends IEntity> Long addObject(T object) {
        Long id = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            id = (Long) session.save(object);
            object.setId(id);
            transaction.commit();
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).warning(e.getLocalizedMessage());
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            session.close();
        }
        return id;
    }

    public static <T extends IEntity> boolean delObject(T object) {
        boolean b = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        try {
            session.delete(object);
            transaction.commit();
            b = true;
        } catch (HibernateException e) {
            Logger.getLogger(DAO.class.getName()).warning(e.getLocalizedMessage());
        } finally {
            if (!transaction.wasCommitted()) transaction.rollback();
            session.close();
        }
        return b;
    }
}
