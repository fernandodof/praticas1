<%-- 
    Document   : principalApostador
    Created on : 31-May-2014, 3:50:15 PM
    Author     : Fernando
--%>

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
        <h1>Pagina principal</h1>
        <c:if test="${proximoConcurso != null and proximoConcurso.realizado == false}">
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

    </body>
</html>
