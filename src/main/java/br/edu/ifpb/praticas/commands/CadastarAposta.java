/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Aposta;
import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.beans.Pessoa;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import br.edu.ifpb.praticas.exceptions.ErroAconteceuException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Set;
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
        PrintWriter out = null;
        try {
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Pessoa pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, request.getSession().getAttribute("id"));
            out = response.getWriter();
            String[] numerosString = request.getParameter("aposta").split(",");
            if (numerosString.length < 6) {
                throw new ErroAconteceuException("Aposta não realizada, informe 6 números para apostar");
            }
            Set numeros = new TreeSet();
            numeros.addAll(Arrays.asList(numerosString));
            Concurso concurso = (Concurso) request.getSession().getAttribute("proximoConcurso");
            Aposta aposta = new Aposta(numeros);
            aposta.setConcurso(concurso);
            pessoa.getAposta().add(aposta);
            genericoDAO.update(pessoa);
            request.setAttribute("apostaRealizada", true);
        } catch (IOException ex) {
            request.setAttribute("apostaRealizada", false);
            this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
            ex.printStackTrace();
        } catch (ErroAconteceuException ex) {
            request.setAttribute("apostaRealizada", false);
            request.setAttribute("erroAposta", ex.getMessage());
            this.forwardRequest(request, response, "apostador/PaginaPrincipalApostador.jsp");
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
