/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Aposta;
import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.ApostaDAO;
import br.edu.ifpb.praticas.dao.ConcursoDAO;
import br.edu.ifpb.praticas.dao.PessoaDAO;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class CadastarAposta implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ApostaDAO apostaDAO = new ApostaDAO();
            ConcursoDAO concursoDAO = new ConcursoDAO();
            PessoaDAO pessoaDAO = new PessoaDAO();
            Pessoa pessoa = pessoaDAO.getById((String) request.getSession().getAttribute("id"));
            String[] numerosString = request.getParameter("aposta").split(",");
            if (numerosString.length < 6) {
                throw new ErroAconteceuException("Aposta não realizada, informe 6 números");
            }
            SortedSet numeros = new TreeSet();
            numeros.addAll(Arrays.asList(numerosString));

            Concurso concurso = concursoDAO.getProximoConcurso();
            Aposta aposta = new Aposta(numeros);
            apostaDAO.insert(aposta, pessoa.getId(),concurso.getId());

//            apostaDAO.ligarConcurso(aposta.getId(), concurso.getId());
//            pessoaDAO.ligarAposta(pessoa.getId(), aposta.getId());
//            genericoDAO.update(aposta);
//            genericoDAO.update(pessoa);
            request.setAttribute("apostaRealizada", true);
            this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
        } catch (ErroAconteceuException ex) {
            request.setAttribute("apostaRealizada", false);
            request.setAttribute("erroAposta", ex.getMessage());
            this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
            ex.printStackTrace();
        } catch (SQLException ex) {
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
