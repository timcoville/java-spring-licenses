<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New License</title>
</head>
<body>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<h1>New Person</h1>
<form:form action="/licenses" method="post" modelAttribute="License">
    <p>
        <form:label path="person">Person</form:label>
        
         <form:select path="person">
              <form:option value="-" label="--Please Select"/>
	              <c:forEach items="${people}" var="person">
	             	 <form:option value="${person}" label="${person.firstName} ${person.lastName}"/>
	              </c:forEach>
       		  </form:select>
  		<form:errors path="person"/>
    </p>
    <p>
        <form:label path="state">State</form:label>
        <form:errors path="state"/>
        <form:textarea path="state"/>
    </p>
    <p>
	    <label>Expiration Date</label>
	    <input type="date" name="expirationDate">
 	</p>
    <input type="submit" value="Submit"/>
 
</form:form>  


</body>
</html>