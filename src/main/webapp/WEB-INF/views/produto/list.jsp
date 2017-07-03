<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url value="/" var="contextPath" />
        <link href="${contextPath}resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}resources/css/bootstrap-theme.min.css" rel="stylesheet">

        <title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
    </head>
    <body>
        <div class="container">
            <h2 class="page-header">Lista de produtos</h2>

            <h5 class="bg-success">${successMessage}</h5>
            <h5 class="bg-info">${message}</h5>
            <h4 class="bg-danger">${error}</h4>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Descrição</th>
                        <th>Qtde Páginas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${produtos}" var="produto">
                        <tr>
                            <td>
                                <a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">${produto.titulo}</a>
                            </td>
                            <td>${produto.descricao}</td>
                            <td>${produto.paginas}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>