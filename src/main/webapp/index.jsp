<%-- 
    Document   : index
    Created on : 31-May-2014, 3:14:41 PM
    Author     : Fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loteria</title>
    </head>
    <body>

        <h1>Loteria</h1>

        <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
            <input type="text" name="email" placeholder="Email" required>
            <input type="text" name="senha" placeholder="Senha" required>
            <input type="hidden" value="Login" name="command">
            <input type="submit" value="Entrar">
        </form>
        <c:if test="${loginErro != null}">
            <label>Email ou Senha não conferem</label>
        </c:if>

        <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
            <input type="text" name="nome" placeholder="Nome" required value="${nomeCadastro}">
            <input type="email" name="email" placeholder="Email" required value="${email}">
            <input type="password" name="senha" placeholder="Senha" required>
            <input type="password" name="senha1" placeholder="Confirme a senha" required>
            <input type="hidden" value="CadastrarApostador" name="command">
            <input type="submit" value="Cadastrar-se">
        </form>
        <c:if test="${erroOcorrido != null}">
            <label>${erroOcorrido}</label>
        </c:if>
    </body>
</html>
