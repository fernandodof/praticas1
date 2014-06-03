<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>        
        <title>Loteria - A sua sorte começa aqui!</title>
        <meta charset="utf-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/default.css" rel="stylesheet">
    </head>
    <body>
        <div class="topo"><h1>Loteria</h1></div>
        <div class="container">
            <div class="row" id="logar">
                <div class="span6">
                    <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                        <input type="email" name="email" placeholder="Email" required>
                        <input type="text" name="senha" placeholder="Senha" required>
                        <input type="hidden" value="Login" name="command">                       
                        <button type="submit" class="btn btn-success">Entrar</button>	
                    </form>
                    <c:if test="${loginErro != null}">
                        <label>Email ou Senha não conferem</label>
                    </c:if>
                </div>
                
            </div>
            
            <div class="row" id="cadastrar">
                <div class="span6">
                    <form method="POST" action="/ProjetoPraticas/FrontCrontroller">
                        <input type="text" name="nome" placeholder="Nome" required value="${nomeCadastro}">
                        <input type="email" name="email" placeholder="Email" required value="${email}">
                        <input type="password" name="senha" placeholder="Senha" required>
                        <input type="password" name="senha1" placeholder="Confirme a senha" required>
                        <input type="hidden" value="CadastrarApostador" name="command">                        
                        <button type="submit" class="btn btn-success">Entrar</button>
                    </form>
                    <c:if test="${erroOcorrido != null}">
                        <label>${erroOcorrido}</label>
                    </c:if>
                </div>
                
            </div>
            
            
        </div>    
         
        
    </body>
</html>
