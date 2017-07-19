<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<%@include file="_header.jsp"%>


		<section id="index-section" class="container middle">

			<h1>Ocorreu um erro interno. Caso o erro persista, contate o administrador do sistema.</h1>

			<p><b>Message:</b> ${exception.message}</p>
            <c:forEach items="${exception.strackTrace}" var="stack">
                <p>${strack}</p>
            </c:forEach>

		</section>
		<%@include file="_footer.jsp"%>
        <%--<script src="${contextPath}resources/js/bootstrap.min.js"></script>--%>
	</body>
</html>