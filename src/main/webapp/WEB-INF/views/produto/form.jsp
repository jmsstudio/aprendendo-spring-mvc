<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
        <c:url value="/" var="contextPath" />
        <link href="${contextPath}resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}resources/css/bootstrap-theme.min.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <h2 class="page-header">Cadastrar produto</h2>
            <form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="produto" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="titulo">Título</label>
                    <form:input type="text" id="titulo" path="titulo" cssClass="form-control"/>
                    <form:errors path="titulo" cssStyle="color: #f00;"/>
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <form:textarea id="descricao" path="descricao" cssClass="form-control"></form:textarea>
                    <form:errors path="descricao" cssStyle="color: #f00;"/>
                </div>
                <div class="form-group">
                    <label for="paginas">Qtde Páginas</label>
                    <form:input type="text" id="paginas" path="paginas" cssClass="form-control"/>
                    <form:errors path="paginas" cssStyle="color: #f00;"/>
                </div>
                <div class="form-group">
                    <label for="dataLancamento">Data de Lançamento</label>
                    <form:input type="text" id="dataLancamento" path="dataLancamento" cssClass="form-control"/>
                    <form:errors path="dataLancamento" cssStyle="color: #f00;"/>
                </div>
                <div class="form-group">
                    <label for="sumario">Sumário</label>
                    <input type="file" id="sumario" name="sumario" class="form-control"/>
                </div>
                <fieldset>
                    <legend>Preços</legend>

                    <c:forEach items="${tipos}" var="tipo" varStatus="status">
                        <div class="form-group">
                            <label for="${status.index}">${tipo}</label>
                            <form:input type="text" id="${status.index}" path="precos[${status.index}].valor" cssClass="form-control"/>
                            <form:hidden path="precos[${status.index}].tipo" value="${tipo}"/>
                        </div>
                    </c:forEach>
                </fieldset>

                <button class="btn btn-primary pull-right" type="submit">Cadastrar</button>
            </form:form>
        </div>
    </body>
</html>