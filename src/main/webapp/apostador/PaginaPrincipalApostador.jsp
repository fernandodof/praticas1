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
        <link href="/praticas1/css/bootstrap.css" rel="stylesheet">
        <link href="/praticas1/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="/praticas1/css/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="/praticas1/img/favicon.fw.png" />
    </head>
    <body onload="botaoAposta()">
        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="index.jsp"><img src="/praticas1/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
                        <%
                            GenericoDAO genericoDAO = new GenericoDAOJPA();
                            Concurso proximoConcurso = (Concurso) genericoDAO.getSingleResultOfNamedQuery("Concurso.proximos");
                            pageContext.setAttribute("proximoConcurso", proximoConcurso);
                        %>

            <div class="row">
                <div class="span11"><div class="btn btn-primary btn-large"><i class="icon-user icon-white"></i> ${nome}</div><span style="color: #0088cc"></span></div>
                <div class="span1">
                    <form method="post" action="/praticas1/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>    
            </div>
            <div class="row">
                <div class="span6">
                    <c:if test="${proximoConcurso != null}">
                        <label>Próximo Concurso: <span style="color: #499249">${proximoConcurso.id}</span></label>
                        <label>Data do Sorteio: <span style="color: #499249">${proximoConcurso.dataHora}</span></label>
                        </c:if>
                        <c:choose>     
                            <c:when test="${proximoConcurso != null}">   

                                <form method="post" action="/praticas1/FrontCrontroller">    
                                    <input type="text" id="aposta" name="aposta" readonly>
                                    <input type="hidden" value="CadastarAposta" name="command"> 
                                    <button id="realizar" type="submit" class="btn btn-warning" style="margin-top: -15px;" disabled>Realizar Aposta</button>
                                   
                                </form>


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
                            <div class="row">
                                <div class="alert alert-success span3" style="margin-top: 10px;">Aposta realizada com sucesso!</div>                                    
                            </div>
                        </c:when>
                        <c:when test="${apostaRealizada != null and apostaRealizada == false}">
                            <div class="alert alert-error span3" style="margin-top: 10px;">Erro no processamento da sua aposta</div>
                            <div class="alert alert-error span3" style="margin-top: 10px;">${erroAposta}</div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="span6">
                    <a class="btn btn-block btn-primary span4" style="margin-left: 0px; margin-bottom: 25px; margin-top: 25px;" href="/praticas1/apostador/ApostasEResultados.jsp">Apostas e resultados</a>
                </div>
                
            </div>

        </div>
        <script src="/praticas1/js/carregarNumeros.js"></script>
    </body>
</html>
