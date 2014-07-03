/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.PessoaDAO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
            PessoaDAO pessoaDAO = new PessoaDAO();

            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            Pessoa pessoa;
            try {
                pessoa = pessoaDAO.logar(email, senha);
                request.getSession().setAttribute("nome", pessoa.getNome());
                request.getSession().setAttribute("id", pessoa.getId());
                request.getSession().setAttribute("isAdm", pessoa.isAdm());
                request.getSession().setAttribute("logado", true);
                if (pessoa.isAdm()) {
                    this.forwardRequest(request, response, "administrador/PaginaPrincipalAdministrador.jsp");
                } else {
                    this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
                }
            } catch (SQLException ex) {
                 request.setAttribute("loginErro", "Usuário ou senha não conferem");
            this.forwardRequest(request, response, "index.jsp");
            }  
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
