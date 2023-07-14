<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <title><tiles:getAsString name="title"/></title> --%>
<title><tiles:insertAttribute name="title"/></title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>
 	<tiles:insertAttribute name="menu" />
 	
 	<tiles:insertAttribute name="body" />
 	
 	<tiles:insertAttribute name="footer" />
</body>
</html>