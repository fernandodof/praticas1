/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.dao;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Fernando
 */
public interface GenericoDAO<T> {
    public boolean save (T entity);
    public T find (Class<T> classType, T entity);
    public boolean update (T entity);
    public boolean delete (T entity);
    public T getById (Class<T> classTClass, Object id);
    public T simpleQuery(String query, Map<Integer, Serializable> map);
    public EntityManager getEntityManager(); 
    public T executeNativeQuery(String query);
    public T getSingleResultOfNamedQuery(String namedQuery, Map<String, Object> map);
    public T getSingleResultOfNamedQuery(String namedQuery);
}
