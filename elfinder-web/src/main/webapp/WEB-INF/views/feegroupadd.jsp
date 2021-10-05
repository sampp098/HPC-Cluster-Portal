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

  <title>Fees Group Add</title>

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
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>Groups</li>
              <li><i class="fa fa-files-o"></i>Fees Group Add</li>
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
								<input class=" form-control" id="fullname" name="fname" type="text" value="<%= ((User) session.getAttribute("currentuser")).getFname() %>" readonly />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Last name <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="lname" type="text" value="<%= ((User) session.getAttribute("currentuser")).getLname() %>" readonly />
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
								<input class=" form-control" id="fullname" name="nationalcode" type="text" value="<%= ((User) session.getAttribute("currentuser")).getNationalCode() %>" />
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
								<input class=" form-control" id="fullname" name="field" type="text" value="<%= ((User) session.getAttribute("currentuser")).getField() %>" />
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">State<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="state">
										<option>profesor</option>
										<option>student</option>
										<option selected="selected">none</option>
								</select>
							</div>
						</div>
					
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Stu/Prof ID<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="sn_pn" type="text" value="<%= ((User) session.getAttribute("currentuser")).getSN_PN() %>" />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Phone<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="phone" type="text" value="<%= ((User) session.getAttribute("currentuser")).getPhone() %>" />
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-12">
							<label for="address" class="control-label">Address<span class="required">*</span></label>
							<input class=" form-control" id="address" name="address" type="text" value="<%= ((User) session.getAttribute("currentuser")).getAddress() %>" />
							
						</div>

					</div>
					<div class="row form-group col-lg-12">

						<div class="form-group col-lg-6">
							<label for="address" class="control-label col-lg-2">Personal image<span class="required">*</span></label>
							<div class="col-lg-3">
								<input type="file" id="exampleInputFile" name="pimage">
							</div>
						</div>
						
						<div class="form-group col-lg-6">
							<label for="address" class="control-label col-lg-2">National Card<span class="required">*</span></label>
							<div class="col-lg-3">
								<input type="file" id="exampleInputFile" name="nationalcard">
							</div>
						</div>
						
					</div>
										
					<div class="row form-group col-lg-12">

						<div class="form-group col-lg-6">
							<label for="address" class="control-label col-lg-2">st/prof card<span class="required">*</span></label>
							<div class="col-lg-3">
								<input type="file" id="exampleInputFile" name="st_pr_card">
							</div>
						</div>
						
						<div class="form-group col-lg-6">
							<label for="address" class="control-label col-lg-2">Recommandation<span class="required">*</span></label>
							<div class="col-lg-3">
								<input type="file" id="exampleInputFile" name="racommandation">
							</div>
						</div>
					</div>
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-primary" type="submit">Save</button>
                        <button class="btn btn-default" type="button">Cancel</button>
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
	<
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
	<script>
		//knob
		$(function() {
			$(".knob").knob({
				'draw' : function() {
					$(this.i).val(this.cv + '%')
				}
			})
		});

		//carousel
		$(document).ready(function() {
			$("#owl-slider").owlCarousel({
				navigation : true,
				slideSpeed : 300,
				paginationSpeed : 400,
				singleItem : true

			});
		});

		//custom select box

		$(function() {
			$('select.styled').customSelect();
		});

		/* ---------- Map ---------- */
		$(function() {
			$('#map').vectorMap({
				map : 'world_mill_en',
				series : {
					regions : [ {
						values : gdpData,
						scale : [ '#000', '#000' ],
						normalizeFunction : 'polynomial'
					} ]
				},
				backgroundColor : '#eef3f7',
				onLabelShow : function(e, el, code) {
					el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
				}
			});
		});
	</script>

</body>

</html>
