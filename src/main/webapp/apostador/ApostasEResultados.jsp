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
        <title>Loteria - sua loteria!</title>
        <meta charset="utf-8">
        <link href="/ProjetoPraticas/css/bootstrap.css" rel="stylesheet">
        <link href="/ProjetoPraticas/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="/ProjetoPraticas/css/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="/ProjetoPraticas/img/favicon.fw.png" />
    </head>
    <body>

        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="index.jsp"><img src="/ProjetoPraticas/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
                        <%
                            GenericoDAO genericoDAO = new GenericoDAOJPA();
                            Concurso proximoConcurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
                            pageContext.setAttribute("proximoConcurso", proximoConcurso);

                            Pessoa pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, session.getAttribute("id"));
                            List<Aposta> apostas = pessoa.getAposta();
                            pageContext.setAttribute("apostas", apostas);

                        %>
            <div class="row">
                <div class="span1"><div class="btn btn-primary btn-large" style="margin-left: -25px;" title="voltar"><i class="icon-arrow-left icon-white"></i><a href="/ProjetoPraticas/apostador/PaginaPrincipalApostador.jsp"></a></div></div><div>
                    <div class="span10"><div class="btn btn-primary btn-large" style="margin-left: -50px;"><i class="icon-user icon-white"></i> ${nome}</div><span style="color: #0088cc"></span></div>                
                <div class="span1">
                        
                    <form method="post" action="/ProjetoPraticas/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>
                
            </div>
            <h3>Apostas e Resultados</h3>
            <table class="table table-condensed">
                <thead>
                    <tr>
                        <th>Id Aposta</th>
                        <th>Números Apostados</th>
                        <th>Id Concurso</th>
                        <th>Números Sorteados</th>
                        <th>Resultado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${apostas}" var="aposta">
                        <tr class="info">
                            <td class="ids">${aposta.id}</td>
                            <td class="numeros">${aposta.numeros}</td>
                            <td class="ids">${aposta.concurso.id}</td>
                            <c:choose>
                                <c:when test="${aposta.concurso.numeros != null}">
                                    <td class="numeros">${aposta.concurso.numeros}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>Sorteio não realizado</td>
                                </c:otherwise>
                            </c:choose>
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
        </div>
    </body>
</html>
