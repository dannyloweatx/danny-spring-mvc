<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">Spring Mvc and Hibernate (with postgress) Example</h2>
	    	<div>&nbsp;</div>
	    	
	    	<!-- Div to add a new user to the postgres database -->
	    	<div id="add_new_user">
	    			<c:url var="addUrl" value="../user/add" /><a id="add" href="${addUrl}" class="btn btn-success">Add user</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user list from the postgres database -->
	    	<table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th><th>First Name</th><th>Last Name</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${users}" var="user">
	                	<tr align="center">
	                    	<td><c:out value="${user.id}" /></td>
	                    	<td><c:out value="${user.firstName}" /></td>
	                    	<td><c:out value="${user.lastName}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="../user/edit?id=${user.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="../user/delete?id=${user.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>