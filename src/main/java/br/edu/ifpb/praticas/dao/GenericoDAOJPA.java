/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Fernando
 * @param <T>
 */
public class GenericoDAOJPA<T> implements GenericoDAO<T> {

    private EntityManager em;
    private EntityManagerFactory factory;

    public GenericoDAOJPA() {
        this.em = getEntityManager();
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            factory = Persistence.createEntityManagerFactory("praticas");
            em = factory.createEntityManager();
        }
        return em;
    }

//    public UserTransaction getTransacao() throws NamingException {
//        Context context = (Context) new InitialContext().lookup("java:comp");
//        UserTransaction userTransaction = (UserTransaction) context.lookup("UserTransaction");
//        return userTransaction;
//    }
    @Override
    public boolean save(T entity) {
        try {
            this.em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            this.em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(T entity) {
        try {
            this.em.getTransaction().begin();
            em.merge(entity);
            em.flush();
            this.em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            this.em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T find(Class<T> classType, T entity) {
        return this.em.find(classType, entity);
    }

    @Override
    public boolean delete(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(entity);
            this.em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            this.em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T getById(Class<T> classTClass, Object id) {
        try {
            return getEntityManager().find(classTClass, id);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public T simpleQuery(String query, Map<Integer, Serializable> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T executeNativeQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T getSingleResultOfNamedQuery(String namedQuery, Map<String, Object> map) {
        Query query = this.em.createNamedQuery(namedQuery);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String param = entry.getKey();
            Object value = entry.getValue();
            query.setParameter(param, value);
        }
        return (T) query.getSingleResult();
    }

    public T getSingleResultOfNamedQuery(String namedQuery) {
        try {
            Query query = this.em.createNamedQuery(namedQuery);
            return (T) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
