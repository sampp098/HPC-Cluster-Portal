<%@page import="org.springframework.ui.Model"%>
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

<title>New Tariff</title>

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
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-files-o"></i>New Tariff</h3>
            <a href="<spring:url value="/user/hpchome"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>Tariff</li>
              <li><i class="fa fa-files-o"></i>New Tariff</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->

        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              
              <header class="panel-heading">
                <div class="row">
              					
				</div>
                
              </header>
                          
              
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="register_form" action="<spring:url value="/tariff/newtariff"/>" method="post">					
                    <div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Tariff Name<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="text" name="name" required 
									minlength="5"
									maxlength="14"
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max walltime (H)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="walltime" type="number" name="maxwalltime" required 
									min="0"
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>	
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">1 Core Cost (coin/h)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="corecost" required 
									min="0"
									pattern="[0-9]*"
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">1GB MEM Cost (coin/h)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="memcost" required 
									min="0"
									pattern="[0-9]*"
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max Nodes per job<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="maxnodes" required 
									min="1"
									max="2"
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max Cores per node<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="maxcores" required 
									min="1"
									max="88"
									pattern="[0-9]*"
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max Memory per node (GB)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="maxmemory" required 
									min="1"
									max="128"
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max Storage (GB)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="maxstorage" required 
									min="1"
									max="50"
									pattern="[0-9]*"
								
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Min Charge (Coins)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="mincharge" required 
									min="1"
									
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Queue size (jobs)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="queuesize" required 
								min="1"
								
								pattern="[0-9]*"
								
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max user queued jobs (jobs)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="maxuserjobs" required 
									min="1"
								
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max user concurrent jobs (jobs)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="number" name="maxusercjobs" required 
									min="1"
								
									pattern="[0-9]*"
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Max CPU time (H)<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="maxcputime" required 
									min="1"
								
									pattern="[0-9]*"
								/>
							</div>
						</div>
		
					</div>
					<span id="myForm"></span>
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button name="button" value="save" class="btn btn-primary" type="submit">submit</button>
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