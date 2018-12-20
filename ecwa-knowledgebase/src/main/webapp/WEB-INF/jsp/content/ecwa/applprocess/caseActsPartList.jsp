<%@ include file="../../../include/taglib.jsp"%>
<script>
	window.onload=function(){
	     var radios=document.getElementsByName("selectActsPart");
	     var form1=document.getElementById("ActsPartList");
	     for(i=0;i<radios.length;i++){
	        radios[i].onclick=function() { ActsPartList.submit(); buttonsDisabled(true);};
	      }
	  };
	  
	  $("ActsPartList").on("submit", function () {
    	$(this).find(":submit").prop("disabled", true);
     	$(this).find(":button").prop("disabled", true);
	});
</script>

	<form name="ActsPartList" id="ActsPartList" method="post" action="${pageContext.request.contextPath}/cswp/user/caseActsPartList.htm">
		
		<input type="hidden" name="action" id="action" value="">
		
		<div id="contentTwo">
			<div class="gutter2">
				<div class="box">				
					<h2>ACTS Participant Search Results</b></h2>
					<hr>
				</div>
				<div>
					<table>
						<tr>
							<td>Name: ${casePartName}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><b>DOB:</b> ${casePartDOB}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><b>SSN:</b> ${casePartSSN}</td>
						</tr>
					</table>
				</div>
				<c:choose>
				<c:when test="${not empty partList}">
					<p><font color="red">Select ACTS participant for details.</font></p>
				</c:when>
				<c:otherwise>
					<p><font color="red">No results found.</font></p>
				</c:otherwise>
				</c:choose>
				<div>
					<table>
						<tr>
							<td>
								<table border="1">
									<tr>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>DOB</th>
										<th>SSN</th>
										<th>Race</th>
										<th>Sex</th>
										<th>MPI</th>
									</tr>
									<c:forEach var="part" items="${partList}" varStatus="tc">
										<tr>
											<td>${tc.count}.</td>
											<td><input type="radio" name="selectActsPart" id="selectActsPart" value="${part.getPartId()}"></td>
											<td>${part.getApplicantFNm()}</td>
											<td>${part.getApplicantLNm()}</td>
											<td>${part.getBrthDtStr()}</td>
											<td>${part.getSsnNb()}</td>
											<td>${part.getEthncGrp()}</td>
											<td>${part.getApplicantGender()}</td>
											<td>${part.getPartId()}</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<table>
									<tr>
										<td><input type="button" name="back" value="Back To Application Details" title="Return to application details." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationPartList.htm'; buttonsDisabled(true);"></td>									
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<br>
			</div>
		</div>
	</form>

