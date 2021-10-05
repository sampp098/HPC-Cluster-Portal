<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link rel="stylesheet" href="/alert/bootstrap.min.css">
<script src="/alert/jquery.min.js"></script> 
<script src="/alert/bootstrap.min.js"></script> 
 
<%
String success= (String)request.getAttribute("success");
String warning= (String)request.getAttribute("warning");
String error= (String)request.getAttribute("error");
%>

<% if(success!=null){%>
	<div class="alert alert-success alert-dismissible fade in">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>Success!</strong> <%=success %>
    </div>
<%}%>

<% if(warning!=null){%>
	<div class="alert alert-warning alert-dismissible fade in">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>Warning!</strong> <%=warning %>
    </div>
<%}%>

<% if(error!=null){%>
	<div class="alert alert-danger alert-dismissible fade in">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>Error!</strong> <%=error %>
    </div>
<%}%>