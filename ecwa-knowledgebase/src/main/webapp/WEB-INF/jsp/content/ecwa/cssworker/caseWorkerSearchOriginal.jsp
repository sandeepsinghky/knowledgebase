<%@ include file="../../../include/taglib.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	
	
			hideAll ();
			 $("#errorCaseWorker").hide();
		 
			$('#resetbtn').click(function() {
				 $("#error-message-box").hide();
				 $('#selectCaseWorkerSrch').val("");
				 $("#errorCaseWorker").hide();
				hideAll ();
		    });
		
			$('#submitbtn').click(function() {
				var errorMessage =validateInput();
				if (errorMessage==""){
					$('#searchworker').submit();
				}
		    });
		
			$("#selectCaseWorkerSrch").on("change",function() {
				hideAll ();
				 $("#errorCaseWorker").hide();
				 
				if (this.value=="RACFID"){
					$('#d').add();
					$('#RACF_ID').show();
					$('label[for="RACF_ID"]').show(); 
					 
				} else if (this.value=="WORKERNUM"){
					
					$('#WORKER_NUM').show();
					$('label[for="WORKER_NUM"]').show();
					
				} else if(this.value=="COUNTY"){
					
					$('#COUNTY_ID').show();
					$('label[for="COUNTY_ID"]').show();
					
				} else if(this.value=="COUNTY_LNAME"){
					
					$('#COUNTY_LNAME_LASTNAME').show();
					$('label[for="COUNTY_LNAME_LASTNAME"]').show();
					$('#COUNTY_LNAME_COUNTY_ID').show();
					$('label[for="COUNTY_LNAME_COUNTY_ID"]').show();
					
				} else if(this.value=="LNAME"){
					$('#LNAME').show();
					$('label[for="LNAME"]').show();
					
				} else if(this.value=="LNAME_FNAME"){
					$('#LNAME_FNAME_LASTNAME').show();
					$('label[for="LNAME_FNAME_LASTNAME"]').show();
					$('#LNAME_FNAME_FIRSTNAME').show();
					$('label[for="LNAME_FNAME_FIRSTNAME"]').show();
				}
		
				});
	 
});

			function validateInput(){
				 
				var selectedOption=$('#selectCaseWorkerSrch option:selected');
				var selectValue=selectedOption.val();
				var errorMessage="";
				
						if (selectValue==""){
							errorMessage="Serach Criteria not Selected";
							 
						} else{
							 
								if (selectValue=="RACFID"){
									 
									if ($('#RACF_ID').val() ==""){
										errorMessage="Please Enter RACF ID";
									}
								} else if (selectValue=="WORKERNUM"){
								 
									if ($('#WORKER_NUM').val() ==""){
										errorMessage="Please Enter Worker #";
									}
								} else if (selectValue=="COUNTY"){
									 
									if ($('#COUNTY_ID').val() ==""){
										errorMessage="Plesae Enter County";
									}
								} else if (selectValue=="COUNTY_LNAME"){
									
									var countId=$('#COUNTY_LNAME_COUNTY_ID').val();
									var lastName=$('#COUNTY_LNAME_LASTNAME').val();
									if ((countId =="") || (lastName =="")) {
										errorMessage="Plesae Enter County and Last Name";
									}
								}else if (selectValue=="LNAME"){
								 
									if ($('#LNAME').val() =="")  {
										errorMessage="Plesae Enter Last Name";
									}
								}else if (selectValue=="LNAME_FNAME"){
									
									if(($('#LNAME_FNAME_LASTNAME').val() =="") || ($('#LNAME_FNAME_FIRSTNAME').val() =="")) {
										errorMessage="Plesae Enter Last and First Name";	
									 
									}
								}
						}  
						if (errorMessage!=""){
							$('#errorCaseWorker').html( errorMessage);
							$("#errorCaseWorker").show();
						}
						return errorMessage;
			} 

			function hideAll () {
				$('label').hide();  
				$('input[type="text"]').hide();     
				$("#COUNTY_ID").hide();	
				$("#COUNTY_LNAME_COUNTY_ID").hide();	
			}


</script>


<form name="SerchworkerForm" id="searchworker" method="post"  action="${pageContext.request.contextPath}/cswp/user/listCaseWorker.htm">
 
<div id="contentTwo">
	<div class="gutter2">
		
		<div id="box">
			<h2>Search Case Worker</h2>
			 <div id="errorCaseWorker"></div>
			 <c:if test="${!empty message}">
			<div id="error-message-box" class="error message-box">${message}</div>
		</c:if>
			<hr>
		</div><!--end div box --> 
		 <div>
		 	 <table>
		 	
		 	 <tr>
		 	 	<td class="field1" >Select Search Criteria</td>
		 	 	<td>
		 	 		<select id="selectCaseWorkerSrch" name="selectcriteria"> 
			 		<option value=""></option>
			  		<option value="RACFID">By RACF ID</option>
			  		<option value="WORKERNUM">By Worker #</option>
			  		<option value="COUNTY">By County</option>
			  		<option value="COUNTY_LNAME">By County and Last Name</option>
			  		<option value="LNAME">By  Last Name</option>
			  		<option value="LNAME_FNAME">By  Last Name and First Name</option>
					</select>
		 	 	</td>
		 	 </tr>
		 	 <tr>
		 	 	 <td > <label class="field1"  for="RACF_ID">Enter RACF ID</label></td>
			 	 <td> <input type="text" id="RACF_ID" name="RACF_ID"></input></td>
			 </tr> 
			 
			 <tr>
			 	 <td> <label class="field1"  for="WORKER_NUM">Enter Worker#</label></td>
			 	 <td> <input type="text" id="WORKER_NUM" name="WORKER_NUM" maxlength="8"></input></td>
			 </tr> 
			 
			 <tr>
			 	 <td> <label class="field1"  for="COUNTY_ID">Enter County</label></td>
			 	 <!-- <td> <input type="text" id="COUNTY_ID" name="COUNTY_ID"></input></td> -->
			 	 <td> 
		   				<select id="COUNTY_ID" name="COUNTY_ID" style="width:138px;" > 
							<c:forEach var="list" items="${countyLookup}">
									<option value="${list.getCodeId()}"  ${list.getCodeDesc().equalsIgnoreCase(caseWorker.getCounty()) ? 'selected' : ''} >${list.getCodeDesc()}</option>
							</c:forEach>
						</select>
				</td>
			 </tr> 
			 
			 <tr>
			 	 <td> <label class="field1" for="COUNTY_LNAME_COUNTY_ID">Enter County</label></td>
			 	<!--  <td> <input type="text" id="COUNTY_LNAME_COUNTY_ID" name="COUNTY_LNAME_COUNTY_ID"></input></td> -->
			 	 <td> 
		   				<select id="COUNTY_LNAME_COUNTY_ID" name="COUNTY_LNAME_COUNTY_ID" style="width:138px;" > 
							<c:forEach var="list" items="${countyLookup}">
									<option value="${list.getCodeDesc()}"  ${list.getCodeDesc().equalsIgnoreCase(caseWorker.getCounty()) ? 'selected' : ''} >${list.getCodeDesc()}</option>
							</c:forEach>
						</select>
				</td>
			 	 <td> <label class="field1" for="COUNTY_LNAME_LASTNAME">Enter Last Name</label></td>
			 	 <td> <input type="text" id="COUNTY_LNAME_LASTNAME" name="COUNTY_LNAME_LASTNAME" maxlength="17"></input></td>
			  </tr>	 
			 
			  <tr>
			 	 <td> <label class="field1" for="LNAME">Enter Last Name</label></td>
			 	 <td> <input type="text" id="LNAME" name="LNAME" maxlength="17"></input></td>
			 </tr>	
			 
			  <tr> 
			 	 <td> <label class="field1" for="LNAME_FNAME_LASTNAME">Enter Last Name</label></td>
			   	 <td> <input type="text" id="LNAME_FNAME_LASTNAME" name="LNAME_FNAME_LASTNAME" maxlength="17"></input></td>
			   	 
			   	 <td> <label class="field1" for="LNAME_FNAME_FIRSTNAME">Enter First Name</label></td>
			   	 <td> <input type="text" id="LNAME_FNAME_FIRSTNAME" name="LNAME_FNAME_FIRSTNAME" maxlength="15"></input></td>
		 	 </tr>
			
		 	 </table>
		 </div>
		
			<br>
			 <div>
				<table width="70%" border="0">
					<tr>
						<td align="left"><input id="submitbtn" type=button value='Submit'></td>
						<td align="left"><input id="resetbtn" type=button value='Reset'></td>			 
					</tr>
				</table>
			 
			</div> <!--end div table --> 
			
	</div> <!--end div gutter2 --> 
</div> <!--end div contentTwo --> 
</form>