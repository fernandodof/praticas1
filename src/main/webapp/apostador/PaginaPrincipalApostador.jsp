<%-- 
    Document   : principalApostador
    Created on : 31-May-2014, 3:50:15 PM
    Author     : Fernando
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAOJPA"%>
<%@page import="br.edu.ifpb.praticas.beans.Concurso"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='js/carregarNumeros.js'></script>
        <title>Pagina principal</title>
    </head>
    <body>
        <div class="topo"><a href="/ProjetoPraticas/index.jsp">Loteria</a></div>
        <%
            GenericoDAO genericoDAO = new GenericoDAOJPA();
            Concurso proximoConcurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
            pageContext.setAttribute("proximoConcurso", proximoConcurso);
        %>

        <h1>Pagina principal</h1>
        <c:if test="${proximoConcurso != null}">
            <label>Próximo Concurso</label>
            <label>Numero do Concurso: ${proximoConcurso.id}</label>
            <label>Data do Sorteio: ${proximoConcurso.dataHora}</label>
        </c:if>
        <c:choose>     
            <c:when test="${proximoConcurso != null}">   
                <form method="post" action="/ProjetoPraticas/FrontCrontroller">    
                    <input type="text" id="aposta" name="aposta" readonly>
                    <input type="hidden" value="CadastarAposta" name="command"> 
                    <input type="submit" value="Realizar Aposta"><br>
                </form>

                <c:forEach begin="1" end="60" var="count" >
                    <input type="button" id="${count}" class="btNumero" value="${count}" onclick="carregarNumeros(this)" style="width: 50px">
                    <c:if test="${count mod 5 == 0}">
                        <br>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <label>Não há concursos cadastrados para as próximas datas</label>
            </c:otherwise>            
        </c:choose>
        <c:choose>
            <c:when test="${apostaRealizada != null and apostaRealizada == true}">
                <label>Apostas realizada com sucesso, Boa Sorte</label>
            </c:when>
            <c:when test="${apostaRealizada != null and apostaRealizada == false}">
                <label>Ocorreu um erro no processamento da sua aposta</label>
                <label>${erroAposta}</label>
            </c:when>
        </c:choose>
                <a href="ApostasEResultados.jsp">Apostas e resultados</a>

        <form method="post" action="/ProjetoPraticas/FrontCrontroller">
            <input type="submit" value="Logout">
            <input type="hidden" value="Logout" name="command">
        </form>
    </body>
</html>
