<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h2>로그인</h2>
	<form action="${pageContext.request.contextPath}/student/login.do" method="post">
	<%-- <form action=" <c:url value="/student/stuAdd.jsp" /> "> --%>
		학번 : <input type="text" name="stuNo" value="" /> <br>
		이름 : <input type="text" name="stuName" value="" /> <br>
		<input type="submit" value="로그인" />
	</form>
	
<!-- </body>
</html> -->
	
		
