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
            Set numeros = new TreeSet();
            numeros.addAll(Arrays.asList(numerosString));
            Aposta aposta = new Aposta(numeros);
            Concurso concurso = (Concurso)request.getSession().getAttribute("proximoConcurso");
            aposta.setConcurso(concurso);
            pessoa.getAposta().add(aposta);
            genericoDAO.update(pessoa);
            request.setAttribute("apostraRealizada", true);
            request.getRequestDispatcher("administrador/PaginaPrincipalApostador.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            request.setAttribute("apostraRealizada", false);
            ex.getMessage();
        }
    }

}
