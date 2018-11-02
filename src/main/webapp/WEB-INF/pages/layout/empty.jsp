<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" 	uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- 페이지 모듈 스타일  -->
<tiles:insertAttribute name="pageCss" />
</head>
<body>
	<tiles:insertAttribute name="content" />
</body>
</html>

<!-- 페이지 모듈 스크립트  -->
<tiles:insertAttribute name="pageScript" />