<%@page import="bab.mvc.data.entities.complex.JobsComplex"%>
<%@page import="bab.mvc.data.entities.pure.HPCTariff"%>
<%@page import="java.util.Date"%>
<%@page import="bab.mvc.data.entities.pure.Applications"%>
<%@page import="bab.mvc.data.entities.pure.FeesGroup"%>
<%@page import="bab.mvc.data.entities.pure.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>My Report</title>

<!-- Bootstrap CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="/css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="/css/elegant-icons-style.css" rel="stylesheet" />
<link href="/css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link href="/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="/assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="/css/owl.carousel.css" type="text/css">
<link href="/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="/css/fullcalendar.css">
<link href="/css/widgets.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<link href="/css/style-responsive.css" rel="stylesheet" />
<link href="/css/xcharts.min.css" rel=" stylesheet">
<link href="/css/jquery-ui-1.10.4.min.css" rel="stylesheet">

<link href="/css/daterangepicker.css" rel="stylesheet" />
<link href="/css/bootstrap-datepicker.css" rel="stylesheet" />
<link href="/css/bootstrap-colorpicker.css" rel="stylesheet" />
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
				<!--overview start-->
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa-laptop"></i> My Report
						</h3>
						<a href="<spring:url value="/user/hpchome"/>">Back</a>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              				<li><i class="icon_document_alt"></i>Job</li>
              				<li><i class="fa fa-files-o"></i>My Report</li>
						</ol>
					</div>
				</div>
				<!--/.row-->

				<%
				
				FeesGroup feesgroup=(FeesGroup) request.getAttribute("feesgroup");  
				User owner=(User) request.getAttribute("owner");
				
				%>

				<div class="row col-lg-12">

					<section class="panel">
						<!-- users table -->
						<div class="col-lg-9 col-md-12">
							<div class="panel panel-default">
								<header class="panel-heading">
									<h2>
										<i class="fa fa-flag-o red"></i><strong>Group Description</strong>
									</h2>
								</header>
								<div class="panel-body">
									<h4>
										<div>
											<label for="cemail" class="col-lg-3">group name: </label><label>
												<%=feesgroup.getFgname()%></label>
										</div>
										<div>
											<label for="cemail" class="col-lg-3">group owner:</label><label>
												<%=owner.getFname() + " " + owner.getLname()%></label>
										</div>
										<div>
											<label for="cemail" class="col-lg-3">remaining
												charge: </label><label><%=feesgroup.getCharge()%> Coins</label>
										</div>
									</h4>
								</div>
							</div>
						</div>
					</section>

					<section class="panel">
						<!-- users table -->
						<div class="col-lg-9 col-md-12">
							<div class="panel panel-default">
								<header class="panel-heading">
									<h2>
										<i class="fa fa-flag-o red"></i><strong>Tariffs</strong>
									</h2>
								</header>
								<div class="panel-body">
									<div class="col-lg-3">
										<select id="app" class="form-control" name="appname">
											<option value="All">All</option>
											<%
												for (HPCTariff t : (List<HPCTariff>) request.getAttribute("tariffs")) {
											%>
											<option value="<%=t.getName()%>">
												<%=t.getName()%>
											</option>
											<%
												}
											%>
										</select>
									</div>
								</div>
							</div>
						</div>
					</section>
					

					<section class="panel">
						<!-- users table -->
						<div class="col-lg-9 col-md-12">
							<div class="panel panel-default">
								<header class="panel-heading">
									<h2>
										<i class="fa fa-flag-o red"></i><strong>My Jobs</strong>
									</h2>
								</header>
								<div class="panel-body">
									<div>
										<form action="<spring:url value="/job/userreportsearch" />"
											method="post">
											<div class="col-lg-3">
												<label for="app" >Application</label>
												<select id="app" class="form-control" name="appname">
													<option value="All">All</option>
													<%
														for (Applications g : (List<Applications>) request.getAttribute("applications")) {
													%>
													<option value="<%=g.getAppname()%>">
														<%=g.getAppname()%>
													</option>
													<%
														}
													%>
												</select>
											</div>
											<div class="col-lg-3">
												<label for="state" >State</label>
												<select id="state" class="form-control" name="state">
													<option value="All">All</option>
													<option value="Q">Queued</option>
													<option value="R">Running</option>
													<option value="C">Concluded</option>
												</select>
											</div>

											<div class="form-group">
												<div class="col-lg-3 input-append date" id="dpYears"
													data-date="<%out.write((new Date().toString().split(" "))[0]);%>"
													data-date-format="yyyy-mm-dd" data-date-viewmode="years">
													<label for="date" >Start Date</label>
													<input id="date" name="date" class="form-control" size="13"
														type="text"
														value="<%=(new Date().toString().split(" "))[0]%>"
														readonly> <span class="add-on"><i
														class="icon-calendar"></i></span>
												</div>
											</div>
											<label for="button" >Action</label>
											<input id="button" type="submit" name="button" value="search"
												class="btn btn-primary" />
										</form>
									</div>
								</div>
								<div class="panel-body">
									<table class="table bootstrap-datatable countries" id="jobstable">
										<thead>
											<tr>
												<th>#</th>
												<th>User Name</th>
												<th>Job name</th>
												<th>Wall time(h)</th>
												<th>CPU time(h)</th>
												<th>status</th>
											</tr>
										</thead>
										<tbody>

											<!-- users list -->
											<%
												List<JobsComplex> js = (List<JobsComplex>) request.getAttribute("jobs");
												String username = ((User) session.getAttribute("currentuser")).getUserName();
											%>

											<%
												for (JobsComplex j : js) {
											%>
											<!-- a row in table -->

											<tr <%if (j.getState().equals("C")) {%> bgcolor="ccffcc"
												<%} else if (j.getState().equals("R")) {%> bgcolor="ffff80"
												<%}%>>
												<form action="<spring:url value="/job/editselected"/>"
													method="post">

													<td><input type="hidden" name="jobid"
														value="<%=j.getJobid()%>"><input type="hidden"
														name="jid" value="<%=j.getJid()%>"><input
														type="hidden" name="appname" value="<%=j.getAppname()%>"><%=j.getJobid()%>
													</td>
													<td><input type="hidden" name="username"
														value="<%=username%>"><%=username%></td>
													<td><input type="hidden" name="jname"
														value="<%=j.getJname()%>"><%=j.getJname()%></td>
													<td><input type="hidden" name="walltime"
														value="<%=j.getWalltime()%>"><%=j.getWalltime()%></td>
													<td><input type="hidden" name="cputime"
														value="<%=j.getCputime()%>"><%=j.getCputime()%></td>
													<td>
														<%
															if (j.getState().equals("C"))
																	out.write("concluded");
																else if (j.getState().equals("R"))
																	out.write("running");
																else
																	out.write("queued");
														%>
													</td>
												</form>
											</tr>
											<!-- end a row in table -->
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- end users table  -->
					</section>
				</div>


			</section>
			<div class="text-right">
				<div class="credits">
					<!--
            All the links in the footer should remain intact.
            You can delete the links only if you purchased the pro version.
            Licensing information: https://bootstrapmade.com/license/
            Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
          -->

				</div>
			</div>
		</section>
		<!--main content end-->
	</section>
	<!-- container section start -->

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

	<!-- chartjs -->
	<script src="/assets/chart-master/Chart.js"></script>
	<!-- custom chart script for this page only-->
	<script src="/js/chartjs-custom.js"></script>
	<script src="/js/charts.js"></script>
	<!-- charts scripts -->
	<script src="/assets/jquery-knob//js/jquery.knob.js"></script>
	<script src="/js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="/js/owl.carousel.js"></script>
	
	<!-- <script src="/js/dataTable/jquery-3.3.1.js"></script> -->
	<script src="/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="/js/dataTable/dataTables.bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
		$('#jobstable').DataTable();
		} );
	</script>
	
</body>

</html>
