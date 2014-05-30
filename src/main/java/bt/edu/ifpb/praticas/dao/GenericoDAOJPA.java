/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.edu.ifpb.praticas.dao;

import com.sun.jndi.cosnaming.CNCtx;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Fernando
 */
public class GenericoDAOJPA<T> implements GenericoDAO<T> {

    private EntityManager em;
    private EntityManagerFactory factory;

    public GenericoDAOJPA() {
        this.em = getEntityManager();
    }

    private EntityManager getEntityManager() {
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

    public boolean salvar(T entity) {
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

    public boolean atualizar(T entity) {
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

}
