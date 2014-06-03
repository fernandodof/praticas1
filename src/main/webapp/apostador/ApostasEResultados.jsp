<%-- 
    Document   : ApostasEResultados
    Created on : 2-Jun-2014, 10:42:18 PM
    Author     : Fernando
--%>

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
        <title>JSP Page</title>
    </head>
    <body>
        <%
        GenericoDAO genericoDAO = new GenericoDAOJPA();
        Pessoa pessoa = (Pessoa) genericoDAO.getById(Pessoa.class, session.getAttribute("id"));
        List<Aposta> apostas = pessoa.getAposta();
        pageContext.setAttribute("apostas", apostas);
        %>
        <h1>Apostas e Resultados</h1>
    </body>
</html>
