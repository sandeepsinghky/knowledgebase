<%@ include file="../../../include/taglib.jsp"%>
<script>

	window.onload=function(){
	     var radios=document.getElementsByName("caseId");
	     var form1=document.getElementById("OnlineAppCreateCase");
	     for(i=0;i<radios.length;i++){
	        radios[i].onclick=function() { OnlineAppCreateCase.submit(); buttonsDisabled(true);};
	      }
	  };
	  
	  
	 function processPart(formButton)
	 {
		var selected = false;
		var selValue="";
		var cpType = false;
		var ncpType = false;
		var chldType = false;
		var ncpCount = 0;
		
		for(i=0; i<document.OnlineAppCreateCase.selectCasePart.length; i++ ){
			if(document.OnlineAppCreateCase.selectCasePart[i].checked){
				if (document.OnlineAppCreateCase.selectCasePart[i].value == ""){
					alert("MPI# is not tied to one of the application participant selected");
					document.OnlineAppCreateCase.selectCasePart[i].focus();
					return false;
				}
				else{
					if (document.getElementById(document.OnlineAppCreateCase.selectCasePart[i].value+"_part").value == "1"){
						cpType = true;
					}
					if (document.getElementById(document.OnlineAppCreateCase.selectCasePart[i].value+"_part").value == "2"){
						ncpType = true;	
						ncpCount++;
					}	
					if (document.getElementById(document.OnlineAppCreateCase.selectCasePart[i].value+"_part").value == "3"){
						chldType = true;
					}
				}
				selected = true;
				if (selValue=="")
					selValue = document.OnlineAppCreateCase.selectCasePart[i].value;
				else
					selValue = selValue+","+document.OnlineAppCreateCase.selectCasePart[i].value;
			}
		}
		if (!selected){
			alert("Select participants to create case.");
			return false;
		}
		if(!cpType || !ncpType || !chldType){
			alert("Select CP (custodial parent/guardian), NCP (noncustodial parent) and at least one child to create a case.");
			return false;
		}
		if(ncpCount > 1){
			alert("Cannot create a case with two NCPs in the same case");
			return false;
		}
		document.OnlineAppCreateCase.selCasePartVal.value=selValue;
		document.OnlineAppCreateCase.action.value="createCase";
		document.OnlineAppCreateCase.submit();
		buttonsDisabled(true);
		return true;
	 }
	  
	  
</script>

	<form name="OnlineAppCreateCase" id="OnlineAppCreateCase" method="post" action="${pageContext.request.contextPath}/cswp/user/caseActsCreate.htm">
		
		<input type="hidden" name="action" id="action" value="">
		<input type="hidden" name="selCasePartVal" id="selCasePartVal" value="">
		
		<c:if test="${!empty message}">
				<div class="error message-box">${message}</div>
			</c:if>

		<c:if test="${!empty success}">
			<div class="success message-box">${success}</div>
		</c:if>
		
		<div id="contentTwo">
			<div class="gutter2">
				<div class="box">
					<h2>Case Creation</h2>
					<hr>
				</div>
				<h3>Reference Number: ${applId}</h3>
				
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>

				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
				
				<p><font color="red">Select participants to create case</font></p>

				<div>
					<table>
						<tr>
							<td>
								<table border="1">
									<tr>
										<th>&nbsp;</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Relation</th>
										<th>DOB</th>
										<th>SSN</th>
										<th>Race</th>
										<th>Sex</th>
										<th>MPI</th>
									</tr>
									<c:forEach var="part" items="${partList}" varStatus="tc">
										<tr>
											<td>
												<input type="checkbox" name="selectCasePart" id="selectCasePart" ${part.isPartSelected() ? 'checked' : ''} value="${part.getPartId()}">
												<input type="hidden" name="${part.getPartId()}_part" id="${part.getPartId()}_part" value="${part.getPartType()}">
											</td>
											<td>${part.getApplicantFNm()}</td>
											<td>${part.getApplicantLNm()}</td>
											<td>${part.getPartTypeDesc()}</td>
											<td>${part.getBrthDtStr()}</td>
											<td>${part.getSsnNb()}</td>
											<td>${part.getEthncGrpDesc()}</td>
											<td>${part.getApplicantGenderDesc()}</td>
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
										<c:if test="${not empty partList}">
										<td><input type="button" name="createCase" value="Create Case" onclick="return processPart(this)"></td>
										</c:if>
										<td><input type="button" name="back" value="Back To Application Details" title="Return to application details." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationPartList.htm'; buttonsDisabled(true);"></td>
										<td><input type="button" name="advsearch" value="Advanced Search" title="Search for applications." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationSearch.htm'; buttonsDisabled(true);"></td>									
									</tr>
								</table>
							</td>	
						</tr>
						<c:if test="${not empty applCases}">
						<tr><td><hr></td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
        						<fieldset>
          							<legend><strong>Case Information for this Application</strong></legend>
          							<p>Select case to view details.</p>
									<table width="50%" border="1">
          								<tr>
											<th>&nbsp;</th>
											<th align="center">IVD Case</th>
          								</tr>
          								<c:forEach var="caseId" items="${applCases}">
          								<tr>
          									<td><input type="radio" name="caseId" id="caseId" value="${caseId}" ${selectedCase == caseId ? 'checked' : ''}></td>
          									<td>${caseId}</td>
          								</tr>
          								</c:forEach>
									</table>
								</fieldset>
							</td>
						</tr>
						</c:if>
						<tr><td>&nbsp;</td></tr>
						<c:if test="${not empty casePartList}">
						<tr>
							<td>
								<table border="1">
									<tr>
										<th colspan='4'><b>Case Participants for ${selectedCase}</b></th>
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
					</table>
				</div>
				<br>
			</div>
		</div>
	</form>

