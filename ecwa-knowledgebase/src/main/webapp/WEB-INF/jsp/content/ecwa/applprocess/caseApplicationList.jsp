<%@ include file="../../../include/taglib.jsp"%>
<script>

	window.onload=function(){
	     var radios=document.getElementsByName("selectappl");
	     var form1=document.getElementById("OnlineAppList");
	     for(i=0;i<radios.length;i++){
	        radios[i].onclick=function() { OnlineAppList.submit();buttonsDisabled(true); };
	      }
	  };
	  
	$("form").on("submit", function () {
    	$(this).find(":submit").prop("disabled", true);
     	$(this).find(":button").prop("disabled", true);
	});
	
	function processStatusSel(selectObject){
		document.OnlineAppList.action.value=selectObject.value;
		document.OnlineAppList.submit();
		buttonsDisabled(true);
	}
  
</script>

	<form name="OnlineAppList" id="OnlineAppList" method="post" action="${pageContext.request.contextPath}/cswp/user/caseApplicationList.htm">
		
		<input type="hidden" name="action" id="action" value="">
		
		<div id="contentTwo">
			<div class="gutter2">
				<div id="box">
					<h2>Child Support Applications</h2>
				<hr>
				</div>
				<br>
				<p><font color="red"><b>Select application to view details</b></font></p>
				<div>
					<table width='100%'>
						<tr>
							<td>
								<table border="1">
									<tr>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>Applicant Name</th>
										<th>DOB</th>
										<th>SSN</th>
										<th>Ref Number</th>
										<th>Appl Submit Date</th>
										<th>County</th>
										<th title="Select 'complete' in the drop down menu to view completed applications.">Appl Status
										<c:if test="${searchType !='searchadv'}">
											&nbsp;
											<select name="applStatus" id="applStatus" onchange="processStatusSel(this)" title="Select 'complete' in the drop down menu to view completed applications.">
												<option value="3" ${appStatusSel == '3' ? 'selected' : ''} >Submit</option>
												<option value="4" ${appStatusSel == '4' ? 'selected' : ''}>Complete</option>
											</select>
										</c:if>
										</th>	
										<th>Appl Locked</th>
										<th>Last Updated By</th>
									</tr>
									<c:choose>
										<c:when test="${empty appList}">
											<tr><td colspan="11" align="center">Application Not Found</td></tr>
										</c:when>
										<c:otherwise>
										<c:forEach var="appl" items="${appList}" varStatus="tc">
										<tr>
											<td>${tc.count}.</td>
											<td><input type="radio" name="selectappl" id="selectappl" value="${appl.getApplicationId()}"></td>
											<td>${appl.getApplicantName()}</td>
											<td>${appl.getApplicantDOB()}</td>
											<td>${appl.getApplicantSsn()}</td>
											<td>${appl.getApplicationId()}</td>
											<td>${appl.getApplCmpltDtStr()}</td>
											<td>${appl.getApplCountyDesc()}</td>
											<c:choose>
												<c:when test="${appl.getApplStatus() == '4'}">
													<td><font color="red"><b>${appl.getApplStatusDesc()}</b></font></td>
												</c:when>
											 	<c:otherwise>
											 		<td>${appl.getApplStatusDesc()}</td>
											 	</c:otherwise>
											</c:choose>
  											<td>
  												<c:if test="${appl.getIsApplLocked().equalsIgnoreCase('y') && appl.getApplStatus() !='4'}">
  													<div class="icon-lock">
  														<div class="lock-top-1"></div>
	      												<div class="lock-top-2"></div>
	      												<div class="lock-body"></div>
    													<div class="lock-hole"></div>
  													</div>
  												</c:if>
  												<c:if test="${appl.getIsApplLocked().equalsIgnoreCase('n') || appl.getApplStatus() =='4'}">
  													<div class="icon-unlock">
  														<div class="unlock-top-1"></div>
	      												<div class="unlock-top-2"></div>
	      												<div class="unlock-body"></div>
    													<div class="unlock-hole"></div>
  													</div>
  												</c:if>
  												<input type="hidden" name="${appl.getApplicationId()}_locked" id="${appl.getApplicationId()}_locked" value="${appl.getIsApplLocked()}">
  											</td>
											<td>
												${appl.getWrkrName()}
												<input type="hidden" name="${appl.getApplicationId()}_lockedBy" id="${appl.getApplicationId()}_lockedBy" value="${appl.getWrkrNcId()}">
												<input type="hidden" name="${appl.getApplicationId()}_lockedByName" id="${appl.getApplicationId()}_lockedByName" value="${appl.getWrkrName()}">
												<input type="hidden" name="${appl.getApplicationId()}_status" id="${appl.getApplicationId()}_status" value="${appl.getApplStatus()}">
											</td>
										</tr>
									</c:forEach>
									</c:otherwise>
								</c:choose>
								</table>
							</td>
						</tr>
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
										<td>${casePart.getBrthDt()}</td>
										<td>${casePart.getSsnNb()}</td>
									</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						</c:if>	
						<tr>
							<td>
								<table>
									<tr>
										<td><input type="button" name="advsearch" value="Advanced Search" title="Search for applications." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationSearch.htm'"></td>	
										<c:if test="${searchType.equalsIgnoreCase('searchadv')}">
											<td>&nbsp;</td>
											<td><input type="button" name="applst" value="Back To Application List" title="Return to list of applications." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationList.htm'"></td>	
										</c:if>								
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

