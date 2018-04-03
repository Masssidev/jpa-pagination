<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${R}res/common.js"></script>
<link rel="stylesheet" href="${R}res/common.css">
</head>
<body>
	<div class="container">
		<h1>${ board}</h1>
		<form:form method="post" modelAttribute="articleRegistrationModel">
			<div class="form-group">
				<label>글번호:</label>
				<form:input path="no" class="form-control w200" />
				<form:errors path="no" class="error" />
			</div>
			<div class="form-group">
				<label>제목:</label>
				<form:input path="title" class="form-control w200" />
				<form:errors path="title" class="error" />
			</div>
			<%-- <div class="form-group">
				<label>내용</label>
				<form:select path="department.id" class="form-control w200"
					itemValue="id" itemLabel="departmentName" items="${ departments }" />
			</div> --%>
			<div class="form-group">
				<label>내용:</label>
				<form:input path="body" class="form-control w200" />
				<form:errors path="body" class="error" />
			</div>
			<button type="submit" class="btn btn-primary">
				<i class="glyphicon glyphicon-ok"></i> 저장
			</button>
			<c:if test="${ article.id > 0 }">
				<a href="delete?${ pagination.queryString }&id=${ article.id }"
					class="btn btn-danger" data-confirm-delete> <i
					class="glyphicon glyphicon-trash"></i> 삭제
				</a>
			</c:if>
			<a href="list?${pagination.queryString }" class="btn btn-default"> <i
				class="glyphicon glyphicon-list"></i> 목록으로
			</a>
		</form:form>
		<c:if test="${ not empty message }">
			<br />
			<div class="alert alert-info">${ message }</div>
		</c:if>
	</div>
</body>
</html>
