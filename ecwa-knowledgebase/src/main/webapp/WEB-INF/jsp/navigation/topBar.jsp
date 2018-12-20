<%@ include file="../include/taglib.jsp"%>

<script>

 function confirmLogout() {
		
	if (confirm("Are you sure you want to logout?")){        
	    return true;
	}else{
	  	return false;
	}	
 }
 
</script>

<div>

	<ul class="tbar">


		<sec:authorize access="isAuthenticated()">
			<li>Welcome: ${sessionScope.wrkrName}<br><a href="<c:url value="/j_spring_security_logout" />"> Logout</a></li>

		</sec:authorize>

	</ul>
</div>