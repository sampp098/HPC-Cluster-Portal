<%@page import="bab.mvc.data.entities.pure.HPCTariff"%>
<%@page import="com.jcraft.jsch.ChannelSftp.LsEntry"%>
<%@page import="java.util.Vector"%>

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

  <title>New Job</title>

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

<style type="text/css">

textarea {
    resize: none;
}

#count_message {
  background-color: smoke;
  margin-top: -20px;
  margin-right: 5px;
}

</style>

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
            <h3 class="page-header"><i class="fa fa-files-o"></i>New <font color="black">'<%= ((Applications) request.getAttribute("application")).getAppname() %>'</font> Job</h3>
            <a href="<spring:url value="/job/attachfiles"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>Job</li>
              <li><i class="fa fa-files-o"></i>New Job</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->

        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              
              <header class="panel-heading">
                <div class="row">
                    <% FeesGroup feesgroup=(FeesGroup)request.getAttribute("feesgroup"); %>
                    <% User owner=(User)request.getAttribute("owner"); %>
                    <% List<HPCTariff> tariff=(List<HPCTariff>)request.getAttribute("tariff"); %>
                    <% Vector<LsEntry> ls=(Vector<LsEntry>) request.getAttribute("ls");%>
                    <% String scripts=(String) request.getAttribute("scripts");%>
                    <div class="row col-lg-12">
                    	
							<h4>
							<div><label for="cemail" class="col-lg-2">group name: </label><label> <%= feesgroup.getFgname() %></label> </div>
							<div><label for="cemail" class="col-lg-2">group owner:</label><label> <%= owner.getFname()+" "+owner.getLname() %></label></div>
							<div><label for="cemail" class="col-lg-2">charge: </label><label><%= feesgroup.getCharge() %></label></div>						
							</h4>
					</div>
					
				</div>
                
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="register_form" action="<spring:url value="/job/newjob"/>" method="post">
						
					
					<div>
						<div class="col-lg-12">
								
								<P>select Tariff: <select class="round-input" style="text-align:center;" id="hpctid" name="hpctid">
														<% for(HPCTariff t: tariff){%>
    														<option value="<%=t.getHpctid() %>" >
    														<%= String.format("%s",t.getName()) %>
    														</option>
														<%} %>
													</select></P>
								<div id="decrib" style="border-left: 6px solid #009900; background-color: #dbffbd;border-radius: 12px; padding: 10px;">
									<font color="black" size="5px" >
										<p>1 core cost (Coin/h)=</p>
										<p>1GB mem cost (Coin/h)=</p>
										<p>max waltime (h)=</p>
										<p>max nodes =     max cores per node =  max memory (GB)=</p>
									</font>
								</div>
								
								<br>
								<p>
								memory (GB)= <input  class="round-input" style="text-align:center;" placeholder="memory" type="number" id="memnum" name="memory" />
								,&emsp;nodes=<input  class="round-input" style="text-align:center;" placeholder="nodes" type="number" id="nodenum" name="nodes" />
								&emsp; cores per node=<input  class="round-input" style="text-align:center;" placeholder="cores" type="number" id="corenum" name="cores" /></p>
								
								<p>walltime (hour)= <input class="round-input" style="text-align:center;" placeholder="wall time" type="number" id="walltime_script" name="hour"/></p>
								
								<p id="app_script">job name &emsp;<input  class="round-input" style="text-align:center;" placeholder="job name" type="text" name="jname" required minlength="1" maxlength="10"/> </p>
								<input type="hidden" name="appid" value="<%= ((Applications) request.getAttribute("application")).getAppid() %>" ></input>
								<% List<String> appScripts=(List<String>) request.getAttribute("appscripts"); %>
								<%for(String script: appScripts) {%>
									<p><%=script %></p>
								<%} %>
								
								
								
									<div class="col-lg-12 form-group">
										<label><input id="advanced" name="advanced" type="checkbox" onclick="advancedMode(); count();">
											Advanced (file1,file2,... are your input files)
										</label>
									
										<div id="advancedtext" class="col-lg-12 form-group">
											<textarea onkeypress="count();" onkeyup="count();" maxlength="2000" id="text" class="round-inpu col-lg-9" rows="4" cols="70" name="editscripts"><%= scripts%></textarea>
											<h6 class="pull-right label label-default" id="count_message">0/2000</h6>
      										<br>
										</div>

								
						</div>
											

					</div>
					
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                      <br>
                        <button class="btn btn-primary" type="submit">submit</button>
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
	
	
	<script type="text/javascript">
	var selectedId=document.getElementById("hpctid").value;
	
	var maxmem= new Array();
	var maxcores= new Array();
	var maxnodes= new Array();
	var maxwalltime= new Array();
	
	var mamcost= new Array();
	var corecost= new Array();
	
	<% for(HPCTariff t: tariff){%>
		maxmem[<%= t.getHpctid()%>]=<%= t.getMaxmemory()%>;
		maxcores[<%= t.getHpctid()%>]=<%= t.getMaxcores()%>;
		maxnodes[<%= t.getHpctid()%>]=<%= t.getMaxnodes()%>;
		maxwalltime[<%= t.getHpctid()%>]=<%= t.getMaxwalltime()%>;
		
		mamcost[<%= t.getHpctid()%>]=<%= t.getMemcost()%>;
		corecost[<%= t.getHpctid()%>]=<%= t.getCorecost()%>;
	<%}%>
	
	document.getElementById("memnum").outerHTML = "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"memory\" type=\"number\" id=\"memnum\" name=\"memory\" value=\"1\"                  min=\"1\" max=\""+maxmem[selectedId]+"\" pattern=\"[0-9]*\" required/>";
	document.getElementById("nodenum").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"nodes\" type=\"number\" id=\"nodenum\" name=\"nodes\"  value=\"1\"                  min=\"1\" max=\""+maxnodes[selectedId]+"\" pattern=\"[0-9]*\" required/>";
	document.getElementById("corenum").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"cores\" type=\"number\" id=\"corenum\" name=\"cores\"  value=\"1\"                  min=\"1\" max=\""+maxcores[selectedId]+"\" pattern=\"[0-9]*\" required/>";
	document.getElementById("walltime_script").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"wall time\" type=\"number\" id=\"walltime_script\" name=\"hour\" value=\"1\" min=\"1\" max=\""+maxwalltime[selectedId]+"\" pattern=\"[0-9]*\" required>";
	
	document.getElementById("decrib").outerHTML="<div id=\"decrib\" style=\"border-left: 6px solid #009900; background-color: #dbffbd;border-radius: 12px; padding: 10px;\">"
													+"<font color=\"black\" size=\"3px\" >"
													+"<p>1 core coset (Coin/h)= "+corecost[selectedId]+"</p>"
													+"<p>1GB mem coset (Coin/h)= "+mamcost[selectedId]+"</p>"
													+"<p>max waltime (h)="+maxwalltime[selectedId]+"</p>"
													+"<p>max nodes ="+maxnodes[selectedId]+"&emsp;max cores per node= "+maxcores[selectedId]+"&emsp;max memory (GB)= "+maxmem[selectedId]+"</p>"
													+"</font>"
												+"</div>";
	
	</script>
	
	<script type="text/javascript">
		document.getElementById("hpctid").onchange = function() {hpctChange()};
		function hpctChange() {
			
			var selectedId=document.getElementById("hpctid").value;
			
			var maxmem= new Array();
			var maxcores= new Array();
			var maxnodes= new Array();
			var maxwalltime= new Array();
			
			var mamcost= new Array();
			var corecost= new Array();
			
			<% for(HPCTariff t: tariff){%>
				maxmem[<%= t.getHpctid()%>]=<%= t.getMaxmemory()%>;
				maxcores[<%= t.getHpctid()%>]=<%= t.getMaxcores()%>;
				maxnodes[<%= t.getHpctid()%>]=<%= t.getMaxnodes()%>;
				maxwalltime[<%= t.getHpctid()%>]=<%= t.getMaxwalltime()%>;
				
				mamcost[<%= t.getHpctid()%>]=<%= t.getMemcost()%>;
				corecost[<%= t.getHpctid()%>]=<%= t.getCorecost()%>;
			<%}%>
			
			document.getElementById("memnum").outerHTML = "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"memory\" type=\"number\" id=\"memnum\" name=\"memory\" value=\"1\"                  min=\"1\" max=\""+maxmem[selectedId]+"\" pattern=\"[0-9]*\" required/>";
			document.getElementById("nodenum").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"nodes\" type=\"number\" id=\"nodenum\" name=\"nodes\"  value=\"1\"                  min=\"1\" max=\""+maxnodes[selectedId]+"\" pattern=\"[0-9]*\" required/>";
			document.getElementById("corenum").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"cores\" type=\"number\" id=\"corenum\" name=\"cores\"  value=\"1\"                  min=\"1\" max=\""+maxcores[selectedId]+"\" pattern=\"[0-9]*\" required/>";
			document.getElementById("walltime_script").outerHTML= "<input class=\"round-input\" style=\"text-align:center;\" placeholder=\"wall time\" type=\"number\" id=\"walltime_script\" name=\"hour\" value=\"1\" min=\"1\" max=\""+maxwalltime[selectedId]+"\" pattern=\"[0-9]*\" required>";
			
			document.getElementById("decrib").outerHTML="<div id=\"decrib\" style=\"border-left: 6px solid #009900; background-color: #dbffbd;border-radius: 12px; padding: 10px;\">"
															+"<font color=\"black\" size=\"3px\" >"
															+"<p>1 core coset (Coin/h)= "+corecost[selectedId]+"</p>"
															+"<p>1GB mem coset (Coin/h)= "+mamcost[selectedId]+"</p>"
															+"<p>max waltime (h)= "+maxwalltime[selectedId]+"</p>"
															+"<p>max nodes= "+maxnodes[selectedId]+"&emsp;max cores per node= "+maxcores[selectedId]+"&emsp;max memory (GB)="+maxmem[selectedId]+"</p>"
															+"</font>"
														+"</div>";
		}

		
		
		
		
		
	</script>
	<script>
		var myhtml =document.getElementById("advancedtext").innerHTML;
		
		mycheck = document.getElementById("advancedtext").innerHTML = "";
	
		function advancedMode(){
			
			if (document.getElementById("advanced").checked) {
				mycheck = document.getElementById("advancedtext").innerHTML = myhtml;
			
			} else {
				mycheck = document.getElementById("advancedtext").innerHTML = "";
			}
		}
		
		
		
		function count() {
		var text_max = 2000;
		$('#count_message').html('0 / ' + text_max );

		
			console.log('changed');
		  var text_length = $('#text').val().length;
		  var text_remaining = text_max - text_length;
		  
		  $('#count_message').html(text_length + ' / ' + text_max);
		};
		
	</script>

</body>

</html>
