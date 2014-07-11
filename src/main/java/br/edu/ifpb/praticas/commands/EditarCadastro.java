/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.PessoaDAO;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class EditarCadastro implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            if (!pessoaDAO.verificaSenha(request.getSession().getAttribute("id").toString(), request.getParameter("senhaAntiga"))) {
                throw new ErroAconteceuException("Senha Anterior incorreta");
            }
            if (!request.getParameter("senha").equals(request.getParameter("senha1"))) {
                throw new ErroAconteceuException("Novas Senhas não conferem");
            }
            Pessoa pessoa = pessoaDAO.getById(request.getSession().getAttribute("id").toString());
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setEmail(request.getParameter("email"));
            if(!request.getParameter("senha").equals("")){
                pessoa.setSenha(request.getParameter("senha"));
            }else{
                pessoa.setSenha(request.getParameter("senhaAntiga"));
            }
            pessoaDAO.atualizarPessoa(pessoa);
            request.setAttribute("editadoSucesso", true);
            this.forwardRequest(request, response, "apostador/EditarCadastro.jsp");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ErroAconteceuException ex) {
            request.setAttribute("erroOcorrido", ex.getMessage());
            request.setAttribute("editadoSucesso", false);
            request.setAttribute("nomeCadastro", request.getAttribute("nome"));
            request.setAttribute("email", request.getAttribute("email"));
            this.forwardRequest(request, response, "apostador/EditarCadastro.jsp");
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
