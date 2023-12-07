<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h2>학생 추가</h2>
	<%-- <form action="${pageContext.request.contextPath}/student/add.do" method="post" enctype="multipart/form-data">
	<form action=" <c:url value="/student/stuAdd.jsp" /> ">
		학번 : <input type="text" name="stuNo" value="${studentVo.stuNo}" /> <br>
		이름 : <input type="text" name="stuName" value="${studentVo.stuName}" /> <br>
		점수 : <input type="number" name="stuScore" value="${studentVo.stuScore}" /> <br>
		<!-- 점수 : <input type="number" name="stuScore" value="0" /> <br> -->
		첨부파일: <input type="file" name="bbsFile" /><br>
		<input type="submit" />
	</form> --%>
	
	<form:form modelAttribute="studentVo" action="${pageContext.request.contextPath}/student/add.do" method="post">
		학번 : <form:input path="stuNo" /> <form:errors path="stuNo"/><br>
		이름 : <form:input path="stuName" /> <form:errors path="stuName"/><br>
		점수 <form:input path="stuScore" /> <form:errors path="stuScore"/><br>
		<input type="submit" />
	</form:form>
	
<!-- </body>
</html> -->
	
		
