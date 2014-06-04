<%-- 
    Document   : ApostasEResultados
    Created on : 2-Jun-2014, 10:42:18 PM
    Author     : Fernando
--%>

<%@page import="br.edu.ifpb.praticas.beans.Concurso"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.praticas.beans.Aposta"%>
<%@page import="br.edu.ifpb.praticas.beans.Pessoa"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAOJPA"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/cssApostasEResultados.css" type="text/css" rel="stylesheet"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="topo"><a href="/ProjetoPraticas/index.jsp">Loteria</a></div>
        <%
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Concurso proximoConcurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
            pageContext.setAttribute("proximoConcurso", proximoConcurso);

            Pessoa pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, session.getAttribute("id"));
            List<Aposta> apostas = pessoa.getAposta();
            pageContext.setAttribute("apostas", apostas);

        %>
        <h1>Apostas e Resultados</h1>
        <table>
            <thead>
                <tr>
                    <th class="ids">Id Aposta</th>
                    <th class="numeros">Números Apostados</th>
                    <th class="ids">Id Concurso</th>
                    <th class="numeros">Números Sorteados</th>
                    <th>Resultado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${apostas}" var="aposta">
                    <tr>
                        <td class="ids">${aposta.id}</td>
                        <td class="numeros">${aposta.numeros}</td>
                        <td class="ids">${aposta.concurso.id}</td>
                        <td class="numeros">${aposta.concurso.numeros}</td>
                        <c:choose>
                            <c:when test="${aposta.numeros.toString() eq aposta.concurso.numeros.toString()}">
                                <td>Acertou</td>
                            </c:when>
                            <c:otherwise>
                                <td>Errou</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <form method="post" action="/ProjetoPraticas/FrontCrontroller">
            <input type="submit" value="Logout">
            <input type="hidden" value="Logout" name="command">
        </form>
    </body>
</html>
