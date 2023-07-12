<%-- <%@page import="com.exam.student.StudentVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.student.StuDaoBatis"%>
<%@page import="com.exam.student.StudentDao"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%! //선언부

private StudentDao studentDao = new StuDaoBatis();

%>

<% //java code 입력

List<StudentVo> list = studentDao.selectStudentList();
request.setAttribute("studentList", list);

%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset=\"UTF-8\">
<title>게시글 목록</title>	
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
/* a {text-decoration : none; color : black;} */
</style>	
</head>
<body>

<jsp:include page="/WEB-INF/views/menu.jsp" />

<%-- <% 
for (StudentVo vo : list) {
%> --%>

<%-- <!-- 로그인이 된 경우 -->
<c:if test="${loginUser!=null}">
${loginUser.stuName}
${loginUser.getStuName()}
${loginUser["stuName"]}
<button><a href="${pageContext.request.contextPath}/student/logout.do">로그아웃</a></button>
</c:if>

<!-- 로그인이 되지 않은 경우 -->
<c:if test="${loginUser==null}">
<c:redirect url="<c:url value="/student/login.do" />"></c:redirect>
<button><a href="${pageContext.request.contextPath}/student/login.do">로그인</a></button>
<button><a href="<%=request.getContextPath()%>/student/add.do">학생추가</a></button>
<button><a href="${pageContext.request.contextPath}/student/add.do">학생추가</a></button>
<button><a href="<c:url value="/student/add.do" />">학생추가</a></button>
</c:if> --%>
	
	
<!-- System.out.println(vo.getStuNo() + ":" + vo.getStuName() + ":" + vo.getStuScore()); -->

<table>
	<thead>
		<tr><th>번호</th>	<th>제목</th>	<th>작성자</th><th>등록일시</th>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${bbsList}">	
			<tr>
				<td>${vo.bbsNo}</td>
				<td>
					<a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">
					<c:out value="${vo.bbsTitle}"/>
					</a>
				</td>
				<td><c:out value="${vo.bbsWriter}" /></td>
				<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%-- <% 
}
%> --%>

<a href="<%=request.getContextPath()%>/bbs/add.do">글쓰기</a>
<%-- <a href="${pageContext.request.contextPath}/student/add.do">학생추가</a>
<a href="<c:url value="/student/add.do" />">학생추가</a>

<a href="${pageContext.request.contextPath}/student/delform.do">학생삭제</a> --%>

<%-- <a href="<c:url value="/student/del.do">
	<c:param name="stuNo">${vo.stuNo}</c:param>
</c:url>">학생삭제</a> --%>

<%-- <a href="<c:url value="/student/delform.do"></c:url>">학생삭제</a> --%>

<%-- <c:if test="${param.message != null}">
<script>
	alert("${param.message}");
</script>
</c:if> --%>


</body>
</html>
