
<%@page import="bab.mvc.data.entities.pure.HPCLimitations"%>
<%@page import="bab.mvc.data.entities.pure.FeesGroup"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="bab.mvc.data.entities.pure.Applications"%>
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
  <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
  <meta name="author" content="GeeksLabs">
  <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
  <link rel="shortcut icon" href="img/favicon.png">

  <title>New HPC Group</title>

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
            <h3 class="page-header"><i class="fa fa-files-o"></i>New Group</h3>
            <a href="<spring:url value="/user/hpchome"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>Groups</li>
              <li><i class="fa fa-files-o"></i>New HPC Group</li>
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
              <% System.out.println("------#########--------------------------------->"+getClass().getResource(getClass().getSimpleName() + ".class")); %>
              <%
              HPCLimitations hpcLimitations=(HPCLimitations) request.getAttribute("hpcLimitation");
			  List<User> us= (List<User>) request.getAttribute("users");
			  
              String fgname="";
              double charge=0;
              //int qid=1;
              //int fid=0;
              //String feename="";
              String corecost="";
              String memcost="";
              //String storagecost="";
              if((int)request.getAttribute("type")==1){
              	System.out.println("invoking newhpcgroup JSP : type:1 ");
              	fgname=(String)request.getAttribute("fgname");
              	charge=(double)request.getAttribute("charge");
              	//qid=(int)request.getAttribute("qid");
              	//fid=(int)request.getAttribute("fid");   
              	           
              }else if((int)request.getAttribute("type")==2){
              	fgname=(String)request.getAttribute("fgname");
              	charge=(double)request.getAttribute("charge");
              	//qid=(int)request.getAttribute("qid");
              	//fid=(int)request.getAttribute("fid");
              	
              	//feename=(String)request.getAttribute("feename");
              	corecost=(String)request.getAttribute("corecost");
              	memcost=(String)request.getAttribute("memcost");
              	//storagecost=(String)request.getAttribute("storagecost");
              	//System.out.println("invoking newhpcgroup JSP : type:2 feename:"+feename+" "+corecost+" "+memcost+" "+" "+storagecost);
              }
              %>
              
              
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="register_form" action="<spring:url value="/group/newhpcgroup"/>" method="post">					
                    <div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Fee Group Name<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="name" type="text" name="fgname" value="${fgname}" required 
									minlength="5"
									maxlength="20"
									
								/>
							</div>
						</div>
						
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">First Charge<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="charge" type="number" name="charge" value="${charge}" required 
									min="0"
									step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+"
								/>
							</div>
						</div>
		
					</div>	

					<div >
						<!-- <div>
							
							<label value="first name">first name <input type="text" name="sfname" /></label>
							<label value="last name">last name <input type="text" name="slname" /></label>
							<label value="user name">user name <input type="text" name="susername" /></label>
							<input name="button" type="submit" value="search"   class="btn btn-primary" />
						</div> -->
						<table class="table bootstrap-datatable countries" id="userstable">
							<thead>
								<tr>
									<th>selected</th>
									<th>#</th>
									<th>Logo</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>User Name</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>							
							<%
							int i=1;
							for(User u: us ){
							%>
								<tr>
									<td><input type="radio"  name="ownerid" value="<%= u.getUid() %>"></td>
									<td><input type="hidden"  name="uid" value="<%= u.getUid() %>"><%= ""+i++%> </td>
									<td><img src="<%="/user/imageController/"+u.getUid()+"/myicon" %>" style="width: 40px; height: 40px; margin-top: -2px;"></td>
									<td><input type="hidden"  name="fname" value="<%= u.getFname() %>"><%= u.getFname() %> </td>
									<td><input type="hidden"  name="lname" value="<%= u.getLname() %>"><%= u.getLname() %></td>
									<td><input type="hidden"  name="username" value="<%= u.getUserName() %>"><%= u.getUserName() %></td>
									<td><input type="hidden"  name="email" value="<%= u.getEmail() %>"><%= u.getEmail() %></td>
								</tr>
							<% } %>
							</tbody>
						</table>
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
	
	<!-- <script src="/js/dataTable/jquery-3.3.1.js"></script> -->
	<script src="/js/dataTable/jquery.dataTables.min.js"></script>
	<script src="/js/dataTable/dataTables.bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
		$('#userstable').DataTable();
		} );
	</script>
	
</body>

</html>
