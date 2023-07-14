<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h2>새글 쓰기</h2>
	<form action="${pageContext.request.contextPath}/bbs/add.do" method="post" enctype="multipart/form-data">
	<%-- <form action=" <c:url value="/student/stuAdd.jsp" /> "> --%>
		제목 : <input type="text" name="bbsTitle" value="" /> <br>
		내용 : <textarea name="bbsContent" rows="5" cols="30"></textarea><br>
		첨부파일1: <input type="file" name="bbsFile" /><br>
		첨부파일2: <input type="file" name="bbsFile" /><br>
		<input type="submit" />
	</form>
	
<!-- </body>
</html> -->
	
		
