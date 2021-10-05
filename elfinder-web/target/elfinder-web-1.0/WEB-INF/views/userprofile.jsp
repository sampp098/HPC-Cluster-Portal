<%@page import="bab.mvc.data.entities.pure.User"%>
<%@page import="bab.mvc.data.entities.pure.Faculty"%>
<%@page import="bab.mvc.data.entities.pure.University"%>
<%@page import="java.util.List"%>
<%@page import="bab.mvc.data.entities.pure.Grade"%>
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

  <title>My Profile</title>

  <!-- Bootstrap CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="/css/font-awesome.min.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="/css/style.css" rel="stylesheet">
  <link href="/css/style-responsive.css" rel="stylesheet" />
  <link href="/css/daterangepicker.css" rel="stylesheet" />
  <link href="/css/bootstrap-datepicker.css" rel="stylesheet" />
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
            <h3 class="page-header"><i class="fa fa-files-o"></i> Profile for <%= ((User) session.getAttribute("currentuser")).getUserName() %></h3>
            <a href="<spring:url value="/user/hpchome"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>User</li>
              <li><i class="fa fa-files-o"></i>My Profile</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->

        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                <%= ((User) session.getAttribute("currentuser")).getUserName() %>
              </header>
              <div class="panel-body">
                <div class="form">
                  <form enctype="multipart/form-data" class="form-validate form-horizontal" id="register_form" action="<spring:url value="/user/profile"/>" method="post">
                    
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">First name <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="fname" type="text" value="<%= ((User) session.getAttribute("currentuser")).getFname() %>"  />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Last name <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="lname" type="text" value="<%= ((User) session.getAttribute("currentuser")).getLname() %>"  />
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Email<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="cemail" type="email" name="email" required value="<%= ((User) session.getAttribute("currentuser")).getEmail() %>" readonly/>
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="username" class="control-label col-lg-3">Username <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="username" name="username" type="text" readonly value="<%= ((User) session.getAttribute("currentuser")).getUserName() %>" readonly/>
							</div>
						</div>
					</div>
					
					<!--  
                    <div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="password" class="control-label col-lg-3">Password <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="password" name="password" type="password" />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="confirm_password" class="control-label col-lg-3">Confirm Psswd<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="confirm_password" name="confirm_password" type="password" />
							</div>
						</div>
					</div>
					-->
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">National Code<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="ncode" name="nationalcode" type="text"
								required
								pattern="[0-9]*"
								minlength="10"
								maxlength="10"
								value="<%= ((User) session.getAttribute("currentuser")).getNationalCode() %>" />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Grade<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="grade">
									<% for(Grade g: (List<Grade>) request.getAttribute("grade")){%>
    									<option value="<%=g.getGradeid() %>" <% if(g.getGradeid()==((User) session.getAttribute("currentuser")).getGradeid()){%> selected="selected" <%} %>>
    									<%= g.getGradeid()+". "+g.getGradename() %>
    									</option>
									<%} %>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">University<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="university">
									<% for(University g: (List<University>) request.getAttribute("univesity")){%>
    									<option value="<%=g.getUnid() %>" <% if(g.getUnid()==((User) session.getAttribute("currentuser")).getUnid()){%> selected="selected" <%} %>>
    									<%= g.getUnid()+". "+g.getUnname() %>
    									</option>
									<%} %>
								</select>
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Faculty<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="faculty">
									<% for(Faculty g: (List<Faculty>) request.getAttribute("faculty")){%>
    									<option value="<%=g.getFacultyid() %>" <% if(g.getFacultyid()==((User) session.getAttribute("currentuser")).getFacultyid()){%> selected="selected" <%} %>>
    									<%= g.getFacultyid()+". "+g.getFacultyname() %>
    									</option>
									<%} %>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Field<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="field" type="text" value="<%= ((User) session.getAttribute("currentuser")).getField() %>" 
									required
									minlength="4"
									maxlength="45"
								/>							
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">State<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="state">
										<option value="1" <%if(((User) session.getAttribute("currentuser")).getState()==1){ out.write("selected=\"selected\""); }%> >profesor</option>
										<option value="2" <%if(((User) session.getAttribute("currentuser")).getState()==2){ out.write("selected=\"selected\""); }%>>student</option>
										<option value="3" <%if(((User) session.getAttribute("currentuser")).getState()==3){ out.write("selected=\"selected\""); }%>>none</option>
								</select>
							</div>
						</div>
					
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Stu/Prof ID<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="sn_pn" type="text" value="<%= ((User) session.getAttribute("currentuser")).getSN_PN() %>" 
									required
									pattern="[0-9]*"
									minlength="6"
									maxlength="12"
								/>
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Phone<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="phone" type="text" value="<%= ((User) session.getAttribute("currentuser")).getPhone() %>" 
									required
									pattern="[0-9]*"
									minlength="5"
									maxlength="14"
								/>
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-12">
							<label for="address" class="control-label">Address<span class="required">*</span></label>
							<input class=" form-control" id="address" name="address" type="text" value="<%= ((User) session.getAttribute("currentuser")).getAddress() %>" 
								required
								minlength="10"
								maxlength="200"
							/>
							
						</div>

					</div>
					<div class="form-group row col col-lg-12">

						<div class="col col-lg-6">
							<label for="f2" class="form-control-label">Personal image<span class="required">*</span></label>
							<div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im1');" type="file" id="f2" name="pimage">
							</div>
							
							<br>
    						<img align="middle" id="im1" src='<spring:url value="/user/imageController/myicon.jpg"></spring:url>' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:200px; max-height: 200px;" />
						</div>
						
						<div class="col col-lg-6">
							<label for="f1" class="form-control-label">National Card<span class="required">*</span></label>
							<div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im2');" type="file" id="f1" name="nationalcard">
							</div>
							<br>
							<img align="middle" id="im2" src='<spring:url value="/user/imageController/myncard.jpg"></spring:url>' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:200px; max-height: 200px;" />
						</div>
						
					</div>				
					<div class="form-group row col col-lg-12">

						<div class="col col-lg-6">
							<label for="address" class="form-control-label">st/prof card<span class="required">*</span></label>
							<div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im3');" type="file" id="exampleInputFile" name="st_pr_card">
							</div>
							<br>
							<img align="middle" id="im3" src='<spring:url value="/user/imageController/myst_pr_card.jpg"></spring:url>' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:200px; max-height: 200px;" />
						</div>
						
						<div class="col col-lg-6">
							<label for="address" class="form-control-label">Recommandation<span class="required">*</span></label>
							<div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im4');" type="file" id="exampleInputFile" name="racommandation">
							</div>
							<br>
							<img align="middle" id="im4" src='<spring:url value="/user/imageController/myracommandation.jpg"></spring:url>' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:200px; max-height: 200px;" />
						</div>
					</div>
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button id="save"  class="btn btn-primary" type="submit" onclick="return checkCodeMeli(document.getElementById('ncode').value)">Save</button>
                        
                      </div>
                    </div>
					
                  </form>
				  
                </div>
              </div>
            </section>
          </div>
        </div>
        <!-- page end-->
      </section>
    </section>
    <!--main content end-->
   
  </section>
	<!-- container section start -->
	
	<!-- javascripts -->
	<script src="/js/jquery.js"></script>
	<script src="/js/jquery-ui-1.10.4.min.js"></script>
	<script src="/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="/js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="/js/jquery.scrollTo.min.js"></script>
	<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="/assets/jquery-knob//js/jquery.knob.js"></script>
	<script src="/js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="/js/owl.carousel.js"></script>
	<!-- jQuery full calendar -->
	
	<script src="/js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only-->
	<script src="/js/calendar-custom.js"></script>
	<script src="/js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="/js/jquery.customSelect.min.js"></script>
	<script src="/assets/chart-master/Chart.js"></script>

	<!--custome script for all page-->
	<script src="/js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="/js/sparkline-chart.js"></script>
	<script src="/js/easy-pie-chart.js"></script>
	<script src="/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="/js/xcharts.min.js"></script>
	<script src="/js/jquery.autosize.min.js"></script>
	<script src="/js/jquery.placeholder.min.js"></script>
	<script src="/js/gdp-data.js"></script>
	<script src="/js/morris.min.js"></script>
	<script src="/js/sparklines.js"></script>
	<script src="/js/charts.js"></script>
	<script src="/js/jquery.slimscroll.min.js"></script>
	<script src="/js/codemeli.js"></script>
	
	<script>
	function readURL(input,id) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $(id)
	                .attr('src', e.target.result)
	                .width(150)
	                .height(200);
	        };

	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	
	</script>
	
	
</body>

</html>
