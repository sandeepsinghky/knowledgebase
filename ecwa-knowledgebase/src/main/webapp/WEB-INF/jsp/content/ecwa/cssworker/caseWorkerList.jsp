<%@ include file="../../../include/taglib.jsp"%>
	 
<script type="text/javascript">
$(document).ready(function() {
	$("#errorCaseWorker").hide();
	
	$("#caseworkerdataTable").dataTable( {
		 "paging": true,
		 "stripeClasses": [],
		 "bSort": true  
		 
		 
		 
	    } );
	
	$('#deleteWorker').click(function() {
		var retValue=validateSelection();
		if (retValue==""){
		 	$("#action").val("delete");
			$('#caseWorkerList').submit();
		}
		 
    });
	
	$('#createWorker').click(function() {
	 		$("#action").val("create");
 			$('#caseWorkerList').submit();
    });
	
	$('#updateWorker').click(function() {
	var retValue=validateSelection();
		if (retValue==""){
		 	$("#action").val("update");
			$('#caseWorkerList').submit();
		}
		 
    });
	
	$('#viewWorker').click(function() {
		var retValue=validateSelection();
			if (retValue==""){
			 	$("#action").val("inquiry");
				$('#caseWorkerList').submit();
			}
			 
	});
	
	
});
	
function validateSelection(){
	var errorValue=""; 
	var selValue = $('input[name=selectCaseWorker]:checked').val(); 
	 
	
	if ((selValue=="") || (selValue==null)){
	 
		errorValue="Please Select Case Worker";
	} 
	if (errorValue!=""){
		$('#errorCaseWorker').html("<ul>"+ errorValue +"</ul>");
		$("#errorCaseWorker").show();
	}
	 return errorValue;

}

function populateSelectedValues(){
}
</script>
	 
	 
	
	
	<form name="caseWorkerList" id="caseWorkerList" method="post"	action="${pageContext.request.contextPath}/cswp/user/modfiyCaseWorker.htm">
	<input type="hidden" name="action" id="action" value=""> 
	<input type="hidden" name="caseWorkerLastName" value="">
	
		<div id="contentTwo">
			<div class="gutter2">
				<h2>Case Worker List</h2>
				<c:if test="${!empty success}">
					<div class="success message-box">${success}</div>
				</c:if>
				<div id="errorCaseWorker"></div>
				<div style="height:500px; overflow-y: scroll;"> 
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
				
				<div>
						<table width="70%" border="0">
							<tr>
							<td align="center"><input type=button id="createWorker" value='Create Worker'></td>
							<td align="center"><input type=button id="deleteWorker" value='Delete Worker'/></td>
							<td align="center"><input type=button id="updateWorker" value='Update Worker' /></td>
							<td align="center"><input type=button id="viewWorker" value='View Worker' /></td>
							<td align="center"><input type=button id="Previous" value="Previous" onclick="location.href='${pageContext.request.contextPath}/cswp/user/searchWorker.htm';" /></td>
							</tr>
						</table>
				</div>
				
			</div>
		</div>		
	</form>
	
	
 