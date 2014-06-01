/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.dao;

import br.edu.ifpb.praticas.beans.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Fernando
 */
public class UsuarioDAOJPA<T> extends GenericoDAOJPA<T> {

    public UsuarioDAOJPA() {
        super();
    }

    public T login(String email, String senha) throws NoResultException{
        return (T) super.getEntityManager().createNamedQuery("Pessoa.login").
                setParameter("email", email).
                setParameter("senha", senha).getSingleResult();
    }

}
