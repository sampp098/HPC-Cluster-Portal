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

  <title>Registration</title>

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
    <form action="<spring:url value="/user/register"/>" method="post" class="login-form login-form2 " >
		<header class="panel-heading">
                Registration
        </header>
		<div class="login-wrap">
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="fname" type="text" class="form-control" placeholder="First name" autofocus required minlength="3" maxlength="10"
          title="The "
          >
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="lname" type="text" class="form-control" placeholder="Last name" autofocus required minlength="4" maxlength="12">
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="username" type="text" class="form-control" placeholder="Username" autofocus required minlength="5" maxlength="12">
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_contacts_alt"></i></span>
          <input name="email" type="text" class="form-control" placeholder="email" autofocus
          pattern="^([^\x00-\x20\x22\x28\x29\x2c\x2e\x3a-\x3c\x3e\x40\x5b-\x5d\x7f-\xff]+|\x22([^\x0d\x22\x5c\x80-\xff]|\x5c[\x00-\x7f])*\x22)(\x2e([^\x00-\x20\x22\x28\x29\x2c\x2e\x3a-\x3c\x3e\x40\x5b-\x5d\x7f-\xff]+|\x22([^\x0d\x22\x5c\x80-\xff]|\x5c[\x00-\x7f])*\x22))*\x40([^\x00-\x20\x22\x28\x29\x2c\x2e\x3a-\x3c\x3e\x40\x5b-\x5d\x7f-\xff]+|\x5b([^\x0d\x5b-\x5d\x80-\xff]|\x5c[\x00-\x7f])*\x5d)(\x2e([^\x00-\x20\x22\x28\x29\x2c\x2e\x3a-\x3c\x3e\x40\x5b-\x5d\x7f-\xff]+|\x5b([^\x0d\x5b-\x5d\x80-\xff]|\x5c[\x00-\x7f])*\x5d))*$"
          minlength="6" maxlength="30"
          title="The domain portion of the email address is invalid (the portion after the @)."
          required
          >
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
          <input name="pass"  id="pass" type="password" class="form-control" placeholder="Password" required
          minlength="6" maxlength="20"
          >
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
          <input name="cpass"  id="cpass" type="password" class="form-control" placeholder="Confirm Password" required>
        </div>
        
        <input name="button" value="register" class="btn btn-info btn-lg btn-block" type="submit" onclick="return validatePass()"></input>
      	
      </div>
    </form>
    <div class="text-right">
      <div class="credits">
        </div>
    </div>
  </div>
  
  <script type="text/javascript">
  	function validatePass(){
  		var  pass=document.getElementById("pass").value;
  		var cpass=document.getElementById("cpass").value;
  		if(pass == cpass){
  			return true;
  		}
  		
  		alert("the confirm password is not the same with password!");  			

  		return false;
  	}
  	
  </script>

</body>

</html>
