<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h2>게시글 정보 변경</h2>
	<form action="${contextPath}/bbs/edit.do" method="post">
	<%-- <form action=" <c:url value="/student/stuAdd.jsp" /> "> --%>
		<%-- 학번 : <c:out value="${svo.stuNo}" /> --%>
			<%-- <input type="hidden" name="stuNo" value="<c:out value="${svo.stuNo}" />" /> --%> <br>
		번호 : <input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" /> <br>
		<%-- 번호 : <input type="text" name="bbsNo" value="${bbsVo.bbsNo}" readonly="readonly" /> <br> --%>
		
		
		<c:set value="${bbsVo.bbsWriter == loginUser.stuNo}" var="isMine" scope="application"/>
		<c:set value="${pageContext.request.contextPath}" var="contextPath" scope="application"/> 
		
		<%-- 제목 : <input <c:if test="${bbsVo.bbsWriter != loginUser.stuNo}">readonly</c:if> type="text" name="bbsTitle" value="<c:out value="${bbsVo.bbsTitle}" />" /> <br>
		내용 : <textarea <c:if test="${bbsVo.bbsWriter != loginUser.stuNo}">readonly</c:if> name="bbsContent" rows="5" cols="30"><c:out value="${bbsVo.bbsContent}" /></textarea> <br> --%>
		<%-- 제목 : <input ${bbsVo.bbsWriter != loginUser.stuNo? 'readonly' : ''} type="text" name="bbsTitle" value="<c:out value="${bbsVo.bbsTitle}" />" /> <br>
		내용 : <textarea ${bbsVo.bbsWriter != loginUser.stuNo? 'readonly' : ''} name="bbsContent" rows="5" cols="30"><c:out value="${bbsVo.bbsContent}" /></textarea> <br> --%>
		제목 : <input ${isMine? '' : 'readonly'} type="text" name="bbsTitle" value="<c:out value="${bbsVo.bbsTitle}" />" /> <br>
		내용 : <textarea ${isMine? '' : 'readonly'} name="bbsContent" rows="5" cols="30"><c:out value="${bbsVo.bbsContent}" /></textarea> <br>
		
		
		<c:forEach var="vo" items="${bbsVo.attachList}">
		첨부파일:  <a href="${contextPath}/bbs/down.do?attNo=${vo.attNo}"><c:out value="${vo.attOrgName}" /></a> <br>
		</c:forEach>
		작성자: <input type="hidden" name="bbsWriter" value="${bbsVo.bbsWriter}" /><c:out value="${bbsVo.bbsWriter}" /><br>
		등록일 : <fmt:formatDate value="${bbsVo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
		
		<c:if test="${bbsVo.bbsWriter == loginUser.stuNo}">
			<input type="submit" /> <!-- type="button" id="editBtn" value="변경"-->
			<a id="delLink" href="${contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}"><button type="button">삭제</button></a>
			<!-- <input type="button" id="DelBtn" value="삭제"> -->
		</c:if>
	
	</form>
	
<hr> <!-- 수평선 -->
<form id="replyForm" action="${contextPath}/reply/add.do" method="post">
	<input type="hidden" name="repBbsNo" value="${bbsVo.bbsNo}" />
	<textarea name="repContent" rows="3" cols="30"></textarea>
	<!-- <input type="submit" /> -->
	<input id="repSaveBtn" type="button" value="저장" />
</form>

<hr>

<div id="replyList"></div>

<template id="replyTemp">
	<div class="repContent"></div>
	<div class="repWriter"></div>
	<div class="repRegDate"></div>
	<input type="button" value="삭제" class="delBtn" data-no=""/>
	<hr>
</template>


<%-- <script src="${contextPath}/resources/js/jquery-3.7.0.min.js"></script> --%>
<script type="text/javascript">
	/*$('#delLink').on('click', function(ev){
		var ok = confirm('정말 삭제?');
		if (!ok) ev.preventDefault();
	});*/

	$('#delLink').on('click', function(){
		var ok = confirm('정말 삭제?');
		if (!ok) return false;
	});
	
	//var $repTemp = $(document.querySelector('#replyTemp').content);
	var $repTemp = $(document.getElementById('replyTemp').content);
	

	//댓글을 추가하면, 곧바로 댓글목록에 출력되도록 구현
	// 각 댓글 아래에 삭제 버튼을 출력
	function refreshReplyList(){
		
		//"${pageContext.request.contextPath}/reply/list.do"로 GET 방식 AJAX 요청을 전송하여
		//현재 글에 대한 댓글들을 받아오도록 구현
		$.ajax({
		  url: "${pageContext.request.contextPath}/reply/list.do", //요청주소
		  method: "GET", //요청방식
		  data: { repBbsNo : ${bbsVo.bbsNo} }, //요청파라미터
		  dataType: "json"	//응답데이터타입
		}).done(function( data ) {
			console.log(data); // "1개의 댓글 저장" 이라고 출력되도록
			
			/*
			var listHtml = '';
			for (var i=0; i<data.length; i++){
				var repVo = data[i];
				console.log( repVo.repContent, repVo.repWriter, repVo.repRegDate );
				listHtml += '<div>' +  repVo.repContent + '</div>'; 
				listHtml += '<div>' + repVo.repWriter + '</div>'; 
				listHtml += '<div>' + repVo.repRegDate; + '</div>';
				//listHtml += '<div>' + repVo.repNo; + '</div>';				
				//listHtml += '<input type="hidden" name="repNo" value="' + repVo.repNo + '" />';
				//listHtml += '<div><button onclick="location.href=\'${pageContext.request.contextPath}/reply/del.do?repNo=' + repVo.repNo + '\'" id="DelBtn">삭제</button></div>';
				//listHtml += '<div><input onclick="delReply()" class="delBtn" type="button" value="삭제" /></div>';
				if ( repVo.repWriter === '${loginUser.stuNo}' ) {
					listHtml += '<input data-no="' + repVo.repNo + '" class="delBtn" type="button" value="삭제" />';
				}
				listHtml += '<hr>';
			}
			console.log(listHtml); 
			//listHtml 값을 id="replyList"인 div 엘리먼트의 내용으로 출력
			$('#replyList').html( listHtml );
			*/
			
			/*$('.delBtn').on('click', function(){
				confirm('정말 삭제?');
			});*/
			
			
			
			var listHtml = [];
			for (var i=0; i<data.length; i++){
				var repVo = data[i];
				console.log( repVo.repContent, repVo.repWriter, repVo.repRegDate );
				
				/*
				listHtml.push( $('<div>').text( repVo.repContent ) ); 
				listHtml.push( $('<div>').text( repVo.repWriter ) ); 
				listHtml.push( $('<div>').text( repVo.repRegDate ) ); 
				if ( repVo.repWriter === '${loginUser.stuNo}' ) {
					listHtml.push( $('<input>')//.attr( 'data-no', repVo.repNo )
											   //.attr( 'type', "button" )
												.attr({ 'data-no':repVo.repNo, 'type':'button' })
												.addClass('delBtn') //.attr( 'class', "delBtn" )
												.val('삭제') //.attr( 'value', "삭제") 
								);
				}
				listHtml.push( $('<hr>') );
				*/
				
				var $newRep = $repTemp.clone();
				$newRep.find('.repContent').text( repVo.repContent );
				$newRep.find('.repWriter').text( repVo.repWriter );
				$newRep.find('.repRegDate').text( repVo.repRegDate );
				if ( repVo.repWriter === '${loginUser.stuNo}' ) {
					$newRep.find('.delBtn').attr( 'data-no', repVo.repNo );
				} else {
					$newRep.find('.delBtn').remove();
				}
				listHtml.push( $newRep );
				
			}
			console.log(listHtml); 
			//listHtml 값을 id="replyList"인 div 엘리먼트의 내용으로 출력
			//$('#replyList').html('').append( listHtml );
			$('#replyList').empty().append( listHtml );
			
			
		}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을 때 실행
		  alert( "Request failed: " + textStatus );
		});
		
	}
	
	refreshReplyList();
	
	/*$('#replyList').on('click', '.delBtn', function(){
		confirm('정말 삭제?');
	});*/
	
	/*function delReply() {
		confirm('정말 삭제?');
	}*/
	
	
	$('#replyList').on('click', '.delBtn', function(){
		 //alert("삭제!" + this.getAttribute('data-no') );
		 //alert("삭제!" + $(this).attr('data-no') );
		 if ( !confirm('정말 삭제?') ) return;
		
		$.ajax({
			  url: "${pageContext.request.contextPath}/reply/del.do", //요청주소
			  method: "GET", //요청방식
			  data: { repNo : $(this).attr('data-no') }, //요청파라미터
			  dataType: "json" //응답데이터타입
			}).done(function( msg ) { //요청 전송 후 성공적으로 응답을 받았을 때 실행
				refreshReplyList();
				alert(msg.result + "개의 댓글 삭제");
			  /* $( "#log" ).html( msg ); */
			}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을 때 실행
			  alert( "Request failed: " + textStatus );
			});
		
	});

	
	
	//저장버튼을 클릭했을 때, AJAX로 댓글 저장 요청을 전송
	//AJAX
	//(1)XmlHttpRequest 객체 사용
	//(2)fetch() 함수 사용
	//(3)$.ajax() 메서드 사용

	//저장버튼을 클릭했을 때, 알럿창으로 '클릭!' 출력
	$('#repSaveBtn').on('click', function() {
		/* alert("클릭!"); */
		
		$.ajax({
			  url: "${pageContext.request.contextPath}/reply/add.do", //요청주소
			  method: "POST", //요청방식
			  //data: 'repBbsNo=${bbsVo.bbsNo}&repContent='+$('[name="repContent"]').val() , //요청파라미터
			  data: { repBbsNo : ${bbsVo.bbsNo}, repContent : $('[name="repContent"]').val() }, //요청파라미터
			  //data: $('#replyForm').serialize(),
			  dataType: "json" //응답데이터타입
			}).done(function( msg ) { //요청 전송 후 성공적으로 응답을 받았을 때 실행
				refreshReplyList();
				alert(msg.result + "개의 댓글 저장");
				$('[name="repContent"]').val('');
			  /* $( "#log" ).html( msg ); */
			}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을 때 실행
			  alert( "Request failed: " + textStatus );
			});
		
	});
	
	
	/* $('#repSaveBtn').click(function(){}) */
		
	$('#editBtn').on('click', function() {
		/* alert("클릭!"); */
		
		$.ajax({
			  url: "${pageContext.request.contextPath}/bbs/edit.do", //요청주소
			  method: "POST", //요청방식
			  data: { bbsNo : ${bbsVo.bbsNo}, bbsTitle : "${bbsVo.bbsTitle}", bbsContent : "${bbsVo.bbsContent}" }, //요청파라미터
			  dataType: "text" //응답데이터타입
			}).done(function( msg ) { //요청 전송 후 성공적으로 응답을 받았을 때 실행
				alert( msg );
				location.href="${pageContext.request.contextPath}/bbs/list.do"
			  /* $( "#log" ).html( msg ); */
			}).fail(function( jqXHR, textStatus ) { //요청 처리에 오류가 발생했을 때 실행
			  alert( "Request failed: " + textStatus );
			});
		
	});
	
	/*
	$('#DelBtn').on('click', function() {
		$.ajax({
			  url: "${pageContext.request.contextPath}/bbs/del.do",
			  method: "GET", 
			  data: { bbsNo : ${bbsVo.bbsNo} }, 
			  dataType: "text" 
			}).done(function( msg ) {
				document.write(msg);
				//alert( msg );
				//location.href="${pageContext.request.contextPath}/bbs/list.do"
			}).fail(function( jqXHR, textStatus ) {
			  alert( "Request failed: " + textStatus );
			});
	});
	*/
	
	
	
</script>	


	
<!-- </body>
</html> -->
	
		
