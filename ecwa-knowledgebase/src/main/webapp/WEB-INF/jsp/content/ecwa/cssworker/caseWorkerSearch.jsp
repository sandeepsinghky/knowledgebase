<%@ include file="../../../include/taglib.jsp"%>


<script type="text/javascript">
$(document).ready(function() {
	
	  
			hideAll ();
			$(".errorCaseWorker").hide();
			$(".errorCaseWorkerList").hide();
		    checkFirstLoad (); 
		    
		   
		    checkListToHideShow();
		    
			$("#caseWorkerTableDiv").css({ "height":"500px","width":"95%","padding-left": "10px"});
			
			$('#resetbtn').click(function() {
				 $("#error-message-box").hide();
				 $('#selectCaseWorkerSrch').val("");
				 $(".errorCaseWorker").hide();
				hideAll ();
		    });
		
			$('#searchbtn').click(function() {
				var errorMessage =validateInput();
				if (errorMessage==""){
					$('#searchworker').submit();
				}
		    });
		
			$("#selectCaseWorkerSrch").on("change",function() {
				hideAll ();
				 $(".errorCaseWorker").hide();
				 
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
			
		 
			
			$("#caseworkerdataTable").dataTable( {
				 "fixedHeader": {
				        "header": true,
				    },
				 "paging": true,
				 "stripeClasses": [],
				 "bSort": false  
			 } );

			
		$("#deleteWorker").click(function() {
				var retValue=validateSelection();
				if (retValue==""){
					$.confirm({
						boxWidth : '30%',
						type: 'blue',
						useBootstrap : false,
						title : 'Confirm Delete!',
						content : 'Please confirm if you want to delete this Case Worker!',
						buttons : {
							 
								Delete: function() {
									alert("delete");
									$("#action").val("delete");
									$('#caseWorkerList').submit();
	
								},
								cancel: function() {
									 
								} 
							 
						}
					});
				}else{
					$("#action").val("delete");
					$('#caseWorkerList').submit();
				}
		});

		$('#createWorker').click(function() {
			$("#action").val("create");
			$('#caseWorkerList').submit();
		});

		$('#updateWorker').click(function() {
			var retValue = validateSelection();
			if (retValue == "") {
				$("#action").val("update");
				$('#caseWorkerList').submit();
			}

		});

		$('#viewWorker').click(function() {
			var retValue = validateSelection();
			if (retValue == "") {
				$("#action").val("inquiry");
				$('#caseWorkerList').submit();
			}

		});

	});

	function checkFirstLoad() {
		var initLoad = '${initLoadSerchPage}';
		if (initLoad == "init") {
			$("#caseWorkerList").hide();
		} else {
			$("#caseWorkerList").show();
		}
	}

	function checkListToHideShow() {
		var caseList = '${caseWorkerList}';
		var caseListLength = caseList.length;
		if (caseListLength == 0) {
			$("#caseWorkerList").hide();
		} else {
			$("#caseWorkerList").show();
		}

	}

	function validateInput() {

		var selectedOption = $('#selectCaseWorkerSrch option:selected');
		var selectValue = selectedOption.val();
		var errorMessage = "";

		if (selectValue == "") {
			errorMessage = "Serach Criteria not Selected";

		} else {

			if (selectValue == "RACFID") {

				if ($('#RACF_ID').val() == "") {
					errorMessage = "Please Enter RACF ID";
				}
			} else if (selectValue == "WORKERNUM") {

				if ($('#WORKER_NUM').val() == "") {
					errorMessage = "Please Enter Worker #";
				}
			} else if (selectValue == "COUNTY") {

				if ($('#COUNTY_ID').val() == "") {
					errorMessage = "Plesae Enter County";
				}
			} else if (selectValue == "COUNTY_LNAME") {

				var countId = $('#COUNTY_LNAME_COUNTY_ID').val();
				var lastName = $('#COUNTY_LNAME_LASTNAME').val();
				if ((countId == "") || (lastName == "")) {
					errorMessage = "Plesae Enter County and Last Name";
				}
			} else if (selectValue == "LNAME") {

				if ($('#LNAME').val() == "") {
					errorMessage = "Plesae Enter Last Name";
				}
			} else if (selectValue == "LNAME_FNAME") {

				if (($('#LNAME_FNAME_LASTNAME').val() == "")
						|| ($('#LNAME_FNAME_FIRSTNAME').val() == "")) {
					errorMessage = "Plesae Enter Last and First Name";

				}
			}
		}
		if (errorMessage != "") {
			$('.errorCaseWorker').html(errorMessage);
			$(".errorCaseWorker").show();
		}
		return errorMessage;
	}

	function hideAll() {
		$('label').hide();
		$('input[type="text"]').hide();
		$("#COUNTY_ID").hide();
		$("#COUNTY_LNAME_COUNTY_ID").hide();
	}

	function validateSelection() {
		var errorValue = "";
		var selValue = $('input[name=selectCaseWorker]:checked').val();

		if ((selValue == "") || (selValue == null)) {

			errorValue = "Please Select Case Worker";
		}
		if (errorValue != "") {
			$('#errorCaseWorkerList').html("<ul>" + errorValue + "</ul>");
			$("#errorCaseWorkerList").show();
		}
		return errorValue;

	}
</script>


<form name="SerchworkerForm" id="searchworker" method="post"  action="${pageContext.request.contextPath}/cswp/user/listCaseWorker.htm">
 
<div id="contentTwo">
	<div class="gutter2">
		
		<div id="box">
			<h2>Search Case Worker</h2>
			 <div id="errorCaseWorker" class="errorCaseWorker"></div>
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
		 	  
		 	 	 <td><a href="#0" class="cssButton search" id="searchbtn">Search Worker</a></td>
		 	 	<td></td>
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
		
		 
			 
			
	</div> <!--end div gutter2 --> 
</div> <!--end div contentTwo --> 
</form>

<form name="caseWorkerList" id="caseWorkerList" method="post"	action="${pageContext.request.contextPath}/cswp/user/modfiyCaseWorker.htm">
	<input type="hidden" name="action" id="action" value=""> 
	<input type="hidden" name="caseWorkerLastName" value="">
	
		<div id="contentTwo">
			<div class="gutter2">
				<h2>Case Worker List</h2>
				<div id="errorCaseWorkerList" class="errorCaseWorkerList"></div>
				<c:if test="${!empty success}">
					<div class="success message-box">${success}</div>
				</c:if>
				 <div id="cwListButtons" style="padding-bottom: 15px;">
				 	<table width="70%" border="0">
							<tr>
							<!-- <td align="center"><input class= "cssButton add" type=button id="createWorker" value='Create Worker'></td> -->
							<td><a href="#0" class="cssButton add" id="createWorker">Add Worker</a></td>
							<!-- <td align="center"><input type=button id="deleteWorker" value='Delete Worker'/></td> -->
							<td><a href="#0" class="cssButton delete" id="deleteWorker">Delete Worker</a></td>
							<!-- <td align="center"><input type=button id="updateWorker" value='Update Worker' /></td> -->
							<td><a href="#0" class="cssButton edit" id="updateWorker">Update Worker</a></td>
							<!-- <td align="center"><input type=button id="viewWorker" value='View Worker' /></td> -->
							<td><a href="#0" class="cssButton next" id="viewWorker">View Worker</a></td>
							<%-- <td align="center"><input type=button id="Previous" value="Previous" onclick="location.href='${pageContext.request.contextPath}/cswp/user/searchWorker.htm';" /></td> --%>
							</tr>
						</table>
				 </div>
				<div  id="caseWorkerTableDiv"> 
						<table id="caseworkerdataTable" class="caseworkerdataTable" border="0">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<!-- <th>Select</th> -->
								<th>Last Name</th>
								<th>First Name</th>
								<th>MI</th>
								<th>Worker #</th>
								<th>Worker Type</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var="list" items="${caseWorkerList}" varStatus="tc">
							 		<tr>
								 		<%-- <td>${tc.count}.</td> --%>
								 		<td>
								 			<input type="radio" name="selectCaseWorker" id="selectCaseWorker" value="${list.getWorker_num()}">
								 			<input type="hidden" name="${list.getWorker_num()}_caseWorkerLastName" id="${list.getWorker_num()}_caseWorkerLastName" value="${list.getLast_name()}">
								 			<input type="hidden" name="${list.getWorker_num()}_caseWorkerFirstName" id="${list.getWorker_num()}_caseWorkerFirstName" value="${list.getFirst_name()}">
								 			<input type="hidden" name="${list.getWorker_num()}_caseWorkerMiddleName" id="${list.getWorker_num()}_caseWorkerMiddleName" value="${list.getMiddle_name()}">
								 			<input type="hidden" name="${list.getWorker_num()}_caseWorkerType" id="${list.getWorker_num()}_caseWorkerType" value="${list.getWorker_type()}">
								 		</td>
										<td>${list.getLast_name()}</td>
										<td>${list.getFirst_name()}</td>
										<td>${list.getMiddle_name()}</td> 
										<td>${list.getWorker_num()}</td>
										<td>${list.getWorker_type()}</td>
									</tr>
								</c:forEach>
						</tbody>
						</table>
				</div>
				
				 
				
			</div>
		</div>		
	</form>