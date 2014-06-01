/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import br.edu.ifpb.praticas.dao.UsuarioDAOJPA;
import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginApostador implements Command {

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            UsuarioDAOJPA usuarioDAOJPA = new UsuarioDAOJPA();
            PrintWriter out = response.getWriter();
            out.println(request.getParameter("email"));
            out.println(request.getParameter("senha"));
            Pessoa pessoa = (Pessoa) usuarioDAOJPA.login(request.getParameter("email"), request.getParameter("senha"));
            if (pessoa != null) {
                request.getRequestDispatcher("apostador/PaginaPrincipalApostador.jsp").forward(request, response);
            }
        } catch (NoResultException ex) {
            try {
                request.setAttribute("loginError", true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                ex.printStackTrace();
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
