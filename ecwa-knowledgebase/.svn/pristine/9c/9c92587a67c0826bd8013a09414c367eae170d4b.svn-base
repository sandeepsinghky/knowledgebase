<%@ include file="../../../include/taglib.jsp"%>

<script>

	$(function() {
    	$( "#dtFrom" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"    	
    	});
	});
	
	$(function() {
    	$( "#dtTo" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"    	
    	});
	});
	
	
	function validateform() {
		var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
		var ssn_regex = /^\d{9}$/;
		var refnum_regex = /^\d{10}$/;
		var zip_regex = /^\d{5}$/;
		var name_regex = /^[a-zA-Z ]+$/;
		
		if (document.OnlineAppSearch.refNum.value.trim() != "" && !refnum_regex.test(document.OnlineAppSearch.refNum.value)){
			alert("Reference number must be 10 digits");
			document.OnlineAppSearch.refNum.focus();
			return false;
		}
		else if (document.OnlineAppSearch.fName.value != "" && !name_regex.test(document.OnlineAppSearch.fName.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppSearch.fName.focus();
			return false;
		} else if (document.OnlineAppSearch.lName.value !="" && !name_regex.test(document.OnlineAppSearch.lName.value.trim())){
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppSearch.lName.focus();
			return false;
		} else if (document.OnlineAppSearch.ssnNum.value.trim() != "" && !ssn_regex.test(document.OnlineAppSearch.ssnNum.value)){
			alert("SSN should be all digits and 9 digits long.");
			document.OnlineAppSearch.ssnNum.focus();
			return false;		
		} else if (document.OnlineAppSearch.dtFrom.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppSearch.dtFrom.value, "date from")) || !(beforeFutureDate(document.OnlineAppSearch.dtFrom.value, "date from")))) {
			document.OnlineAppSearch.dtFrom.focus();
			return false;	
		} else if (document.OnlineAppSearch.dtTo.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppSearch.dtTo.value, "date to")) || !(beforeFutureDate(document.OnlineAppSearch.dtTo.value, "date to")))) {
			document.OnlineAppSearch.dtTo.focus();
			return false;	
		}  else if (document.OnlineAppSearch.dtFrom.value.trim() != "" && document.OnlineAppSearch.dtTo.value.trim() != ""
				&& !isDateFromBeforeDateTo(document.OnlineAppSearch.dtFrom.value.trim(), document.OnlineAppSearch.dtTo.value.trim())) {
			document.OnlineAppSearch.dtFrom.focus();
			alert("Date value for date from field should be before date to field value.");
			return false;	
		}
		document.OnlineAppSearch.submit();
		buttonsDisabled(true);
	}	
</script>

	<form name="OnlineAppSearch" id="OnlineAppSearch" method="post" action="${pageContext.request.contextPath}/cswp/user/caseApplicationSearch.htm" modelAttribute="appSearchBean">
		
		<div id="contentTwo">
			<div class="gutter2">				
				<div class="box">				
				<h2>Search For Child Support Applications</h2>
				<hr>
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>

				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
			</div>
				<br>
				<div>
					<table>
						<tr>
							<td>
								<table>
									<tr>
                    					<td>&nbsp;</td>
                    					<td align="left">Reference Number:</td>
                    					<td>&nbsp;</td>
                    					<td><input type="text" name="refNum" id="refNum" size="25" maxlength="10" title=" Enter 10 digit reference number to search for application."></td>
                  					</tr>
                  					<tr>
                    					<td>&nbsp;</td>
                    					<td align="left">County:</td>
                    					<td>&nbsp;</td>
                    					<td><select name="cnty" title=" Multi-county offices only: Select county from pick list to search for application.">
											<c:forEach var="cnty" items="${sessionScope.wrkrCntyLst}">
												<option value="${cnty.getCodeId()}" ${sessionScope.wrkrCnty.trim().equals(cnty.getCodeId().trim()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
											</c:forEach>
										</select></td>
                 					 </tr>
                  					<tr>
					                    <td>&nbsp;</td>
					                    <td>&nbsp;</td>
					                    <td>&nbsp;</td>
					                    <td>&nbsp;</td>
				                 	</tr>
				                  	<tr>
					                    <td>&nbsp;</td>
					                    <td align="left">First Name:</td>
					                    <td>&nbsp;</td>
					                    <td><input type="text" name="fName" id="fName" size="25" maxlength="15" title="Enter first name of applicant."></td>
				                  	</tr>
				                  	<tr>
					                    <td>&nbsp;</td>
					                    <td align="left">Last Name:</td>
					                    <td>&nbsp;</td>
					                    <td align="left"><input type="text" name="lName" id="lName" size="25" maxlength="17" title="Enter last name of applicant."></td>
				                  	</tr>
				                  	<tr>
			                    		<td>&nbsp;</td>
			                    		<td align="left">SSN:</td>
			                    		<td>&nbsp;</td>
			                    		<td><input type="text" name="ssnNum" id="ssnNum" size="15" maxlength="9" title="Enter 9 digit social security number in format 123456789."></td>
			                  		</tr>
			                  		<tr>
			                    		<td cospan='4'>&nbsp;</td>
			                  		</tr>
			                  		<tr>
					                    <td>&nbsp;</td>
					                    <td align="left">Req Date From:</td>
					                    <td>&nbsp;</td>
					                    <td><input type="text" name="dtFrom" id="dtFrom" size="10" maxlength="10" title="Enter date from which search for application should start."></td>
			                  		</tr>
			                  		<tr>
					                    <td>&nbsp;</td>
					                    <td align="left">Req Date To:</td>
					                    <td>&nbsp;</td>
					                    <td><input type="text" name="dtTo" id="dtTo" size="10" maxlength="10" title="Enter end date for application search."></td>
			                    	</tr>
								</table>
							</td>							
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<table>
									<tr>
										<!--  <td align="right"><input type="button" name="apvSearch" id="advsearch" value="Search" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationList.htm'"></td>-->
										<td align="right"><input type="button" name="advSearch" id="advsearch" value="Search" onclick="return validateform()"></td>
          								<td align="center"><input type="button" name="clbtn" id="clbtn" value="Clear" onclick="document.getElementById('OnlineAppSearch').reset();"></td>
          								<td align="center"><input type="button" name="back" value="Back To Application List" title="Return to list of applications." onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationList.htm'"></td>								
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

