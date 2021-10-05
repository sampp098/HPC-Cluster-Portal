<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
  <meta name="author" content="GeeksLabs">
  <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
  <link rel="shortcut icon" href="img/favicon.png">

  <title>Login</title>

  <!-- Bootstrap CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="/css/style.css" rel="stylesheet">
  <link href="/css/style-responsive.css" rel="stylesheet" />

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
  <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->

    <!-- =======================================================
      Theme Name: NiceAdmin
      Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
      Author: BootstrapMade
      Author URL: https://bootstrapmade.com
    ======================================================= -->
</head>

<body class="login-img3-body">
	
  <div class="container">
	<jsp:include page="uimessages.jsp"></jsp:include>
    <form action="<spring:url value="/user/login"/>" method="post" class="login-form" >
    
      <div class="login-wrap" >
        <p class="login-img"><i class="icon_lock_alt"></i></p>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="username" type="text" class="form-control" placeholder="Username" autofocus required="required">
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
          <input name="pass" type="password" class="form-control" placeholder="Password" required="required">
        </div>
        
        <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
        <input name="button" value="login"    class="btn btn-primary btn-lg btn-block" type="submit"></input>
        <input name="button" value="register" onclick="location.href='/user/showregister';" class="btn btn-info btn-lg btn-block" type="button"></input>
      	
      	<label class="checkbox">
                <!--<input type="checkbox" value="remember-me"> Remember me -->
                <span class="pull-left"> <a href="<spring:url value="/user/forgotpass"/>"> Forgot Password?</a></span>
                <span class="pull-right"> <a href="<spring:url value="/user/compregform"/>"> Complete Registration</a></span>
        </label>
        <hr>
        <label class="checkbox">
                <!--<input type="checkbox" value="remember-me"> Remember me -->
                <span class="pull-left">  <a href="<spring:url value="/user/freecontact"/>">Contact US</a></span>
                <span class="pull-right"> <a target="_blank" href="<spring:url value="/user/documentation/Help_register.pdf" />">Help?</a></span>
        </label>
        
      </div>
    </form>
    
  </div>
	
	<% if(request.getAttribute("registered")!=null && (boolean) request.getAttribute("registered") ){ %>
	<script type="text/javascript">
		alert("an email has been sent. The email is alive until 24 hours passed! \n please go to 'Complete Registration' from below.");
	</script>
	<%} %>
</body>

</html>
