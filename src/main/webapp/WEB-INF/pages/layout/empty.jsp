<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" 	uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- ������ ��� ��Ÿ��  -->
<tiles:insertAttribute name="pageCss" />
</head>
<body>
	<tiles:insertAttribute name="content" />
</body>
</html>

<!-- ������ ��� ��ũ��Ʈ  -->
<tiles:insertAttribute name="pageScript" />