<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp" />

<h2>학생 추가</h2>
	<form action="${pageContext.request.contextPath}/student/add.do" method="post" enctype="multipart/form-data">
	<%-- <form action=" <c:url value="/student/stuAdd.jsp" /> "> --%>
		학번 : <input type="text" name="stuNo" value="" /> <br>
		이름 : <input type="text" name="stuName" value="" /> <br>
		점수 : <input type="number" name="stuScore" value="0" /> <br>
		첨부파일: <input type="file" name="bbsFile" /><br>
		<input type="submit" />
	</form>
</body>
</html>
	
		
