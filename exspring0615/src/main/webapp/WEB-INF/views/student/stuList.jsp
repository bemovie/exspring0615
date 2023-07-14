<%-- <%@page import="com.exam.student.StudentVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.student.StuDaoBatis"%>
<%@page import="com.exam.student.StudentDao"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- <%! //선언부

private StudentDao studentDao = new StuDaoBatis();

%>

<% //java code 입력

List<StudentVo> list = studentDao.selectStudentList();
request.setAttribute("studentList", list);

%> --%>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset=\"UTF-8\">
<title>학생 목록</title>	
<style>
/* a {text-decoration : none; color : black;} */
</style>	
</head>
<body> -->

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

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
	
<c:forEach var="vo" items="${studentList}">	
	
<!-- System.out.println(vo.getStuNo() + ":" + vo.getStuName() + ":" + vo.getStuScore()); -->
	<p> <a href="${pageContext.request.contextPath}/student/edit.do?stuNo=<c:out value="${vo.stuNo}" />"><c:out value="${vo.stuNo}"/></a> :  <c:out value="${vo.stuName}"/> : ${vo.stuScore}
		
	<a href="${pageContext.request.contextPath}/student/del.do?stuNo=<c:out value="${vo.stuNo}"/>"><button type="button">삭제</button></a>

	<c:url value="/student/del.do" var="delUrl">
		<c:param name="stuNo">${vo.stuNo}</c:param>
	</c:url>
	<a href="${delUrl}">삭제</a>
	</p>

</c:forEach>

<%-- <% 
}
%> --%>

<a href="<%=request.getContextPath()%>/student/add.do">학생추가</a>
<a href="${pageContext.request.contextPath}/student/add.do">학생추가</a>
<a href="<c:url value="/student/add.do" />">학생추가</a>

<a href="${pageContext.request.contextPath}/student/delform.do">학생삭제</a>

<%-- <a href="<c:url value="/student/del.do">
	<c:param name="stuNo">${vo.stuNo}</c:param>
</c:url>">학생삭제</a> --%>

<a href="<c:url value="/student/delform.do"></c:url>">학생삭제</a>

<!-- </body>
</html> -->
