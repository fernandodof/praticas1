<%@page import="br.edu.ifpb.praticas.dao.ConcursoDAO"%>
<%@page import="br.edu.ifpb.praticas.beans.Concurso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Página do Apostador</title>
        <meta charset="utf-8">
        <link href="/BDNCpraticas/css/bootstrap.css" rel="stylesheet">
        <link href="/BDNCpraticas/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="/BDNCpraticas/css/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="/BDNCpraticas/img/favicon.fw.png" />
        <script src="/BDNCpraticas/js/carregarNumeros.js"></script>
        <script src="/BDNCpraticas/js/mostrarDiv.js"></script>
    </head>
    <body onload="botaoAposta()">
        <div class="container">
            <div class="row" style="text-align: center"><h1><a href="/BDNCpraticas/index.jsp"><img src="/BDNCpraticas/img/logo.fw.png" alt="Mais Sorte" /></a></h1></div>
                        <%
                            ConcursoDAO concursoDAO = new ConcursoDAO();
                            Concurso proximoConcurso = concursoDAO.getProximoConcurso();
                            pageContext.setAttribute("proximoConcurso", proximoConcurso);
                        %>

            <div class="row">
                <div class="span11"><div class="btn btn-primary btn-large"><i class="icon-user icon-white"></i>${nome}</div><span style="color: #0088cc"></span></div>
                <div class="span1">
                    <form method="post" action="/BDNCpraticas/FrontCrontroller">
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

                            <form method="post" action="/BDNCpraticas/FrontCrontroller">    
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
                    <a class="btn btn-block btn-primary span4" style="margin-left: 0px; margin-bottom: 25px; margin-top: 25px;" href="/BDNCpraticas/apostador/ApostasEResultados.jsp">Apostas e resultados</a>
                </div>

            </div>

            <div class="row">
                <div class="span6">
                    <a class="btn btn-info" href="/BDNCpraticas/apostador/EditarCadastro.jsp" id="editar">Editar cadastro</a>
                </div>
            </div>

            <div id="divExcluir">
                <div class="row">
                    <div class="span6">
                        <input type="button" class="btn btn-warning span4" id="excluirConta" onclick="mostrarDiv(this)" value="Excluir minha conta">
                    </div>
                </div>

                <div class="row" id="oculto" <c:if test="${erroExclusao != null}">style="display: block"</c:if>>

                        <div class="span6">
                            <form method="post" action="/BDNCpraticas/FrontCrontroller">
                                <input type="text" placeholder="Senha" id="senha" name="senha">
                                <input type="submit" class="btn btn-danger" id="excluir" value="Excluir">
                                <input type="hidden" value="ExcluirConta" name="command">
                            </form>
                            <div class="row">
                            <c:if test="${erroExclusao != null}">
                                <div class="alert alert-error span3">${erroExclusao}</div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
