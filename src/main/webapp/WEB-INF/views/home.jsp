<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" 
	content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<title>책나라 도서관</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<style>
#main-nav {
	width:100%;
}

#main-container {
	display: flex;
	width: 100%;
	padding: 15px 15px 2000px;
}
#home-container {
	width: 70%;
	margin: auto;
	display: block;
}

#home-body {
	display:flex;
	flex-direction: column;
}

.body-box {
	border: 1px solid red;
	flex: 1 1 auto;
	overflow: auto;
	margin: 16px 8px 16px 16px;
}

.form-group {
	display: flex;
		
}
#search-btn {
	margin-left: 3px;
}
.custom-select {
	width: 20%;
}
</style>
</head>
<body>
<nav id="main-nav" class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="<c:url value='/' />">책나라 도서관</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarColor03">
    	<ul id="nav_menu" class="navbar-nav mr-auto">
      		<li class="nav-item active">
        		<a class="nav-link" href="<c:url value='/' />">Home <span class="sr-only">(current)</span></a>
      		</li>
      		<li class="nav-item">
      			<c:if test="${empty login_info}">
        			<a class="nav-link" href="<c:url value='/user/login' />">로그인</a>
        		</c:if>
        		<c:if test="${not empty login_info}">
        			<a class="nav-link" href="<c:url value='/user/logout' />">로그아웃</a>
        		</c:if>
      		</li>
      		<li id="menu_admin" class="nav-item">
       			<a class="nav-link" href="#">About</a>
      		</li>
      		
      		<c:if test="${login_info.user_role == 1}">
	      		<li id="menu_admin" class="nav-item">
	        		<a class="nav-link" href="<c:url value='/admin' />">관리자</a>
	      		</li>
      		</c:if>
      		
    	</ul>
    </div>
</nav>
<section id="main-container">
	<c:if test="${BODY == 'MAIN'}">
	<section id="home-container">
		<article id="search" class="body-box">
			<form action="<c:url value='/admin/book/search'/>" 
					method="POST"
					 autocomplete="off">
				<div class="form-group">
				    <select name="option" class="custom-select">
				      <option value="book_title" <c:if test="${OPTION == 'book_title' ||  empty OPTION}">selected="selected"</c:if>>도서명</option>
				      <option value="book_author" <c:if test="${OPTION == 'book_author'}">selected="selected"</c:if>>저자</option>
				      <option value="book_comp" <c:if test="${OPTION == 'book_comp'}">selected="selected"</c:if>>출판사</option>
				      <option value="book_isbn" <c:if test="${OPTION == 'book_isbn'}">selected="selected"</c:if>>도서코드</option>
				    </select>
					<input name="search" class="form-control" type="text" placeholder="Text Input" value="${SEARCH}">
					<button id="search-btn"class="btn btn-primary" type="submit">Search</button>
				</div>
			</form>
		</article>
		<div id="home-body">
			<article id="cate" class="body-box">
				<p>이책 어떠세요?
				<%@ include file="/WEB-INF/views/body/user_body.jspf" %>
			</article>
			<article id="rent" class="body-box">
				<p>책나라 화제의책
				<%@ include file="/WEB-INF/views/body/rent_body.jspf" %>
			</article>
			
		</div>
	</section>
	</c:if>
	<c:if test="${BODY == 'LOGIN'}">
		<%@ include file="/WEB-INF/views/body/user_login.jspf" %>
	</c:if>
	<c:if test="${BODY == 'ADMIN'}">
		<%@ include file="/WEB-INF/views/body/admin_main.jspf" %>
	</c:if>
</section>
</body>
</html>