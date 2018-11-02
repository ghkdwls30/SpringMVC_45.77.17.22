<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav aria-label="...">
	  <ul class="pagination justify-content-center">
	  
	    <li class="page-item <c:if test="${!pageInfo.hasPreviousPage}">disabled</c:if>">
	      <a class="page-link" href="javascript:changePage('${pageInfo.prePage}')" tabindex="-1">이전</a>
	    </li>
	    <c:forEach items="${pageInfo.navigatepageNums}" var="navigatepageNum">
	    	<li class="page-item <c:if test="${pageInfo.pageNum eq navigatepageNum }">active</c:if>">
	    		<a class="page-link" href="javascript:changePage('${navigatepageNum}')">${navigatepageNum}</a>
	    	</li>
	    </c:forEach>
	    <li class="page-item <c:if test="${!pageInfo.hasNextPage}">disabled</c:if>">
	      <a class="page-link" href="javascript:changePage('${pageInfo.nextPage}')">다음</a>
	    </li>
	  </ul>
</nav>