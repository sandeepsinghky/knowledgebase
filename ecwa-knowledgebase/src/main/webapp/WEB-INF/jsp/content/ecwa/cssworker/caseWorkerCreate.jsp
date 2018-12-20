<%@ include file="../../../include/taglib.jsp"%>


<script type="text/javascript">
	 $(document).ready(function() {
		 hideOnLoad ()
			
		 $('#startTime').timepicker({
			 startTime: new Date(0,0,0,5,0,0) // 5:00:00 AM 

		 });
		 $('#endTime').timepicker({
			 startTime: new Date(0,0,0,15,0,0) // 3:00:00 PM - noon

		 });
		 $('#startLunchTime').timepicker({
			 startTime: new Date(0,0,0,11,0,0),
		 	 interval: 15  

		 });
		 $('#endLunchTime').timepicker({
			 startTime: new Date(0,0,0,11,0,0),
		 	interval: 15  
		 });
		 
		 
		 $( "#dateHired").datepicker({
		    	changeMonth: true,
		    	changeYear: true,
		    	yearRange: "-100:+10"    	
	    	});
		 
		 $('#resetbtn').click(function() {
			 
			 $("#error-message-box").hide();
			 $("#errorCaseWorker").hide();
			 $("#createDBWorker").find('input:text,select').val('');
			 $("#worker_num").val("");
			  
		 });
		 
		
		 $('#createDB').click(function() {
	 		 			var workingHrsError="";
					    var errorMessage="";
						$("#errorCaseWorker").hide();
						
						
						 
						if ($('#worker_num').val()==""){
							errorMessage =errorMessage +"<li>"  +"Please Enter Worker Number" + "</li>";
						}
						
									
						if ($('#last_name').val()==""){
							 errorMessage = errorMessage +"<li>"  + "Please Enter Last Name"+ "</li>";
						} else{
							var numFound = $('#last_name').val().match(/\d+/g);
							if (numFound != null) {
								errorMessage = errorMessage +"<li>"  + "Invalid Last Name "+ "</li>";
							}
							
						}
						
						if ($('#first_name').val()==""){
							 errorMessage = errorMessage +"<li>"  + "Please Enter First Name"+ "</li>";
						} else{
							var numFound = $('#first_name').val().match(/\d+/g);
							if (numFound != null) {
								errorMessage = errorMessage +"<li>"  + "Invalid First Name "+ "</li>";
							}
						}
						
			
						if ($('#worker_type').val()==""){
							 errorMessage = errorMessage +"<li>"  + "Please Enter Worker Type"+ "</li>";
						} 
			
						if ($('#term_id').val()==""){
							 errorMessage = errorMessage +"<li>"  + "Please Enter Term ID"+ "</li>";
						} 
						
						if ($('#printer_id').val()==""){
							 errorMessage = errorMessage +"<li>"  + "Please Enter Printer ID"+ "</li>";
						} 
						if ($( "#mfaSelect option:selected" ).text()=='Yes')  {
							if ($('#ncid').val()==""){
								 errorMessage = errorMessage +"<li>"  + "NCID is required If MFA Enabled "+ "</li>";
							} 
						}
						 
						workingHrsError =validateWorkingHours ();
						if (workingHrsError!=""){
							 errorMessage = errorMessage +workingHrsError;
						}
						
						if (errorMessage!=""){
							$('#errorCaseWorker').html("<ul>"+ errorMessage +"</ul>");
							$("#errorCaseWorker").show();
						}else{
							$("#action").val("createCaseWorkerDB");
							$('#createDBWorker').submit();
						}
			
    	});  
	
});

	 function validateWorkingHours () {
		 	var errorMsg="";
		 	var startTime= $('#startTime').val(); 
			var endTime= $('#endTime').val();
			var startLunchTime= $('#startLunchTime').val(); 
			var endLunchTime= $('#endLunchTime').val();
			 
			var startTimeDate = new Date('1/1/1991' + ' ' + startTime);       
			var endTimeDate = new Date('1/1/1991' + ' ' + endTime);
			var startLunchTimeDate = new Date('1/1/1991' + ' ' + startLunchTime);       
			var endLunchTimeDate = new Date('1/1/1991' + ' ' + endLunchTime);
			
			if (startTimeDate > endTimeDate){
				errorMsg="<li>"  +"End Time is before Start time" + "</li>";
				 
			}
			
			if (startLunchTimeDate > endLunchTimeDate){
				errorMsg= errorMsg+"<li>"  +"End Lunch Time is before Start Lunch time" + "</li>";
			}
			
			return errorMsg; 
	 }
	 
	 function hideOnLoad () {
		 $("#errorCaseWorker").hide();
		 $("#hideLupdatedateLabel").hide();
		 $("#hideLupdatedateText").hide();
		 $("#hideLupdatetimeText").hide();
		 $("#hideLupdateByLabel").hide();
		 $("#hideLupdateByText").hide();
	 }
	
</script>

<form name="createDBWorker" id="createDBWorker" method="post" action="${pageContext.request.contextPath}/cswp/user/updateDBWorker.htm" modelAttribute="caseWorker">
<input type="hidden" name="action" id="action" value="">
<div id="contentTwo">
	<div class="gutter2">
		 
			<div id="box">
			<h2>Create Case Worker</h2>
			<hr>
		</div><!--end div box --> 
		<div id="errorCaseWorker"></div>
		<c:if test="${!empty message}">
			<div id="error-message-box" class="error message-box">${message}</div>
		</c:if>
		
		<c:if test="${!empty success}">
			<div class="success message-box">${success}</div>
		</c:if>
	 
		<br>
		 <table class="caseWorkerModifyTable">
				 <tr>
					<td><label class="field"  for="worker_num">Worker #</label></td>
					<td> <input type="text" id="worker_num" name="worker_num" value=${caseWorker.getWorker_num()}></input></td>
					<td>  <label class="field"  for="worker_type">Worker Type</label></td> 
			   		<td>  <input type="text" id="worker_type" name="worker_type" value=${caseWorker.getWorker_type()} ></input> </td>
				 </tr>
				 <tr>
					<td> <label class="field"  for="last_name">Last Name</label></td>
					<td> <input type="text" id="last_name" name="last_name" value=${caseWorker.getLast_name()}></input></td>
			   		<td> <label class="field"  for="first_name">First Name</label></td>
			   		<td> <input type="text" id="first_name" name="first_name" value=${caseWorker.getFirst_name()}></input></td>
			   		<td> <label class="field"  for="middle_name">MI</label></td> 
			   		<td> <input type="text" id="middle_name" name="middle_name" size="1" maxlength="1" value=${caseWorker.getMiddle_name()}></input> </td>
				 </tr>
				 <tr>
				 	<td> <label class="field"  for="title">Title</label></td>
					<td> <input type="text" id="title" name="title" value=${caseWorker.getTitle()}></input></td>
				 </tr>
				
				 <tr>
					<td>  <label class="field"  for="ivd_area">IVD Area</label></td>
					<td> <input type="text" id="ivd_area" name="ivd_area" value=${caseWorker.getIvd_area()}></input></td>
			   		<td>  <label class="field"  for="county">County</label></td>
					<!-- <td><input type="text" id="county" name="county"></input></td>  -->
					<td> 
		   				<select id="county" name="county" style="width:138px;" > 
							<c:forEach var="list" items="${countyLookup}">
									<option value="${list.getCodeDesc()}"  ${list.getCodeDesc().equalsIgnoreCase(caseWorker.getCounty()) ? 'selected' : ''} >${list.getCodeDesc()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				 
				  <tr>
					<td> <label class="field"  for="term_id">Term ID</label></td>
					<td> <input type="text" id="term_id" name="term_id"  value=${caseWorker.getTerm_id()}></input></td>
			   		<td> <label class="field"  for="printer_id">Printer ID</label></td>
			   		<td> <input type="text" id="printer_id" name="printer_id" value=${caseWorker.getPrinter_id()} ></input></td>
			   	 </tr>
				 
				 <tr>
				 	<td> <label class="field"  for="caseloadtotal">Case Load Total</label></td> 
			   		<td> <input type="text" id="caseloadtotal" name="caseloadtotal" value=${caseWorker.getCaseloadtotal()}></input></td>
			   		<td> <label class="field"  for="nivdTotal">NIVD Total</label></td> 
			   		<td> <input type="text" id="nivdTotal" name="nivdTotal" value=${caseWorker.getNivdTotal()}></input></td>
				</tr>
				 
				 <tr>
				 	<td> <label class="field"  for="supervisor">Supervisor</label></td>
				 	<td><input type="text" id="supervisor" name="supervisor" value=${caseWorker.getSupervisor()}></input></td>
				 </tr>
			 
			 	 
				 <tr>
				   	<td><label class="field"  for="streetAddress1">street Address 1</label></td>
				 	<td> <input type="text" id="streetAddress1" name="streetAddress1" value=${caseWorker.getStreetAddress1()}></input></td>
				  </tr>
				   <tr>
				 	<td><label class="field"  for="streetAddress2">street Address 2</label></td>
				 	<td> <input type="text" id="streetAddress2" name="streetAddress2" value=${caseWorker.getStreetAddress2()}></input></td>
				 </tr>
				 
				 <tr>
			 	 	<td> <label class="field"  for="city">City</label></td>
			   		<td> <input type="text" id="city" name="city" value=${caseWorker.getCity()}></input></td>
		    		<td> <label class="field"  for="state">State</label> </td>
			   		<td> <input type="text" id="state" name="state" value=${caseWorker.getState()}></input></td>
			   		<td> <label class="field"  for="zipcode">Zip Code</label></td>
			   		<td> <input type="text" id="zipcode" name="zipcode" value=${caseWorker.getZipcode()}></input></td>
				 </tr>
				 <tr>
				<td><hr></td>
				 </tr>
				 <tr>
				 	 
			 	 	<td> <label class="field"  for="phone_num">Phone #</label></td>
			   		<td> <input type="text" id="phoneACD" name="phoneACD" size="3" maxlength="3"  value=${caseWorker.getPhoneACD()}></input>
			   			 <input type="text" id="phoneEXC" name="phoneEXC" size="3"  maxlength="3" value=${caseWorker.getPhoneEXC()}></input>
			   			 <input type="text" id="phoneLN" name="phoneLN"   size="4"  maxlength="4"  value=${caseWorker.getPhoneLN()}>
			   		</td>
			   		 
		    		<td> <label class="field"  for="phone_ext">EXT#</label> </td>
			   		<td> <input type="text" id="phone_ext" name="phone_ext" size="6" maxlength="6" value=${caseWorker.getPhone_ext()}></input></td>
			   	</tr>
			   	<tr>	
			   		<td> <label class="field"  for="fax">Fax</label></td>
			   		<td> <input type="text" id="faxACD" name="faxACD"  size="3" maxlength="3" value=${caseWorker.getFaxACD()}></input>
			   			 <input type="text" id="faxEXC" name="faxEXC"  size="3" maxlength="3" value=${caseWorker.getFaxEXC()}></input>
			   			 <input type="text" id="faxLN" name="faxLN"  size="4"  maxlength="4"  value=${caseWorker.getFaxLN()}></input>
			   		</td>
			   		 
				 </tr>
				 
				 <tr>
				 <td><hr></td>
				 </tr>
							 
				 <tr>
				 	<td> <label class="field"  for="mfaEnabled">MFA Enabled</label></td>
				 	<td>
				 <select id="mfaSelect" name="mfacriteria"> 
					 <c:forEach var="list" items="${mfaSelectList}">
								<option value="${list}"  ${list.equalsIgnoreCase(caseWorker.getMfacriteria()) ? 'selected' : ''} >${list}</option>
						</c:forEach>
				 </select>
				</td> 
					 
					
					<td> <label class="field"  for="ncid">NCID</label></td> 
			   		<td> <input type="text" id="ncid" name="ncid" value=${caseWorker.getNcid()}  ></input> </td>
					<td> <label class="field"  for="racfid">RACFID</label></td>
			   		<td> <input type="text" id="racfid" name="racfid" value=${caseWorker.getRacfid()}></input></td>
					</tr>
					
					<tr>
				 		<td><label class="field"  for="actsSSPRole">ACTS SSP Role</label></td>
			   		 	<td><input type="text" id="actsSSPRole" name="actsSSPRole"  value=${caseWorker.getActsSSPRole()}  ></input></td>
						<td><label class="field"  for="ocseSSPRole">OCSE SSP Role</label></td>
			   		 	<td><input type="text" id="ocseSSPRole" name="ocseSSPRole"  value=${caseWorker.getOcseSSPRole()}></input></td>
				 	</tr>
				 	
				 	<tr>
						<td> <label class="field"  for="dateHired">Date Hired</label></td>
				   		<td> <input type="text" id="dateHired" name="dateHired" value=${caseWorker.getDateHired()}></input></td>
						 
						
				 </tr>
				 
				 <tr>
				 		<td><hr></td>
				 		<td><label class="normalHoursField"  for="NormalHours">Normal Working Hours:</label></td>
				 		<td><hr></td>
				 </tr>
				 <tr>
				 		<td> <label class="field"  for="startTime">Start</label></td>
				   		<td> <input type="text" id="startTime" name="startTime" value='${caseWorker.getStartTime()}' ></input></td>
				 </tr>
				 <tr>
				 		<td> <label class="field"  for="startLunchTime">Lunch Begin</label></td>
				   		<td> <input type="text" id="startLunchTime" name="startLunchTime" value='${caseWorker.getStartLunchTime()}' ></input></td>
				 
				 		<td> <label class="field"  for="endLunchTime">Lunch End</label></td>
				   		<td> <input type="text" id="endLunchTime" name="endLunchTime" value='${caseWorker.getEndLunchTime()}'></input></td>
				 </tr>
				 <tr>
				 		<td> <label class="field"  for="endTime">End</label></td>
				   		<td> <input type="text" id="endTime" name="endTime" value='${caseWorker.getEndTime()}'></input></td>
				 </tr>
				 <tr>
				 		<td> <label id="hideLupdatedateLabel" class="field"  for="lastUpdateDate">Last Updated </label></td>
				   		<td> <input id="hideLupdatedateText" type="text" id="lastUpdateDate" name="lastUpdateDate" value= ${caseWorker.getLastUpdateDate()}></input> </td>
				   		<td> <input id="hideLupdatetimeText" type="text" id="lastUpdateTime" name="lastUpdateTime" value= ${caseWorker.getLastUpdateTime()}></input> </td> 
				   		
				   		<td> <label id="hideLupdateByLabel" class="field"  for="lastUpdateBy">By Worker</label></td>
				   		<td> <input id="hideLupdateByText" type="text" id="lastUpdateBy" name="lastUpdateBy" value= ${caseWorker.getLastUpdateBy()}></input> </td>
				 </tr>
		 </table>
		
				<br>
			 <div>
				<table width="70%" border="0">
					<tr>
					<td align="center"><input type=button id="createDB" value='Submit'></td>
					<td align="center"><input id="resetbtn" type=button value='Reset'></td>
					<td align="center"><input type=button value='Previous'	onclick="location.href='${pageContext.request.contextPath}/cswp/user/listCaseWorker.htm';"  /></td>
					 
					</tr>
				</table>
			 
			</div>
	</div> <!--end div gutter2 -->  
</div> <!--end div contentTwo --> 
</form>