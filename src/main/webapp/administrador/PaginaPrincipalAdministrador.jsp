<%-- 
    Document   : PaginaPrincipalAdministrador
    Created on : 1-Jun-2014, 8:48:16 PM
    Author     : Fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal Apostador</title>
    </head>
    <body>
        <h1>Principal Apostador</h1>
        <h2>Novo concurso</h2>
        <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
            <label>Data e Hora do sorteio</label>
            <input type="date" name="data">
            <input type="time" name="hora">
            <!--<input type="datetime-local" name="dataHora">-->
            <input type="hidden" value="CadastrarConcurso" name="command">
            <input type="submit" value="Cadastrar">
        </form>
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
                <input type="submit" value="Realizar Sorteio">
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
    </body>
</html>
