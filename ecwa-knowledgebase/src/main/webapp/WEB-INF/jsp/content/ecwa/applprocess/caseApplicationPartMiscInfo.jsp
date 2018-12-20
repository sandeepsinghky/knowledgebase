<%@ include file="../../../include/taglib.jsp"%>
<script>

</script>

	<form name="applicationMisc" id="applicationMisc">
		
		<div id="contentTwo">
			<div class="gutter2">
				<div class="box">
					<h2>Additional Information</h2>
					<hr><br>
					<h3>&nbsp;${partInfo.getApplicantFNm()}&nbsp;${partInfo.getApplicantLNm()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MPI Number: ${partInfo.getPartId()}</h3>
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
								<table>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>CELL PHONE:</td>
										<td colspan='2'>&nbsp;</td>
										<td>
											<c:if test="${not empty cPh}">
	                    							${cPh.substring(0, 3)}-${cPh.substring(3, 6)}-${cPh.substring(6, 10)}                   					
	                    						</c:if>
										</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>EMAIL:</td>
										<td colspan='2'>&nbsp;</td>
										<td>${email}</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>EMPLOYER:</td>
										<td colspan='2'>&nbsp;</td>
										<td>
											<c:if test="${(not empty empInfo) && empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('2') || empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('4')}">
                    							<c:if test="${not empty empInfo.getThirdPartyInfo().getAddressLn1()}">
                    								${empInfo.getThirdPartyInfo().getAddressLn1()}<br>	
                    							</c:if>
                    							<c:if test="${not empty empInfo.getThirdPartyInfo().getThirdPartyCity()}">
                    								${empInfo.getThirdPartyInfo().getThirdPartyCity()}
             									</c:if> 
                    							<c:if test="${not empty empInfo.getThirdPartyInfo().getThirdPartyState()}">
                    								${empInfo.getThirdPartyInfo().getThirdPartyState()}
                    							</c:if> 
                    							<c:if test="${not empty empInfo.getThirdPartyInfo().getThirdPartyZip5()}">
                    								${empInfo.getThirdPartyInfo().getThirdPartyZip5()}
                    							</c:if>              					
                    						</c:if>
										</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>ASSETS:</td>
										<td colspan='2'>&nbsp;</td>
										<td><c:if test="${(not empty empInfo)}">$</c:if> ${assets}</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>PROTECT:</td>
										<td colspan='2'>&nbsp;</td>
										<td>
										${!partInfo.getProtOrd().equals('') ? partInfo.getProtOrd().equals('1') || partInfo.getProtOrd().equals('2') ? 'Y' : 'N' : ''}
										</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td>SPECIAL ASSISTANCE:</td>
										<td colspan='2'>&nbsp;</td>
										<td>
										${!partInfo.getSpecialAssist().equals('3') ? partInfo.getSpecialAssistStr(): partInfo.getSpecialAssistOt()}
										</td>
										<td colspan='3'>&nbsp;</td>
									</tr>
								</table>
								<table>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td align="left" colspan='7'>HEALTH CARE INFO:</td>
									</tr>
									<c:if test="${not empty chldInsurnceMap}">
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td colspan='7'>
											<table border="1">
												<tr>
													<th>Insurance</th>
													<th>Insurance Provider</th>
													<th>Policy Holder First Name</th>
													<th>Policy Holder Last Name</th>
													<th>Policy Holder Relationship</th>
												</tr>
												<c:forEach var="insCode" items="${chldInsurnceMap.keySet()}">
												<tr>
													<td>${chldInsurnceMap.get(insCode).getInsuranceTypeStr()}</td>
													<td>${chldInsurnceMap.get(insCode).getInsuranceProvider()}</td>
													<td>${chldInsurnceMap.get(insCode).getInsHolderF()}</td>
													<td>${chldInsurnceMap.get(insCode).getInsHolderL()}</td>
													<td>${chldInsurnceMap.get(insCode).getInsHolderRelshpStr()}</td>
												</tr>
												</c:forEach>				
											</table>
										</td>
									</tr>
									</c:if>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td align="left" colspan='7'>ORDER INFO:</td>
									</tr>
									<c:if test="${not empty supportOrderList}">
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td colspan='7'>
											<table border="1">
												<tr>
													<th>Support Type</th>
													<th>Docket Number</th>
													<th>Amount Ordered</th>
													<th>Frequency</th>
													<th>Effective Date</th>
													<th>County</th>
													<th>State</th>
													<th>Payor</th>
													<th>Child(ren)</th>
												</tr>
												<c:forEach var="order" items="${supportOrderList}">
												<tr>
													<td>${order.getSupportTypeDesc()}</td>
													<td>${order.getDocketNm()}</td>
													<td>${order.getOrderAmountDisplay()}</td>
													<td>${order.getPayFreq()}</td>
													<td>${order.getOrderEffDtStrPg()}</td>
													<td>${order.getCountyNm()}</td>
													<td>${order.getStateNm()}</td>
													<td>${order.getPayorNm()}</td>
													<td>${order.getChildren()}</td>
												</tr>
												</c:forEach>				
											</table>
										</td>
									</tr>
									</c:if>
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td align="left" colspan='7'>MARRIAGE DATA:</td>
									</tr>
									<c:if test="${not empty partProfileExtMap && partProfileExtMap.containsKey('1')}">
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td colspan='7'>
											<table border="1">
												<tr>
													<td colspan='2'>City, State, County and Country where child was conceived:</td>
												</tr>	
												<tr>
													<td align="left"><label title="City where this child was born">City: </label></td>
													<td align="left">${partProfileExtMap.containsKey('1') ? partProfileExtMap.get('1').getProfCty() : ''}</td>
												</tr>
												<tr>
													<td align="left"><label>County: </label></td>
													<td align="left">${partProfileExtMap.get('1').getProfCntyNm()}</td>
												</tr>
												<tr>
													<td align="left"><label>State: </label></td>
													<td align="left">${partProfileExtMap.get('1').getProfSt()}</td>
												</tr>
												<tr>
													<td align="left"><label>Country: </label></td>
													<td align="left">${partProfileExtMap.get('1').getProfCntry()}</td>
												</tr>					
											</table>
										</td>
									</tr>
									</c:if>
									<c:if test="${not empty partProfileExtMap && partProfileExtMap.containsKey('2')}">
									<tr><td colspan='9'>&nbsp;</td></tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td colspan='7'>
											<table border="1">
												<tr>
													<td colspan='2'>City, State, County and Country of child's birth:</td>
												</tr>	
												<tr>
													<td align="left"><label title="City where this child was born">City: </label></td>
													<td align="left">${partProfileExtMap.containsKey('2') ? partProfileExtMap.get('2').getProfCty() : ''}</td>
												</tr>
												<tr>
													<td align="left"><label>County: </label></td>
													<td align="left">${partProfileExtMap.get('2').getProfCntyNm()}</td>
												</tr>
												<tr>
													<td align="left"><label>State: </label></td>
													<td align="left">${partProfileExtMap.get('2').getProfSt()}</td>
												</tr>
												<tr>
													<td align="left"><label>Country: </label></td>
													<td align="left">${partProfileExtMap.get('2').getProfCntry()}</td>
												</tr>					
											</table>
										</td>
									</tr>
									</c:if>
									<tr><td colspan='9'>&nbsp;</td></tr>
								</table>
								<table>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td align="left" colspan='7'>OTHER INFO:</td>
									</tr>
									<tr>
										<td colspan='2'>&nbsp;&nbsp;&nbsp;</td>
										<td colspan='7'>
										<textarea  name = "noteText" rows="5" cols="100" maxlength="500" readonly="readonly">${ncpExtDetails.getAdditionalNote()}</textarea>
										</td>
									</tr>																	
								</table>
							</td>							
						</tr>
						<tr><td colspan='9'>&nbsp;</td></tr>
						<tr>
							<td>
								<table>
									<tr>
										<td>&nbsp;&nbsp;</td>
          								<td><input type="button" name="back" value="Back" onclick="location.href='${pageContext.request.contextPath}/cswp/user/caseApplicationPartUpdate.htm'"></td>								
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

