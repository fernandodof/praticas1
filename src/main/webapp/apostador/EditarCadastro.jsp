<%-- 
    Document   : EditarCadastro
    Created on : 10-Jul-2014, 6:24:34 PM
    Author     : Fernando
--%>

<%@page import="br.edu.ifpb.praticas.beans.Pessoa"%>
<%@page import="br.edu.ifpb.praticas.dao.PessoaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cadastro</title>
        <link href="/BDNCpraticas/css/bootstrap.css" rel="stylesheet">
        <link href="/BDNCpraticas/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="/BDNCpraticas/css/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="/BDNCpraticas/img/favicon.fw.png" />
    </head>
    <body>
        <%
            PessoaDAO pessoaDAO = new PessoaDAO();
            Pessoa pessoa = pessoaDAO.getById(session.getAttribute("id").toString());
            pageContext.setAttribute("pessoa", pessoa);
        %>
        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="/BDNCpraticas/index.jsp"><img src="/BDNCpraticas/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
            <div class="row">
                <div class="span1"><a class="btn btn-primary btn-large" href="/BDNCpraticas/apostador/PaginaPrincipalApostador.jsp"><<</a></div>
                <div class="span10"><div class="btn btn-primary btn-large" style="margin-left: -20px"><i class="icon-user icon-white"></i> ${nome}</div><span style="color: #0088cc"></span></div>                
                <div class="span1">

                    <form method="post" action="/BDNCpraticas/FrontCrontroller">
                        <button type="submit" class="btn btn-danger">Sair</button>
                        <input type="hidden" value="Logout" name="command">
                    </form>
                </div>

            </div>
            <h3>Editar cadastro</h3>
            <div class="span6">
                <c:if test="${erroOcorrido != null}">
                    <div class="span3 alert alert-block" style="margin-left: 0px">${erroOcorrido}</div>
                </c:if>
                <c:if test="${editadoSucesso != null and editadoSucesso==true}">
                    <div class="span3 alert alert-success" style="margin-left: 0px">Editado com sucesso</div>
                </c:if>                   
                <form method="POST" action="/BDNCpraticas/FrontCrontroller">
                    <input class="span4" type="text" name="nome" placeholder="Nome" required value="${pessoa.nome}">
                    <input class="span4" type="email" name="email" placeholder="Email" required value="${pessoa.email}">
                    <input class="span4" type="password" name="senhaAntiga" placeholder="Senha Anterior" required>
                    <label>Editar Senha</label>
                    <input class="span4" type="password" name="senha" placeholder="Nova Senha">
                    <input class="span4" type="password" name="senha1" placeholder="Confirme a senha">
                    <input type="hidden" value="EditarCadastro" name="command">
                    <div class="row" style="margin-left: 250px;">
                        <button type="submit" class="btn btn-success btn-large">Salvar</button>
                    </div>
                </form>
            </div> 
        </div>
    </body>
</html>
