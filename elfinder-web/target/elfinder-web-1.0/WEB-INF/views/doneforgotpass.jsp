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

  <title>Forgot Password</title>

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
    <form action="<spring:url value="/user/doneforgotpass"/>" method="post" class="login-form login-form2" >
		<header class="panel-heading">
                Registration
        </header>
		<div class="login-wrap">
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="username" type="text" class="form-control" placeholder="Username" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_contacts_alt"></i></span>
          <input name="key" type="password" class="form-control" placeholder="key" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_contacts_alt"></i></span>
          <input name="pass" type="password" class="form-control" placeholder="password" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_contacts_alt"></i></span>
          <input name="cpass" type="password" class="form-control" placeholder="confirm password" autofocus>
        </div>
                
        <input name="button" value="change password" class="btn btn-info btn-lg btn-block" type="submit"></input>
      </div>
    </form>
    <div class="text-right">
      <div class="credits">
        </div>
    </div>
  </div>

	<% if(request.getAttribute("done")!=null && (boolean) request.getAttribute("done") ){ %>
	<script type="text/javascript">
		alert("an email has been sent. please follow your email.");
	</script>
	<%} %>
</body>

</html>
