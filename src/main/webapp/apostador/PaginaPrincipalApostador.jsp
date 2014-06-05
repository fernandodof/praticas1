<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAOJPA"%>
<%@page import="br.edu.ifpb.praticas.beans.Concurso"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Página do Apostador</title>
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
            %>

            <div class="row">
                <div class="span11"><h2>Apostador</h2></div>
                <div class="span1">
                    <form method="post" action="/ProjetoPraticas/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>    
            </div>
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

            
        </div>
            <script src="/ProjetoPraticas/js/carregarNumeros.js"></script>
    </body>
</html>
