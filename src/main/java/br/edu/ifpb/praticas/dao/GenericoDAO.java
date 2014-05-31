/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.dao;

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
}
