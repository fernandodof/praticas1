<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>        
        <title>Loteria - sua loteria!</title>
        <meta charset="utf-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="/ProjetoPraticas/img/favicon.fw.png" />
    </head>
    <body>

        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="index.jsp"><img src="/ProjetoPraticas/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
                        <%
                            String nome = (String) session.getAttribute("nome");
                        %>
                        <c:choose>
                            <c:when test="${nome == null}">

                    <div class="row" id="logar">
                        <div class="span6">
                            <h3>Entre na sua Conta</h3>
                            <c:if test="${loginErro != null}">
                                <div class="span3 alert alert-error" style="margin-left: 0pX;">Email ou Senha não conferem</div>                               
                            </c:if>
                                <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                                    <input class="span4" type="email" name="email" placeholder="Email" required>
                                    <input class="span4" type="password" name="senha" placeholder="Senha" required>
                                    <input type="hidden" value="Login" name="command"> 
                                    <div class="row" style="margin-left: 280px;">
                                        <button style="text-align: right" type="submit" class="btn btn-success btn-large">Entrar</button>	
                                    </div>
                                </form>
                        </div>


                        <div class="span6">
                            <h3>Faça seu cadastro!</h3>
                            <c:if test="${erroOcorrido != null}">
                                <div class="span3 alert alert-block" style="margin-left: 0px">${erroOcorrido}</div>
                            </c:if>
                            <c:if test="${cadastroSucesso != null}">
                                <div class="span3 alert alert-success" style="margin-left: 0px">Cadastro sucesso</div>
                            </c:if>                   
                            <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                                <input class="span4" type="text" name="nome" placeholder="Nome" required value="${nomeCadastro}">
                                <input class="span4" type="email" name="email" placeholder="Email" required value="${email}">
                                <input class="span4" type="password" name="senha" placeholder="Senha" required>
                                <input class="span4" type="password" name="senha1" placeholder="Confirme a senha" required>
                                <input type="hidden" value="CadastrarApostador" name="command">
                                <div class="row" style="margin-left: 250px;">
                                    <button type="submit" class="btn btn-success btn-large">Cadastrar</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${isAdm != null and isAdm == true}">
                            <a href="administrador/PaginaPrincipalAdministrador.jsp">Minha Página</a>
                        </c:when>
                        <c:when test="${isAdm != null and isAdm == false}">
                            <a href="apostador/PaginaPrincipalApostador.jsp">Minha Página</a>
                        </c:when>
                    </c:choose>
                    <h2>Bem vindo: ${nome}</h2>
                    <form method="post" action="/ProjetoPraticas/FrontCrontroller">
                        <input type="submit" value="Logout">
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </c:otherwise>
            </c:choose>
        </div>    


    </body>
</html>
