<%@page import="bab.mvc.data.services.util.Util"%>
<%@page import="bab.mvc.data.entities.pure.FeesGroup"%>
<%@page import="bab.mvc.data.entities.pure.User"%>
<%@page import="java.util.List"%>
<%@page import="bab.mvc.data.entities.pure.Jobs"%>
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

<title>Jobs List</title>

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
	<% FeesGroup group=(FeesGroup) request.getAttribute("group"); %>
	<% User owner=(User) request.getAttribute("owner"); %>
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
							<i class="fa fa-laptop"></i> Report of Group '<%= group.getFgname() %>'
						</h3>
						<a href="<spring:url value="/user/hpchome"/>">Back</a>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
							<li><i class="icon_document_alt"></i>Groups</li>
              				<li><i class="fa fa-files-o"></i>Report</li>
						</ol>
					</div>
				</div>
				<!--/.row-->
				<section class="panel">
			<!-- users table -->
			<div class="col-lg-9 col-md-12">
				<div class="panel panel-default">
					<header class="panel-heading">
						<h2>
							<i class="fa fa-flag-o red"></i><strong>Group
								Description</strong>
						</h2>
					</header>
					<div class="panel-body">
						<h4>
							<div>
								<label for="cemail" class="col-lg-3">group name: </label><label>
									<%=group.getFgname()%></label>
							</div>
							<div>
								<label for="cemail" class="col-lg-3">group owner:</label><label>
									<%=owner.getFname() + " " + owner.getLname()%></label>
							</div>
							<div>
								<label for="cemail" class="col-lg-3">remaining charge: </label><label><%=group.getCharge()%>
									Coins</label>
							</div>
						</h4>
					</div>
				</div>
			</div>
		</section>

				<div class="row">

					<!-- users table -->
					<div class="col-lg-9 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong>My Group Reports</strong>
								</h2>
								
							</div>
							<div class="panel-body">



								<table class="table bootstrap-datatable countries"
									id="jobstable">
									<thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Username</th>
                                        <!-- <th>App-Name</th> -->
                                        <th>Average Cores</th>
                                        <th>Average Memory</th>
                                        <th>Total CPU-Time</th>
                                        <th>Cost</th>
                                        <th>Report</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<% 
                                	int i=1;
                                	for(Object[] obj : (List<Object[]>) request.getAttribute("jobs") ) {
                                		//job		obj[0]
                                		//cost		obj[1]
                                		//cputime	obj[2]
                                		//corenum	obj[3]
                                		//memnum	obj[4]
                                		//user		obj[5]
                                		
                                		Jobs j=(Jobs) obj[0];
                                		User u=(User) obj[5];

                                	%>
                                		
                                    <tr>
                                        <td><%= i++ %></td>
                                        <td><%= u.getUserName() %></td>
                                        <%-- <td><%= j.getAppname() %></td> --%>
                                        <td><%= obj[3].toString() %></td>
                                        <td><%= obj[4].toString()%></td>
                                        <td><%= Util.getTime(Double.parseDouble(obj[2].toString())) %></td>
                                        <td><%= obj[1].toString()%></td>
										<td>
											<form target="_blank" action='<spring:url value="/reports/reportmember" ></spring:url>' method="post" >
												<input type="hidden" name="uid" value="<%= j.getUid() %>"  />
												<button class="btn btn-info notika-btn-teal" type="submit" >More Info</button>
											</form>
										</td>
                                    </tr>
                                    <%}%>
                                </tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- end users table  -->

				</div>
				
				<div class="row">

					<!-- users table -->
					<div class="col-lg-9 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong> CPU & Memory Average Report</strong>
								</h2>
								
							</div>
							<div class="panel-body">
								<div class="col-lg-6" style="margin-left: -30px;">
            						<div id="piechart4"></div>
            					</div>
            					<div class="col-lg-6" style="margin-left: -30px;"">
            						<div id="piechart2"></div>
            					</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">

					<!-- users table -->
					<div class="col-lg-9 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong> CPU Time & Cost Report</strong>
								</h2>
								
							</div>
							<div class="panel-body">
								<div class="col-lg-6 " style="margin-left: -30px;">
            						<div id="piechart1"></div>
            					</div>
            					<div class="col-lg-6 " style="margin-left: -30px;">
            						<div id="piechart3"></div>
            					</div>
							</div>
						</div>
					</div>
				</div>				
				
				

			</section>
			<div class="text-right">
				<div class="credits">


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
	
	
	<!-- <script src="/js/dataTable/jquery-3.3.1.js"></script> -->
	<script src="/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="/js/dataTable/dataTables.bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
		$('#jobstable').DataTable(
				 {
				        "order": [[ 1, "desc" ]]
				    }		
		
		);
		} );
	</script>
<!-- ****************************************************************************	 -->

    <script src="/report/Chart.js"></script>
    <script type="text/javascript" src="/report/loader.js"></script>

	<script type="text/javascript">
		// Load google charts
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(drawChart1);
		google.charts.setOnLoadCallback(drawChart2);
		google.charts.setOnLoadCallback(drawChart3);
		google.charts.setOnLoadCallback(drawChart4);
		$( window ).load(LineChart());
		
		<% 
		String data1="[ [ 'Task', 'Hours per Day' ],";
		String data2="[ [ 'Task', 'Hours per Day' ],";
		String data3="[ [ 'Task', 'Hours per Day' ],";
		String data4="[ [ 'Task', 'Hours per Day' ],";
		
		for(Object[] obj : (List<Object[]>) request.getAttribute("jobsPie") ) {
		
				//job		obj[0]
                //cost		obj[1]
                //cputime	obj[2]
                //corenum	obj[3]
                //memnum	obj[4]
				data1+="['"+((Jobs)obj[0]).getAppname()+"', "+obj[2]+"],";
				data2+="['"+((Jobs)obj[0]).getAppname()+"', "+obj[4]+"],";
				data3+="['"+((Jobs)obj[0]).getAppname()+"', "+obj[1]+"],";
				data4+="['"+((Jobs)obj[0]).getAppname()+"', "+obj[3]+"],";
			
		}
		data1+="]";
		data2+="]";
		data3+="]";
		data4+="]";
    	%>

		// Draw the chart and set the chart values
		function drawChart1() {
			var data = google.visualization.arrayToDataTable(<%= data1%>);

			// Optional; add a title and set the width and height of the chart
			var options = {
				'title' : 'CPU Time',
				'width' : 450,
				'height' : 350
			};

			// Display the chart inside the <div> element with id="piechart"
			var chart = new google.visualization.PieChart(document
					.getElementById('piechart1'));
			chart.draw(data, options);
		}
		function drawChart2() {
			var data = google.visualization.arrayToDataTable(<%= data2%>);

			// Optional; add a title and set the width and height of the chart
			var options = {
				'title' : 'Average Memory',
				'width' : 450,
				'height' : 350
			};

			// Display the chart inside the <div> element with id="piechart"
			var chart = new google.visualization.PieChart(document
					.getElementById('piechart2'));
			chart.draw(data, options);
		}
		function drawChart3() {
			var data = google.visualization.arrayToDataTable(<%= data3%>);

			// Optional; add a title and set the width and height of the chart
			var options = {
				'title' : 'Cost',
				'width' : 450,
				'height' : 350
			};

			// Display the chart inside the <div> element with id="piechart"
			var chart = new google.visualization.PieChart(document
					.getElementById('piechart3'));
			chart.draw(data, options);
		}
		
		function drawChart4() {
			var data = google.visualization.arrayToDataTable(<%= data4%>);

			// Optional; add a title and set the width and height of the chart
			var options = {
				'title' : 'Average Cores',
				'width' : 450,
				'height' : 350
			};

			// Display the chart inside the <div> element with id="piechart"
			var chart = new google.visualization.PieChart(document
					.getElementById('piechart4'));
			chart.draw(data, options);
		}
		
		
		<% 
		String label="";
		
		String data5="";
		String data6="";
		for(Object[] obj : (List<Object[]>) request.getAttribute("jobsLine") ) {
			label+="'"+obj[0]+"',";
			data5+=""+obj[1]+",";
			data6+=""+obj[2]+",";
		}
    	%>
		
		function LineChart() {
			 "use strict";
			 
				 /*----------------------------------------*/
				/*  1.  Basic Line Chart
				/*----------------------------------------*/
				var ctx = document.getElementById("basiclinechart");
				var basiclinechart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: [<%= label%>],
						datasets: [{
							label: "Jobs count",
							fill: false,
			                backgroundColor: '#00c292',
							borderColor: '#00c292',
							data: [<%= data5%>]
			            }, {
			                label: "Cost",
							fill: false,
			                backgroundColor: '#fb9678',
							borderColor: '#fb9678',
							data: [<%= data6%>]
							
					}]
					},
					options: {
						responsive: true,
						title:{
							display:true,
							text:'Jobs/Cost per Day'
						},
						tooltips: {
							mode: 'index',
							intersect: false,
						},
						hover: {
							mode: 'nearest',
							intersect: true
						},
						scales: {
							xAxes: [{
								display: true,
								scaleLabel: {
									display: true,
									labelString: 'Days'
								}
							}],
							yAxes: [{
								display: true,
								scaleLabel: {
									display: true,
									labelString: 'Cost/jobs number'
								}
							}]
						}
					}
				});
			}
		
		
		
	</script>


	
</body>

</html>
