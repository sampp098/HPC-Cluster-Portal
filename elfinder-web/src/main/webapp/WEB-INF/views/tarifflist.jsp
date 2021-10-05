<%@page import="bab.mvc.data.entities.pure.HPCTariff"%>
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

<title>Tariff List</title>

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

<script src="/js/jquery-1.12.4.js"></script>
<script src="/js/jquery-ui.js"></script>
<script>
  function deactive_tariff(id) {
	 var SplitText = "Deactive Tariff?";
	 var $dialog = $('<div></div>').html(SplitText ).dialog({
      resizable: false,
      height: "auto",
      width: 400,
      modal: true,
      autoOpen: false,
      buttons: {
        "Deactive": function() {
          $( this ).dialog( "close" );
          console.log('submit');
          console.log(id);
          document.getElementById(id).submit();
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
    });
	 $dialog.dialog('open');
	 $dialog.html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>This Tariff will be deactived. Are you sure?</p>');
  }

  function active_tariff(id) {
		 var SplitText = "Active Tariff?";
		 var $dialog = $('<div></div>').html(SplitText ).dialog({
	      resizable: false,
	      height: "auto",
	      width: 400,
	      modal: true,
	      autoOpen: false,
	      buttons: {
	        "Active": function() {
	          $( this ).dialog( "close" );
	          console.log('submit');
	          console.log(id);
	          document.getElementById(id).submit();
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
		 $dialog.dialog('open');
		 $dialog.html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Do you want to active tis Tariff?</p>');
	  }
</script>

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
							<i class="fa fa-laptop"></i>Tariff List
						</h3>
						<a href="<spring:url value="/user/hpchome"/>">Back</a>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              				<li><i class="icon_document_alt"></i>Tariff</li>
              				<li><i class="fa fa-files-o"></i>Tariff List</li>
						</ol>
					</div>
				</div>
				<!--/.row-->


				<div class="row">

					<!-- users table -->
					<div class="col-lg-12 col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-flag-o red"></i><strong>Tariff List</strong>
								</h2>
							</div>
							<div class="panel-body">

								<table class="table bootstrap-datatable countries" id="tarifftable">
									<thead>
										<tr>
											<th>#</th>
											<th>tariff name</th>
											<th>cost per core</th>
											<th>max nodes</th>
											<th>max cores</th>
											<th>max memory</th>
											<th>max storage</th>
											<th>min charge</th>
											<th></th>
											
										</tr>
									</thead>
									<tbody>
									
									<!-- users list -->
									<%
										List<HPCTariff> fg= (List<HPCTariff>) request.getAttribute("tariffs");
									%>
									
									<%
									int i=0;
									for(HPCTariff u: fg ){
									%>
										<!-- a row in table -->
										
										<tr>
											
												<td><%= u.getHpctid() %> </td>
												<td><%= u.getName() %> </td>
												<td><%=u.getCorecost()%></td>
												<td><%= u.getMaxnodes() %></td>
												<td><%= u.getMaxcores() %></td>
												<td><%= u.getMaxmemory() %></td>
												<td><%= u.getMaxstorage() %></td>
												<td><%= u.getMincharge() %></td>
												<td>
	                      							<div class="btn-group">
	                      								<form action="<spring:url value="/tariff/tarifflist"/>" method="post">
	                      									<input type="hidden"  name="hpctid" value="<%= u.getHpctid() %>"> 
	                        								<input  type="submit" name="button" value="edit"   class="btn btn-warning btn-sm" />
	                        							</form>
	                        							<form id="myform<%=i %>" action="<spring:url value="/tariff/active"/>" method="post">
                      										<input type="hidden"  name="hpctid" value="<%= u.getHpctid() %>">
                      										<% if(u.getActive()==1){%>
                        										<input  type="button" name="button" value="deactivate" class="btn btn-danger btn-sm" onclick='deactive_tariff("myform<%=i++ %>")' />
                        									<%} else{ %>
                        										<input  type="button" name="button" value="activate" class="btn btn-primary btn-sm" onclick='active_tariff("myform<%=i++ %>")' />
                        									<%} %>
                      									</form>
	                      							</div>
	                    						</td>
										</tr>
										<!-- end a row in table -->
									<% i++;
									} %>
									</tbody>
								</table>
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
		$('#tarifftable').DataTable();
		} );
	</script>
	
</body>

</html>
