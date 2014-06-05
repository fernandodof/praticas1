<%@page import="br.edu.ifpb.praticas.beans.Concurso"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAOJPA"%>
<%@page import="br.edu.ifpb.praticas.dao.GenericoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>        
        <title>Página do Administrador - Acesso Restrito</title>
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
                <div class="span11"><h2>Administrador</h2></div>
                <div class="span1">
                    <form method="post" action="/ProjetoPraticas/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>    
            </div>
            <h3>Novo concurso</h3>
            <div class="row">
                <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                    <div class="span12">
                        <label>Data e Hora do sorteio</label>
                        <div class="row">
                            <div class="span5">
                                <input type="date" name="data">
                                <input type="time" name="hora">
                            <!--<input type="datetime-local" name="dataHora">-->
                            </div>
                        
                            <input type="hidden" value="CadastrarConcurso" name="command">
                            <div class="span2">
                                <button style="text-align: left;" type="submit" class="btn btn-success">Cadastrar</button>
                            </div>
                        </div>  
                    </div>
                </form>
            </div>
            <c:choose>
                <c:when test="${cadastroSucesso != null and cadastroSucesso == true}">
                    <label>Novo concurso cadastrado com sucesso</label>
                </c:when>
                <c:when test="${cadastroSucesso != null and cadastroSucesso == false}">
                    <label>Novo concurso NÃO cadastro</label>
                </c:when>    
            </c:choose>
            <c:if test="${erroOcorrido != null}">
                <label>${erroOcorrido}</label>
            </c:if>

            <c:if test="${proximoConcurso != null and proximoConcurso.realizado == false}">
                <br><br><label>Próximo Concurso</label><br>
                <label>Numero do Concurso: ${proximoConcurso.id}</label><br>
                <label>Data do Sorteio: ${proximoConcurso.dataHora}</label>
                <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                    <input type="hidden" value="RealizarSorteio" name="command">
                    <input type="hidden" name="NumConcurso" value="${proximoConcurso.id}">
                    <button type="submit" class="btn btn-large btn-inverse">Realizar Sorteio</button>                    
                </form>
            </c:if>

            <c:if test="${numerosSorteados != null}">
                <label>Sorteio Realizado Com sucesso</label><br>
                <label>Numeros Sorteados</label><br>
                <label>
                    <c:forEach items="${numerosSorteados}" var="numero">
                        ${numero}
                    </c:forEach>
                </label>
            </c:if>
            
        </div>
    </body>

</html>
