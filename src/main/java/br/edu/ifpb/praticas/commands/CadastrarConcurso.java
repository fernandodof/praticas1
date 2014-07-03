/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.dao.ConcursoDAO;
import br.edu.ifpb.praticas.dao.PessoaDAO;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class CadastrarConcurso implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Timestamp timestamp = Timestamp.valueOf(request.getParameter("data") + " " + request.getParameter("hora") + ":00");
            Concurso concurso = new Concurso(timestamp);
            ConcursoDAO concursoDAO = new ConcursoDAO();
            try {
                if (concursoDAO.insert(concurso)) {
                    request.setAttribute("cadastroSucesso", true);
                } else {
                    throw new ErroAconteceuException("Ocorreu um erro no cadastro do concurso");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ErroAconteceuException("Ocorreu um erro no cadastro do concurso");
            }
            this.forwardRequest(request, response, "administrador/PaginaPrincipalAdministrador.jsp");
        } catch (ErroAconteceuException ex) {
            request.setAttribute("erroOcorrido", ex.getMessage());
            this.forwardRequest(request, response, "administrador/PaginaPrincipalAdministrador.jsp");
        } catch (UnsupportedEncodingException ex) {
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
