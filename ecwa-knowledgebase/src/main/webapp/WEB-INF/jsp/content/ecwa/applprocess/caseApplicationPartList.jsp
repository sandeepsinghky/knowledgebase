<%@ include file="../../../include/taglib.jsp"%>
<script>

	window.onload=function(){
	     var radios=document.getElementsByName("caseId");
	     var form1=document.getElementById("OnlineAppPartList");
	     for(i=0;i<radios.length;i++){
	        radios[i].onclick=function() { OnlineAppPartList.submit(); buttonsDisabled(true);};
	      }
	  };
	  
	  
	$("form").on("submit", function () {
    	$(this).find(":submit").prop("disabled", true);
     	$(this).find(":button").prop("disabled", true);
	});  
	  
	 function processPart(formButton)
	 {
		var selected = false;
		var selValue;
	
		for(i=0; i<document.OnlineAppPartList.selectApplPart.length; i++ ){
			if(document.OnlineAppPartList.selectApplPart[i].checked){
				selected = true;
				selValue = document.OnlineAppPartList.selectApplPart[i].value;
				break;
			}
		}
		
		if (!selected){
			alert("Select a participant.");
			return false;
		}
		
		if(formButton.name=="searchPart"){
			document.OnlineAppPartList.action.value="searchActsPart";
		}
		else if(formButton.name=="createPart"){
			if (document.getElementById("mpi_"+selValue).value !=""){
				if(document.getElementById(selValue+"_part_resolved").value == 'N'){
					alert("Participant already exists in ACTS. Cannot create.");
					return false;
				}else if(document.getElementById(selValue+"_part_resolved").value == 'U'){
					alert("ACTS data is updated. Cannot create new participant.");
					return false;
				}
			}
			else{
				if (confirm("Creating participant before searching for an existing ACTS participant may result in a duplicate MPI number. Select 'Cancel' to search for an existing ACTS participant or select 'OK' to confirm creation of a new participant.")){        
		       		document.OnlineAppPartList.action.value="createActsPart";
		   	 	}else{
		  			return false;
		  		}
			}
		}
		else if(formButton.name=="updatePart"){
			if (document.getElementById("mpi_"+selValue).value ==""){
				alert("Participant MPI must be assigned before update can occur.");
				return false;
			}else if(document.getElementById(selValue+"_part_resolved").value == 'N'){
				alert("Cannot update new participant.");
				return false;
			}
			document.OnlineAppPartList.action.value="updateActsPart";
		}
		document.OnlineAppPartList.submit();
		buttonsDisabled(true);
		return true;
	 }
	  
	  function createButtonAction(formButton)
	  {
		var canCreateCase  = true;
		if(formButton.name=="createCase"){
			for(i=0; i<document.OnlineAppPartList.selectApplPart.length; i++ ){
				var selValue = document.OnlineAppPartList.selectApplPart[i].value;
				if(document.getElementById("mpi_"+selValue).value ==""){
					canCreateCase = false;
					break;
				}
			}
			if(!canCreateCase){
				alert("All application Participants must have MPI before creating a case");
				return false;
			}
			document.OnlineAppPartList.action.value="createActsCase";
		}
		else if(formButton.name=="createLock"){
			document.OnlineAppPartList.action.value="createApplLock";
		}
		else if(formButton.name=="unlock"){
			document.OnlineAppPartList.action.value="unlockAppl";
		}
		else if(formButton.name=="cmpltAppl"){
			if (confirm("Case created? Application printed? Completing application removes it from the list and additional processing is not allowed. Confirm completion of this application?")){        
		       document.OnlineAppPartList.action.value="cmpltAppl";
		    }else{
		  		return false;
		  	}
		  	//return true;			
		}
		else if(formButton.name=="printappl"){
			document.OnlineAppPartList.action.value="printappl";
			//return true;
		}
		document.OnlineAppPartList.submit();
		buttonsDisabled(true);
		return true;
	  }
	  
	  
</script>

<form name="OnlineAppPartList" id="OnlineAppPartList" method="post"	action="${pageContext.request.contextPath}/cswp/user/caseApplicationPartList.htm">

	<input type="hidden" name="action" id="action" value=""> 
	<input	type="hidden" name="locked" id="locked"	value="${sessionScope.isApplLocked}"> 
	<input type="hidden"		name="user" id="user" value="${sessionScope.userLoginName}"> 
	<input	type="hidden" name="lockedby" id="lockedby"	value="${sessionScope.applLockedBy}">

	<c:if test="${!empty message}">
		<div class="error message-box">${message}</div>
	</c:if>

	<c:if test="${!empty success}">
		<div class="success message-box">${success}</div>
	</c:if>

	<div id="contentTwo">
		<div class="gutter2">

			<h2>Child Support Application Details</h2>
			<hr>
			<br>
			<h3>
				Reference Number:
				${applId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Application
				Locked By:
				<c:if
					test="${sessionScope.applStatus != '4' && sessionScope.isApplLocked == 'Y'}">
					${sessionScope.applLockedByName}
				</c:if>
			</h3>
			<c:if test="${sessionScope.applStatus == '4'}">
				<h4>
					<font color="red">Application has been processed.</font>
				</h4>
			</c:if>

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
									<th>Relation</th>
									<th>DOB</th>
									<th>SSN</th>
									<th>Race</th>
									<th>Sex</th>
									<th>MPI</th>
								</tr>
								<c:forEach var="part" items="${partList}" varStatus="tc">
									<tr>
										<td>${tc.count}.</td>
										<c:choose>
											<c:when
												test="${sessionScope.isApplLocked == 'Y' && sessionScope.userLoginName == sessionScope.applLockedBy && sessionScope.applStatus != '4'}">
												<td><input type="radio" name="selectApplPart"
													id="selectApplPart" value="${part.getApplicantId()}"></td>
											</c:when>
											<c:otherwise>
												<td>&nbsp;</td>
											</c:otherwise>
										</c:choose>
										<td>${part.getApplicantFNm()}</td>
										<td>${part.getApplicantLNm()}</td>
										<td>${part.getPartTypeDesc()}</td>
										<td>${part.getBrthDtStr()}</td>
										<td>${part.getSsnNb()}</td>
										<td>${part.getEthncGrpDesc()}</td>
										<td>${part.getApplicantGenderDesc()}</td>
										<td>${part.getPartId()}<input type="hidden"
											name="mpi_${part.getApplicantId()}"
											id="mpi_${part.getApplicantId()}" value="${part.getPartId()}">
											<input type="hidden"
											name="${part.getApplicantId()}_part_resolved"
											id="${part.getApplicantId()}_part_resolved"
											value="${part.getPartIdResolved()}">
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<table>
										<tr>
											<c:if test="${not empty partList}">
												<c:if
													test="${sessionScope.applStatus != '4' && sessionScope.isApplLocked == 'Y' && sessionScope.userLoginName == sessionScope.applLockedBy}">
													<td><input type="button" name="searchPart"
														value="Search Participant" title="Search for a participant in ACTS."
														onclick="return processPart(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" name="createPart"
														value="Create Participant" title="Create new ACTS participant."
														onclick="return processPart(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" name="updatePart"
														value="Update Participant" title="Update demographic data for existing ACTS participant."
														onclick="return processPart(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" name="createCase" title="Create new ACTS case." value="Create Case"
														onclick="return createButtonAction(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" name="cmpltAppl" title="End processing of application."
														value="Complete Application"
														onclick="return createButtonAction(this)"></td>
													<!--  <td><input type="button" name="cmpltAppl" value="Complete Application">-->

												</c:if>
											</c:if>
										</tr>
									</table>
								</tr>
								<tr>
									<table>
										<tr>
											<c:if test="${sessionScope.applStatus != '4'}">
												<c:if
													test="${(sessionScope.isApplLocked == 'Y' && sessionScope.userLoginName == sessionScope.applLockedBy) || (sessionScope.isApplLocked == 'Y' && (sessionScope.wrkrIsAdmin == 'Y' || sessionScope.wrkrIsAdmin == 'S'))}">
													<td><input type="button" name="unlock"
														value="Unlock Application" title="Unlock application from restricted processing."
														onclick="return createButtonAction(this)"></td>
													<td>&nbsp;</td>
												</c:if>
												<c:if test="${sessionScope.isApplLocked == 'N' && sessionScope.wrkrIsAdmin != 'S'}">
													<td><input type="button" name="createLock"
														value="Lock Application" title="Lock application to restrict processing."
														onclick="return createButtonAction(this)"></td>
													<td>&nbsp;</td>
												</c:if>
											</c:if>
											<td><input type="button" name="printappl" value="View/Print Application" title="View and/or print application." onclick="return createButtonAction(this)"/></td>
											<td><input type="button" name="advsearch"
												value="Advanced Search" title="Search for applications." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationSearch.htm'; buttonsDisabled(true);"></td>
											<td>&nbsp;</td>
											<td><input type="button" name="back"
												value="Back To Application List" title="Return to list of applications."
												onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationList.htm'; buttonsDisabled(true);"></td>
										</tr>
									</table>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${not empty applCases}">
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
								<fieldset>
									<legend>
										<strong>Case Information for this Application</strong>
									</legend>
									<p>Select case to view details.</p>
									<table border="1">
										<tr>
											<th>&nbsp;</th>
											<th align="center">IVD Case</th>
										</tr>
										<c:forEach var="caseId" items="${applCases}">
											<tr>
												<td><input type="radio" name="caseId" id="caseId"
													value="${caseId}"
													${selectedCase == caseId ? 'checked' : ''}></td>
												<td>${caseId}</td>
											</tr>
										</c:forEach>
									</table>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</c:if>
					<c:if test="${not empty casePartList}">
						<tr>
							<td>
								<table border="1">
									<tr>
										<th colspan='4'><b>Case Participants for
												${selectedCase}</b></th>
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
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
	</div>
</form>

