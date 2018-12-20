<%@ include file="../include/taglib.jsp"%>
<div>
	<ul class="nav">
	<sec:authorize var="loggedIn" access="isAuthenticated()" />
		<c:choose>
			<c:when test="${loggedIn}">	
				<li><a href="${pageContext.request.contextPath}/loggedInHome.htm">Home</a></li>
			</c:when>	
			 <c:otherwise>	
			 	<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
			 </c:otherwise>
		</c:choose>
	</ul>
</div>