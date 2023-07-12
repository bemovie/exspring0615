<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp" />

<h2>학생 정보 변경</h2>
	<form action="${pageContext.request.contextPath}/student/edit.do" method="post">
	<%-- <form action=" <c:url value="/student/stuAdd.jsp" /> "> --%>
		학번 : <c:out value="${svo.stuNo}" />
			<%-- <input type="hidden" name="stuNo" value="<c:out value="${svo.stuNo}" />" /> --%> <br>
		학번 : <input type="text" name="stuNo" value="<c:out value="${svo.stuNo}" />" readonly="readonly" /> <br>
		이름 : <input type="text" name="stuName" value="<c:out value="${svo.stuName}" />" /> <br>
		점수 : <input type="number" name="stuScore" value="${svo.stuScore}" /> <br>
		<input type="submit" />
	<a href="${pageContext.request.contextPath}/student/del.do?stuNo=<c:out value="${svo.stuNo}"/>"><button type="button">삭제</button></a>
	<c:url value="/student/del.do" var="delUrl">
		<c:param name="stuNo">${svo.stuNo}</c:param>
	</c:url>
	<a href="${delUrl}">삭제</a>
	</form>
</body>
</html>
	
		
