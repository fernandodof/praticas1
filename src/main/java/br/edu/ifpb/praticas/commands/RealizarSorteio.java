/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.praticas.commands;

import br.edu.ifpb.praticas.beans.Concurso;
import br.edu.ifpb.praticas.dao.GenericoDAO;
import br.edu.ifpb.praticas.dao.GenericoDAOJPA;
import br.edu.ifpb.praticas.gerador.GeradorDeNumeros;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class RealizarSorteio implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Concurso concurso = (Concurso) genericoDAO.getById(Concurso.class, request.getParameter("NumConcurso"));
            Set numerosSorteados = GeradorDeNumeros.getSeisNumerosEntreUmESessenta();
            concurso.getNumeros().add(numerosSorteados);
            concurso.setRealizado(true);
            genericoDAO.update(concurso);
            request.setAttribute("numerosSorteados", numerosSorteados);
            request.getRequestDispatcher("administrador/PaginaPrincipalAdministrador.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
