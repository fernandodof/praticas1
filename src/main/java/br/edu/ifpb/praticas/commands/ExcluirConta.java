/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.dao.PessoaDAO;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class ExcluirConta implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            System.out.println(request.getSession().getAttribute("id").toString()+"  "+ (String) request.getParameter("senha"));
            pessoaDAO.excluirConta(request.getSession().getAttribute("id").toString(), (String) request.getParameter("senha"));
            request.getSession().invalidate();
            response.sendRedirect("/BDNCpraticas/index.jsp");
        } catch (ErroAconteceuException ex) {
            request.setAttribute("erroExclusao", ex.getMessage());
            this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
        } catch (SQLException ex) {
            ex.printStackTrace();
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
