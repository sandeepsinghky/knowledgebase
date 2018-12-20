<%@ include file="../include/taglib.jsp"%>
<script>
	{

		$(document).ready(function() {
			initLightbox();
		});
		function valLogin() {
			if(document.getElementById("j_username").value.trim()=="" && document.getElementById("j_password").value.trim()=="") 
		    {
		         alert("Login name and password are required.");
		         document.getElementById("j_username").focus();
		         return false;
		    }
			else if(document.getElementById("j_username").value.trim()=="") 
		    {
		         alert("Login name is required.");
		         document.getElementById("j_username").focus();
		         return false;
		    }
			else if(document.getElementById("j_password").value.trim()=="") 
		   	{
			   	 alert("Password is required.");
		         document.getElementById("j_password").focus();
		         return false;
		   	}
		   	return true;	 
		}

	}
</script>

<div id="content">
	<div class="gutter">
		<div class="centered"><h2>North Carolina Child Support Portal: Sign In Process</h2></div>
		
		<!-- <p>Welcome to the North Carolina Child Support Portal.</p> -->
		<div id="loginDiv" style="display: none">
			<h2>ERROR</h2>
			<table>
				<tr>
					<td class=legend id="errMsgDiv"></td>
				</tr>
				<tr>
					<td class=center><br /> <input type=button value='Close' onclick="hideLightbox()" />
				</tr>
			</table>
		</div>
		
		<div class="centered">
			<c:if test='${not empty param.login_error}'>
				<font color="red"> Your login attempt was not successful. Please try again.<br /> <br /> Reason: ${param.message}</font>
			</c:if>

			<c:if test="${!empty param.success}">
				<div class="success message-box">${param.success}</div>
				<br />
			</c:if>
			<dl class="feature">
				<form action="<c:url value='/j_spring_security_check'/>" method="post">
					<table>
						<tr>
							<td><label for="login" class="">Login Name:</label>
							</td>
							<td><input type="text" name="j_username" id="j_username" maxlength="20"	id="login" value="" title="Enter your login name" /></td>

						</tr>
						<tr>
							<td><label for="password" class="">Password:</label></td>
							<td><input type="password" name="j_password" id="j_password" maxlength="20"	id="password" value="" title="Enter your password" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center"><input name="loginbtn" type="submit"	value="Login" onclick="return valLogin()"/></td>
						</tr>
					</table>
				</form>
			</dl>
		</div>
		
		<p align="center">NCID username and password are required to use the portal. Contact your county administrator to get access.</p>
		<p align="center">Forgot username/password? Visit <a target="_blank" href=https://idpdev.nc.gov:8443/nidp/idff/sso? />NCID</a> to reset your 
			username/password and return to this page to login.
		</p>
		<hr>
	</div>
</div>

