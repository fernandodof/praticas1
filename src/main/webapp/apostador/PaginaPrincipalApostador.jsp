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
    <body onload="botaoAposta()">
        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="index.jsp"><img src="/ProjetoPraticas/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
                        <%
                            GenericoDAO genericoDAO = new GenericoDAOJPA();
                            Concurso proximoConcurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
                            pageContext.setAttribute("proximoConcurso", proximoConcurso);
                        %>

            <div class="row">
                <div class="span11"><div class="btn btn-primary btn-large"><i class="icon-user icon-white"></i> ${nome}</div><span style="color: #0088cc"></span></div>
                <div class="span1">
                    <form method="post" action="/ProjetoPraticas/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>    
            
            <c:if test="${proximoConcurso != null}">
                <label>Próximo Concurso: <span style="color: #499249">${proximoConcurso.id}</span></label>
                <label>Data do Sorteio: <span style="color: #499249">${proximoConcurso.dataHora}</span></label>
                </c:if>
                <c:choose>     
                    <c:when test="${proximoConcurso != null}">   
                    <div class="span4">
                        <form method="post" action="/ProjetoPraticas/FrontCrontroller">    
                            <input class="span4" type="text" id="aposta" name="aposta" readonly>
                            <input type="hidden" value="CadastarAposta" name="command"> 
                            <div class="row">
                                <button id="realizar" type="submit" class="btn btn-warning">Realizar Aposta</button>
                            </div>
                        </form>
                    </div>
            </div>        
                    <c:forEach begin="1" end="60" var="count" >
                        <input type="button" id="${count}" class="btNumero btn btn-info" value="${count}" onclick="carregarNumeros(this)" style="width: 50px; ">
                        <c:if test="${count mod 6 == 0}">
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
            <a href="/ProjetoPraticas/apostador/ApostasEResultados.jsp">Apostas e resultados</a>


        </div>
        <script src="/ProjetoPraticas/js/carregarNumeros.js"></script>
    </body>
</html>
