<%@ include file="../../../include/taglib.jsp"%>
<script>
	
	window.onload=function(){
     var radios=document.getElementsByName("selectPartCase");
     var form1=document.getElementById("ActsPartDetails");
     for(i=0;i<radios.length;i++){
        radios[i].onclick=function() { ActsPartDetails.submit();buttonsDisabled(true);};
      }
 	 };
 	 
 	 $("form").on("submit", function () {
    	$(this).find(":submit").prop("disabled", true);
     	$(this).find(":button").prop("disabled", true);
	});
  
 	function usePart1(formButton)
	{
		if (document.ActsPartDetails.PartIdResolved.value=='N')
		{
			alert("New MPI is already created for selected application participant. Cannot use existing ACTS participant.");
			return false;
		}else if (document.ActsPartDetails.PartIdResolved.value=='U')
		{
			alert("Existing participant was selected and ACTS data has been updated. Cannot use existing ACTS participant again.");
			return false;
		}
		if (confirm("Confirm assignment of existing ACTS MPI to this participant?")){        
			document.ActsPartDetails.action.value="usePart";
   	 	}else{
  			return false;
  		}
		//return true;
		document.ActsPartDetails.submit();
		buttonsDisabled(true);
	}
	
	
</script>

	<form name="ActsPartDetails" id="ActsPartDetails" method="post" action="${pageContext.request.contextPath}/cswp/user/caseActsPartDetails.htm">
		
		<input type="hidden" name="action" id="action" value="">
		<input type="hidden" name="PartIdResolved" id="PartIdResolved" value="${applPart.getPartIdResolved()}">
		<div id="contentTwo">
			<div class="gutter2">			
				<div class="box">
					<h2>ACTS Participant Details</b></h2>
					<hr>
				</div>
				<div>
					<table>
						<tr>
							<td><b>Name:</b> ${casePartName}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><b>DOB:</b> ${casePartDOB}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><b>SSN:</b> ${casePartSSN}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><b>MPI:</b> ${casePartMPI}</td>
						</tr>
					</table>
				</div>
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>

				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
				<div>
					<table>
						<tr>
							<td>
								<table border="1">
									<tr>
										
										<th>&nbsp;</th>
										<th>Case Number</th>
										<th>Participant Type</th>
										<th>Participant Status</th>
										<th>Case Type</th>
										<th>Case Status</th>
										<th>Proc Status</th>
										<th>Workable</th>
										<th>Inter State Case Number</th>
										<th>Resp Worker</th>
									</tr>
									<c:forEach var="part" items="${partList}">
										<tr>											
											<td><input type="radio" name="selectPartCase" id="selectPartCase" value="${part.getPartNbCase()}" ${selectedActsPartCase == part.getPartNbCase() ? 'checked' : ''}></td>
											<td>${part.getPartNbCase()}</td>
											<td>${part.getPartType()}</td>
											<td>${part.getPartStat()}</td>
											<td>${part.getPartCaseType()}</td>
											<td>${part.getPartCaseStat()}</td>
											<td>${part.getPartPrcsStat()}</td>
											<td>${part.getPartWorkable()}</td>
											<td>${part.getPartIntrCase()}</td>
											<td>${part.getRespWrkrId()}</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<c:if test="${not empty casePartList}">
						<tr>
							<td>
								<table border="1">
									<tr>
										<th colspan='4'><b>Case Participants:</b></th>
									</tr>
									<tr>
										<th>Name</th>
										<th>Part Type</th>
										<th>DOB</th>
										<th>SSN</th>
									</tr>
									<c:forEach var="casePart" items="${casePartList}">
									<tr>
										<td>${casePart.getPartFullNm()}</td>
										<td>${casePart.getPartType()}</td>
										<td>${casePart.getBrthDtStr()}</td>
										<td>${casePart.getSsnNb()}</td>
									</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						</c:if>	
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<table>
									<tr>
										<td><input type="button" name="usePart" value="Use Participant" title="Assign existing ACTS participant's MPI to applicant." onclick="return usePart1(this)"></td>
										<td><input type="button" name="back" value="Back To ACTS Participant List" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseActsPartList.htm'"></td>
										<!-- <td><input type="button" name="advsearch" value="Advanced Search" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationSearch.htm'"></td>-->										
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

