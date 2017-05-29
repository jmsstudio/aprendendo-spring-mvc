<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
    </head>
    <body>
        <h2>Lista de produtos</h2>

        <div>
            <h5>${successMessage}</h5>
            <table>
                <thead>
                    <tr>
                        <td>Título</td>
                        <td>Descrição</td>
                        <td>Qtde Páginas</td>
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