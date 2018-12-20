<%@ include file="../include/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Description" content="DESCRIPTION" />
<meta name="Keywords" content="KEYWORDS" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/autocomplete.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/button.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/lightbox.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/calendar.css"
	rel="stylesheet" type="text/css" />
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico"
	type="image/ico" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.pngFix.pack.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/utils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lightBox.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lightBoxBAD.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/hover.menu.js"></script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<tiles:insertAttribute name="headerlogin" />
	<tiles:insertAttribute name="topNomenu" />
	<tiles:insertAttribute name="leftbar" />
	<tiles:insertAttribute name="body" />
</body>
</html>