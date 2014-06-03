/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class CadastrarApostador implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            if (!request.getParameter("senha").equals(request.getParameter("senha1"))) {
                throw new ErroAconteceuException("Senhas não conferem");
            }
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Pessoa pessoa = new Pessoa(request.getParameter("nome"), request.getParameter("email"), request.getParameter("senha"), false);
            if(genericoDAO.save(pessoa)){
                request.setAttribute("cadastroSucesso", true);
            }else{
                throw new ErroAconteceuException("Ocorreu um erro com seu cadastro");
            }
            this.forwardRequest(request, response,"index.jsp");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ErroAconteceuException ex) {
            request.setAttribute("erroOcorrido", ex.getMessage());
            request.setAttribute("nomeCadastro", request.getAttribute("nome"));
            request.setAttribute("email", request.getAttribute("email"));
            this.forwardRequest(request, response,"index.jsp");
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
