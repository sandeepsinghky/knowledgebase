<%@ include file="../../../include/taglib.jsp"%>
<script>

	$("#OnlineAppSearch").on("submit", function () {
    	$(this).find(":submit").prop("disabled", true);
     	$(this).find(":button").prop("disabled", true);
	});
	
	function toggle(source) {
	    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	    for (var i = 0; i < checkboxes.length; i++) {
	        if (checkboxes[i] != source){
	            if(!checkboxes[i].disabled){
	            	checkboxes[i].checked = source.checked;
	            }
	        }
	    }
	}
	
	function processChkbxSel(source) {
	   if (!source.checked) {
	   		document.getElementById('selAll').checked = source.checked;
	   }
	}
	

	function checkForUpdates() {
		var update = false;
		var disabled = true;
		var dataUpdated = true;
		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		
		for (var i = 0; i < checkboxes.length; i++) {
		    if(checkboxes[i].id != 'selAll')
		    {
		    	if(!checkboxes[i].disabled && checkboxes[i].checked){
			    	update = true;
			    	dataUpdated = false;
			    	disabled = false;
			    	break;
			    }else if(!checkboxes[i].disabled){
			    	if (disabled){
			    		disabled = false;
			    		dataupdated = false;
			    	}
			    }
		    }
		}
		if (disabled && dataUpdated){
			alert("Participant already updated");
		    return false;
		}else if(disabled && !dataUpdated && !update){
			alert("No data to update.");
		    return false;
		}else if(!update){
		    alert("Select data to update.");
		    return false;
		}
		//return true;
		document.updateActsPart.submit();
		buttonsDisabled(true);
	}	
</script>

	<form name="updateActsPart" id="updateActsPart" method="post" action="${pageContext.request.contextPath}/cswp/user/caseApplicationPartUpdate.htm">
		
		<input type="hidden" name="action" id="action" value="">
		
		<div id="contentTwo">
			<div class="gutter2">
				<div class="box">
					<h2>Participant Demographic Data</h2>
					<hr>
				</div>
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>

				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
				
				<h3>Participant MPI Number: ${partInfo.getPartId()}</h3>
				
				
				<p><font color="red">Select demographic data to update ACTS participant.</font></p>
				<div>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<th colspan='2'>&nbsp;</th>
										<th><input type="checkbox" name="selAll" id="selAll" onclick='toggle(this)'><b>Select All</b></th>
										<th colspan='2'>&nbsp;</th>
										<th>&nbsp;</th>
										<th colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;</th>
										<th>&nbsp;</th>
										<th colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;</th>
										<th>ACTS System Updated</th>
									</tr>
									<tr><td colspan='20'><hr></td></tr>
									<tr>
            							<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="name" value="name"  ${partDemo.get("NAME") !=null && !partDemo.get("NAME").equals('') ? 'disabled checked':''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">PARTICIPANT NAME:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>${partInfo.getApplicantFNm()} ${partInfo.getApplicantLNm()}</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("NAME") != null ? partDemo.get("NAME") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="ssn" value="ssn" ${partInfo.getSsnNb().equals('') ? 'disabled': partDemo.get("SSN") !=null && !partDemo.get("SSN").equals('') ? 'disabled checked' : ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">SSN:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>
                    						<c:if test="${not empty partInfo.getSsnNb()}">
                    							${partInfo.getSsnNb().substring(0, 3)}-${partInfo.getSsnNb().substring(3, 5)}-${partInfo.getSsnNb().substring(5, 9)}                   					
                    						</c:if>
                    					</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("SSN") != null ? partDemo.get("SSN") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="dob" value="dob" ${partInfo.getBrthDtStr().equals('') ? 'disabled': partDemo.get("DOB") !=null && !partDemo.get("DOB").equals('') ? 'disabled checked' : ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">DOB:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>${partInfo.getBrthDtStr()}</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("DOB") != null ? partDemo.get("DOB") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="race" value="race" ${partInfo.getEthncGrpDesc().equals('') ? 'disabled': partDemo.get("RACE") !=null && !partDemo.get("RACE").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">RACE:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>${partInfo.getEthncGrpDesc()}</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("RACE") != null ? partDemo.get("RACE") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="gndr" value="gndr" ${partInfo.getApplicantGenderDesc().equals('') ? 'disabled': partDemo.get("GENDER") !=null && !partDemo.get("GENDER").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">SEX:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>${partInfo.getApplicantGenderDesc()}</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("GENDER") != null ? partDemo.get("GENDER") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="lang" value="lang" ${partInfo.getEthncGrpDesc().equals('') ? 'disabled': partDemo.get("LANG") !=null && !partDemo.get("GENDER").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">LANGUAGE:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>${partInfo.getLangPrefDesc()}</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("LANG") != null ? partDemo.get("LANG") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="hph" value="hph" ${empty hPh ? 'disabled': partDemo.get("HOME PHONE") !=null && !partDemo.get("HOME PHONE").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">HOME PHONE:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>
                    						<c:if test="${not empty hPh}">
                    							${hPh.substring(0, 3)}-${hPh.substring(3, 6)}-${hPh.substring(6, 10)}                   					
                    						</c:if>
                    					</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("HOME PHONE") != null ? partDemo.get("HOME PHONE") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td><input type=checkbox name="wph" value="wph" ${empty hPh ? 'disabled': partDemo.get("WORK PHONE") !=null && !partDemo.get("WORK PHONE").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left">WORK PHONE:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>
                    						<c:if test="${not empty wPh}">
                    							${wPh.substring(0, 3)}-${wPh.substring(3, 6)}-${wPh.substring(6, 10)}                   					
                    						</c:if>
                    					</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("WORK PHONE") != null ? partDemo.get("WORK PHONE") : ''}</td>
                  					</tr>
                  					<tr><td colspan='20'>&nbsp;</td></tr>
                  					<tr>
                    					<td colspan='2'>&nbsp;</td>
                    					<td valign="top"><input type=checkbox name="addr" value="addr" ${empty mailAddr ? 'disabled': partDemo.get("ADDRESS") !=null && !partDemo.get("ADDRESS").equals('') ? 'disabled checked': ''} onclick="processChkbxSel(this)"></td>
                    					<td colspan='2'>&nbsp;</td>
                    					<td align="left" valign="top">ADDRESS:</td>
                    					<td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    					<td>
                    						<c:if test="${not empty mailAddr}">
                    							<c:if test="${not empty mailAddr.getAddrLn1()}">
                    								${mailAddr.getAddrLn1()}<br>	
                    							</c:if>
                    							<c:if test="${not empty mailAddr.getAddrCty()}">
                    								${mailAddr.getAddrCty()}
             									</c:if> 
                    							<c:if test="${not empty mailAddr.getAddrSt()}">
                    								${mailAddr.getAddrSt()}
                    							</c:if> 
                    							<c:if test="${not empty mailAddr.getAddrZip5()}">
                    								${mailAddr.getAddrZip5()}
                    							</c:if>              					
                    						</c:if>
                    					</td>
                    					<td colspan='6'>&nbsp;</td>
                    					<td align="center">${partDemo.get("ADDRESS") != null ? partDemo.get("ADDRESS") : ''}</td>
                  					</tr>
                  					
								</table>
							</td>							
						</tr>
						<table>					
						<br><br>
						<table>
							<tr>
								<td align="right"><input type="button" title="Selected demographic data will update in ACTS" name="update" id="update" value="Update ACTS Participant" onclick="return checkForUpdates()"></td>
								<td align="right"><input type="button" name="moreinfo" id="moreinfo" title="View additional demographic information." value="More Information" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationPartMiscInfo.htm'"></td>
          						<td><input type="button" name="back" value="Back To Application Participant List" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationPartList.htm'"></td>								
							</tr>
						</table>
				</div>
				<br>
			</div>
		</div>
	</form>

