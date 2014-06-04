/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class Login implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Map<String, Object> loginParms = new HashMap();
            loginParms.put("email", request.getParameter("email"));
            loginParms.put("senha", request.getParameter("senha"));

            Pessoa pessoa = (Pessoa) genericoDAO.getSingleResultOfNamedQuery("Pessoa.login", loginParms);
            request.getSession().setAttribute("nome", pessoa.getNome());
            request.getSession().setAttribute("id", pessoa.getId());

            if (pessoa.isAdm()) {
                this.forwardRequest(request, response, "administrador/PaginaPrincipalAdministrador.jsp");
            } else {
                this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
            }
        } catch (NoResultException ex) {
            this.forwardRequest(request, response, "index.jsp");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
