<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
    </head>
    <body>
        <form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="produto" enctype="multipart/form-data">
            <div>
                <label for="titulo">Título</label>
                <form:input type="text" id="titulo" path="titulo"/>
                <form:errors path="titulo" cssStyle="color: #f00;"/>
            </div>
            <div>
                <label for="descricao">Descrição</label>
                <form:textarea id="descricao" path="descricao" rows="5" cols="100"></form:textarea>
                <form:errors path="descricao" cssStyle="color: #f00;"/>
            </div>
            <div>
                <label for="paginas">Qtde Páginas</label>
                <form:input type="text" id="paginas" path="paginas"/>
                <form:errors path="paginas" cssStyle="color: #f00;"/>
            </div>
            <div>
                <label for="dataLancamento">Data de Lançamento</label>
                <form:input type="text" id="dataLancamento" path="dataLancamento"/>
                <form:errors path="dataLancamento" cssStyle="color: #f00;"/>
            </div>
            <div>
                <label for="sumario">Sumário</label>
                <input type="file" id="sumario" name="sumario"/>
            </div>
            <fieldset>
                <legend>Preços</legend>

                <c:forEach items="${tipos}" var="tipo" varStatus="status">
                    <div>
                        <label for="${status.index}">${tipo}</label>
                        <form:input type="text" id="${status.index}" path="precos[${status.index}].valor"/>
                        <form:hidden path="precos[${status.index}].tipo" value="${tipo}"/>
                    </div>
                </c:forEach>
            </fieldset>

            <button type="submit">Cadastrar</button>
        </form:form>
    </body>
</html>