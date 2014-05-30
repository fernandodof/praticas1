/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bt.edu.ifpb.praticas.dao;

/**
 *
 * @author Fernando
 */
public interface GenericoDAO<T> {
    boolean salvar(T entity);
    boolean atualizar(T entity);
}
