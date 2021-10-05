<%@page import="java.util.Date"%>
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

  <title>Create News</title>

  <!-- Bootstrap CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="/css/font-awesome.min.css" rel="stylesheet" />
  <link href="/css/daterangepicker.css" rel="stylesheet" />
  <link href="/css/bootstrap-datepicker.css" rel="stylesheet" />
  <link href="/css/bootstrap-colorpicker.css" rel="stylesheet" />
  <!-- date picker -->

  <!-- color picker -->

  <!-- Custom styles -->
  <link href="/css/style.css" rel="stylesheet">
  <link href="/css/style-responsive.css" rel="stylesheet" />

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
  <!--[if lt IE 9]>
      <script src="/js/html5shiv.js"></script>
      <script src="/js/respond.min.js"></script>
      <script src="/js/lte-ie7.js"></script>
    <![endif]-->

    <!-- =======================================================
      Theme Name: NiceAdmin
      Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
      Author: BootstrapMade
      Author URL: https://bootstrapmade.com
    ======================================================= -->
</head>

<body>

  <!-- container section start -->
  <section id="container" class="">
    <!--header start-->
    <jsp:include page="header.jsp"></jsp:include>
    <!--header end-->
	
    <!--sidebar start-->
    <jsp:include page="sidebarMenu.jsp"></jsp:include>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-file-text-o"></i> Create News</h3>
            <a href="<spring:url value="/user/hpchome"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>News</li>
              <li><i class="fa fa-file-text-o"></i>Create News</li>
            </ol>
          </div>
        </div>
        
        <!-- myform-->
	
		<div class="col-lg-6">
            <section class="panel">
              <header class="panel-heading">
                News Form
              </header>
              <div class="panel-body">
                <form role="form" action="<spring:url value="/messaging/createnews"/>" method="post" >
                  
				  <div class="form-group">
                    <label for="exampleInputEmail1">title</label>
                    <input name="title" type="text" class="form-control"  placeholder="Enter the title" minlength="3" maxlength="45" required="required">
                  </div>
                  
					<!-- CKEditor -->
                  <div class="form-group">
                     <label for="text">Text</label>
                     <textarea class="form-control ckeditor" name="text" placeholder="Enter the news" ></textarea>
                  </div>
                   		  
				  <%-- <div class="form-group">
                    <label for="fullname" class="control-label col-lg-3">Expire Time<span class="required">*</span></label>
					<div class="col-lg-4 input-append date" id="dpYears" data-date="<% out.write((new Date().toString().split(" "))[0]); %>" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
						<input name="expire" class="form-control" size="16" type="text" value="<%=(new Date().toString().split(" "))[0] %>" readonly>
						<span class="add-on"><i class="icon-calendar"></i></span>
					</div>
                  </div> --%>
                  <div class="form-group">
                        <label class="control-label col-sm-4">Expire date</label>
                        <div class="col-sm-6">
                          <input name="expire" id="dp1" type="text"  size="16" class="form-control" required="required" readonly="readonly">
                        </div>
                  </div>
                  
				  <div class="form-group">
			   		<button type="submit" class="btn btn-primary">Submit</button>
				  </div>

              </div>
            </section>
          </div>
	
        <!-- end of my form -->
        
        <!-- page end-->
      </section>
    </section>
    <!--main content end-->
    <div class="text-right">
      <div class="credits">
        </div>
    </div>
  </section>
  <!-- container section end -->
  <!-- javascripts -->
  <script src="/js/jquery.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <!-- nice scroll -->
  <script src="/js/jquery.scrollTo.min.js"></script>
  <script src="/js/jquery.nicescroll.js" type="text/javascript"></script>

  <!-- jquery ui -->
  <script src="/js/jquery-ui-1.9.2.custom.min.js"></script>

  <!--custom checkbox & radio-->
  <script type="text/javascript" src="/js/ga.js"></script>
  <!--custom switch-->
  <script src="/js/bootstrap-switch.js"></script>
  <!--custom tagsinput-->
  <script src="/js/jquery.tagsinput.js"></script>

  <!-- colorpicker -->

  <!-- bootstrap-wysiwyg -->
  <script src="/js/jquery.hotkeys.js"></script>
  <script src="/js/bootstrap-wysiwyg.js"></script>
  <script src="/js/bootstrap-wysiwyg-custom.js"></script>
  <script src="/js/moment.js"></script>
  <script src="/js/bootstrap-colorpicker.js"></script>
  <script src="/js/daterangepicker.js"></script>
  <script src="/js/bootstrap-datepicker.js"></script>
  <!-- ck editor -->
  <script type="text/javascript" src="/assets/ckeditor/ckeditor.js"></script>
  <!-- custom form component script for this page-->
  <script src="/js/form-component.js"></script>
  <!-- custome script for all page -->
  <script src="/js/scripts.js"></script>


</body>

</html>
