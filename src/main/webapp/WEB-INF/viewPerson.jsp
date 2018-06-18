<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Page</title>
</head>
<body>
	<h1><c:out value="${person.firstName} ${person.lastName}" /></h1>
	<h2>License Number: <c:out value="${license.number}"/></h2>
	<h3>State: <c:out value="${license.state}"/></h3>
	<c:set var="date" value="${license.expiration_date}"/>
	<h4>Expiration Date: <fmt:formatDate pattern = "MM-dd-yyyy"  value="${date}" /></h4>
</body>
</html>