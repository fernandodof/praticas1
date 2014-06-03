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

            Pessoa pessoa = (Pessoa) genericoDAO.getSingleResultOfNamedQuery("Pessoa.login",loginParms);
            request.getSession().setAttribute("nome", pessoa.getNome());
            request.getSession().setAttribute("id", pessoa.getId());
            
            if(genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos") != null){
                Concurso concurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
                request.getSession().setAttribute("proximoConcurso", concurso);
                String d = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(concurso.getDataHora().toString()));
                request.getSession().setAttribute("dataSorteio", d);
            }else{
                request.getSession().setAttribute("proximoConcurso", null);
            }
            if (pessoa.isAdm()) {
                request.getRequestDispatcher("administrador/PaginaPrincipalAdministrador.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("apostador/PaginaPrincipalApostador.jsp").forward(request, response);
            }
        } catch (NoResultException ex) {
            try {
                request.setAttribute("loginErro", true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                ex.printStackTrace();
            }
        } catch (ServletException | IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
