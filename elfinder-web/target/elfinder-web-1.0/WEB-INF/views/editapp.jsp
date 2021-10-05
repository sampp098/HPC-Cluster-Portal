<%@page import="bab.mvc.data.entities.pure.Applications"%>
<%@page import="java.util.Date"%>
<%@page import="bab.mvc.data.entities.pure.News"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>Edit Application</title>

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
							<i class="fa fa-laptop"></i> Edit Application
						</h3>
						<a href="<spring:url value="/user/hpchome"/>">Back</a>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              				<li><i class="icon_document_alt"></i>Applications</li>
              				<li><i class="fa fa-files-o"></i>Edit Application>
						</ol>
					</div>
				</div>
				<!--/.row-->

				<% Applications app= (Applications) request.getAttribute("app") ;
					String[] types=app.getFiles().split("::");
					String[] scripts=app.getScripts().split("::");
					String[] exports=app.getExports().split("::");
				%>
				<div class="row">

					<!-- users table -->
					<div class="col-lg-9 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong>Edit Application</strong>
								</h2>
							</div>
							<div class="panel-body">
							
							<form class="form-validate form-horizontal" id="register_form" action="<spring:url value="/apps/doeditapp"/>" method="post" accept-charset="UTF-8">
								<input type="hidden" name="appid" value="<%=app.getAppid() %>" />
								<div>
										<h3>Notice: file1,file2,file3... are input files of defined type files</h3>
										<hr>
										<label >Application name(Exact as folder name): <input type="text" name="appname" value="<%=app.getAppname() %>" /></label>
								</div>
								<table class="table bootstrap-datatable countries" id="texport">
									<thead>
										<tr>
											<th>Hidden Commands</th>
										</tr>
									</thead>
									<tbody>
										<%for(String export: exports){ %>
										<tr>
											<td class="td_t"><input type="text" name="export"  class="col-lg-9" value="<%=export%>" /></td>
										</tr>
										<%} %>
									</tbody>
								</table>
								<button name="button" value="add row" class="btn btn-primary" type="button" onclick="myCreateFunction('texport','export')">Add Row</button>
								<button name="button" value="delete row" class="btn btn-primary" type="button" onclick="myDeleteFunction('texport')">Delete Row</button>
								<hr>
								
								<table class="table bootstrap-datatable countries" id="tscript">
									<thead>
										<tr>
											<th>Editable Commands</th>
										</tr>
									</thead>
									<tbody>
										<%for(String script: scripts){ 
											System.out.println("script:"+script);
										%>
										
										<tr>
											<td><input type="text" name="script" class="col-lg-9" value="<%= script %>"/></td>
										</tr>
										<%} %>
									</tbody>
								</table>
								<button name="button" value="add row" class="btn btn-primary" type="button" onclick="myCreateFunction('tscript','script')">Add Row</button>
								<button name="button" value="delete row" class="btn btn-primary" type="button" onclick="myDeleteFunction('tscript')">Delete Row</button>
								<hr>
								
								<table class="table bootstrap-datatable countries" id="ttype">
									<thead>
										<tr>
											<th>File types.(File1,file2,... are rows and '*' defines 'all files')</th>
										</tr>
									</thead>
									<tbody>
										<%for(String type: types){ %>
										<tr>
											<td><input type="text" name="type" class="col-lg-9" value="<%=type %>"/></td>
										</tr>
										<%} %>
									</tbody>
								</table>
								<button name="button" value="add row" class="btn btn-primary" type="button" onclick="myCreateFunction('ttype','type')">Add Row</button>
								<button name="button" value="delete row" class="btn btn-primary" type="button" onclick="myDeleteFunction('ttype')">Delete Row</button>
								<hr>
								
								<button name="button" value="submit" class="btn btn-primary" type="submit" >Submit</button>
							</form>
							</div>
						</div>
					</div>
					<!-- end users table  -->

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
	
	
	<!-- <script src="/js/dataTable/jquery-3.3.1.js"></script> -->
	<script src="/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="/js/dataTable/dataTables.bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
		$('#newstable').DataTable();
		} );
	</script>

<script>
function myCreateFunction(tid,iname) {
  var table = document.getElementById(tid);
  var row = table.insertRow(1);
  var cell1 = row.insertCell(0);
  cell1.innerHTML = '<input type="text" name="'+iname+'" class="col-lg-9" />';
}

function myDeleteFunction(tid) {
  document.getElementById(tid).deleteRow(1);
}
</script>

	
</body>

</html>
