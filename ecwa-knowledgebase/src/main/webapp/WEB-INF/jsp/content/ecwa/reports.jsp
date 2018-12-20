<%@ include file="../../include/taglib.jsp"%>
<script>
function goToPreviousPage() 
{ location.href = "caseApplicationPartList.htm"; }
</script>
<div id="content">
	<div class="gutter">
  		<h2>View & Print Application Form</h2>
  		<hr>
  		<iframe  height="600px" width="100%" src="${pageContext.request.contextPath}/reports?ReportName=ecwa/dss-4451-ia_form.rptdesign&ReportFormat=pdf&appid=${applId}" name="iframe_4_form_print"></iframe>
  		<form name="reports" method="post" action="reports.htm" target ="iframe_4_form_print" modelAttribute="applId"> </form>
  		<br>
  	<input type=button value='Previous' onclick="goToPreviousPage()" />
	</div>
</div>