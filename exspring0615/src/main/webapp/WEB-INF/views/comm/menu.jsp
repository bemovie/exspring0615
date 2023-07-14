<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!-- 로그인이 된 경우 -->
<c:if test="${loginUser!=null}">
${loginUser.stuName}
${loginUser.getStuName()}
${loginUser["stuName"]}
<button><a href="${pageContext.request.contextPath}/student/logout.do">로그아웃</a></button>|
<button><a href="${pageContext.request.contextPath}/student/list.do">학생관리</a></button>|
<button><a href="${pageContext.request.contextPath}/bbs/list.do">게시판</a></button>|
</c:if>

<!-- 로그인이 되지 않은 경우 -->
<c:if test="${loginUser==null}">

<button><a href="${pageContext.request.contextPath}/student/login.do">로그인</a></button>
<button><a href="<%=request.getContextPath()%>/student/add.do">학생추가</a></button>
<button><a href="${pageContext.request.contextPath}/student/add.do">학생추가</a></button>
<button><a href="<c:url value="/student/add.do" />">학생추가</a></button>
</c:if>