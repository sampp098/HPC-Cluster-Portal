<%@page import="bab.mvc.Execute"%>
<%@page import="bab.mvc.data.services.GroupsService"%>
<%@page import="bab.mvc.data.entities.pure.GroupUsers"%>
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

  <title>Edit User</title>

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
            <h3 class="page-header"><i class="fa fa-files-o"></i> Profile for <%= ((User) request.getAttribute("selecteduser")).getUserName() %></h3>
            <a href="<spring:url value="/user/userlist"/>">Back</a>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="<spring:url value="/user/hpchome"/>">Home</a></li>
              <li><i class="icon_document_alt"></i>User</li>
              <li><i class="fa fa-files-o"></i>Edit User</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->

        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                <%= ((User) request.getAttribute("selecteduser")).getUserName() %>
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="register_form" action="<spring:url value="/user/edit"/>" method="post">
                    
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">First name <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="fname" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getFname() %>" readonly />
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Last name <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="lname" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getLname() %>" readonly />
							</div>
						</div>
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="cemail" class="control-label col-lg-3">Email<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="cemail" type="email" name="email" required value="<%= ((User) request.getAttribute("selecteduser")).getEmail() %>" readonly/>
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="username" class="control-label col-lg-3">Username <span class="required">*</span></label>
							<div class="col-lg-6">
								<input class="form-control " id="username" name="username" type="text" readonly value="<%= ((User) request.getAttribute("selecteduser")).getUserName() %>" readonly/>
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
								<input id="ncode" class=" form-control" id="fullname" name="nationalcode" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getNationalCode() %>" 
								required
								pattern="[0-9]*"
								minlength="10"
								maxlength="10"
								/>
							</div>
						</div>
					
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Grade<span class="required">*</span></label>
							<div class="col-lg-6">
								<select class="form-control m-bot15" name="grade">
									<% for(Grade g: (List<Grade>) request.getAttribute("grade")){%>
    									<option value="<%=g.getGradeid() %>" <% if(g.getGradeid()==((User) request.getAttribute("selecteduser")).getGradeid()){%> selected="selected" <%} %>>
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
    									<option value="<%=g.getUnid() %>" <% if(g.getUnid()==((User) request.getAttribute("selecteduser")).getUnid()){%> selected="selected" <%} %>>
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
    									<option value="<%=g.getFacultyid() %>" <% if(g.getFacultyid()==((User) request.getAttribute("selecteduser")).getFacultyid()){%> selected="selected" <%} %>>
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
								<input class=" form-control" id="fullname" name="field" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getField() %>"
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
										<option value="1" <%if(((User) request.getAttribute("selecteduser")).getState()==1){ out.write("selected=\"selected\""); }%> >profesor</option>
										<option value="2" <%if(((User) request.getAttribute("selecteduser")).getState()==2){ out.write("selected=\"selected\""); }%>>student</option>
										<option value="3" <%if(((User) request.getAttribute("selecteduser")).getState()==3){ out.write("selected=\"selected\""); }%>>none</option>
								</select>
							</div>
						</div>
					
					</div>
					
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Stu/Prof ID<span class="required">*</span></label>
							<div class="col-lg-6">
								<input class=" form-control" id="fullname" name="sn_pn" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getSN_PN() %>"
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
								<input class=" form-control" id="fullname" name="phone" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getPhone() %>"
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
							<input class=" form-control" id="address" name="address" type="text" value="<%= ((User) request.getAttribute("selecteduser")).getAddress() %>"
							required
							minlength="10"
							maxlength="200"
							/>
						</div>

					</div>
					
					
					<div class="form-group row col col-lg-12">

						<div class="col col-lg-6">
							<label for="f2" class="form-control-label">Personal image<span class="required">*</span></label>
							<!-- <div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im1');" type="file" id="f2" name="pimage">
							</div> -->
							
							<br>
    						<img align="middle" id="im1" src='<spring:url value="/user/imageController/"></spring:url><%=""+((User) request.getAttribute("selecteduser")).getUid()%>/myicon.jpg' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:300px; max-height: 200px;" />
						</div>
						
						<div class="col col-lg-6">
							<label for="f1" class="form-control-label">National Card<span class="required">*</span></label>
							<!-- <div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im2');" type="file" id="f1" name="nationalcard">
							</div> -->
							<br>
							<img align="middle" id="im2" src='<spring:url value="/user/imageController/"></spring:url><%=""+((User) request.getAttribute("selecteduser")).getUid()%>/myncard.jpg' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:300px; max-height: 200px;" />
						</div>
						
					</div>				
					<div class="form-group row col col-lg-12">

						<div class="col col-lg-6">
							<label for="address" class="form-control-label">st/prof card<span class="required">*</span></label>
							<!-- <div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im3');" type="file" id="exampleInputFile" name="st_pr_card">
							</div> -->
							<br>
							<img align="middle" id="im3" src='<spring:url value="/user/imageController/"></spring:url><%=""+((User) request.getAttribute("selecteduser")).getUid()%>/myst_pr_card.jpg' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:300px; max-height: 200px;" />
						</div>
						
						<div class="col col-lg-6">
							<label for="address" class="form-control-label">Recommandation<span class="required">*</span></label>
							<!-- <div class="input-group">
								<input class="form-control" onchange="readURL(this,'#im4');" type="file" id="exampleInputFile" name="racommandation">
							</div> -->
							<br>
							<img align="middle" id="im4" src='<spring:url value="/user/imageController/"></spring:url><%=""+((User) request.getAttribute("selecteduser")).getUid()%>/myracommandation.jpg' alt="your image" style=" margin-left: auto; margin-right: auto; max-width:300px; max-height: 200px;" />
						</div>
					</div>
					
					
					<%
					List<String> grouplist=(List<String>) request.getAttribute("selectedusergroups");
					
					if(!GroupsService.exist(grouplist, "HPCUser") || !GroupsService.exist(grouplist, "Admin")){ %>
					<div class="row form-group col-lg-12">
						<%if(!GroupsService.exist(grouplist, "Admin")){%><div class="col-lg-6">
							<label for="admincheck" class="control-label">is Admin?<span class="required">*</span><input class=" form-control" id="admincheck" name="isadmin" type="checkbox" /></label>
							
						</div>
						<%} if(!GroupsService.exist(grouplist, "HPCUser")){ %>
						<div class="col-lg-6">
							<label for="usercheck" class="control-label">is HPC User?<span class="required">*</span><input class=" form-control" id="usercheck" name="isuser" type="checkbox" /></label>	
						</div>
						<%}%>
					</div>
					<%}%>
					<div class="row form-group col-lg-12">
						<div class="col-lg-6">
							<label for="fullname" class="control-label col-lg-3">Expire Time<span class="required">*</span></label>
							<div class="col-lg-4 input-append date" id="dpYears" data-date="<%=((User) request.getAttribute("selecteduser")).getExptime().toString().split(" ")[0] %>" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
								<input name="expire" class="form-control" size="16" type="text" value="<%=((User) request.getAttribute("selecteduser")).getExptime().toString().split(" ")[0] %>" readonly>
								<span class="add-on"><i class="icon-calendar"></i></span>
							</div>
						</div>
					
						<div class="col-lg-6 radios">
							<label class="label_radio col-lg-3" for="radio-02">
                                <input name="active" id="radio-01" value="1" type="radio" <% if(((User) request.getAttribute("selecteduser")).getIsactive()==1){ %> checked <%}%> />active
                            </label>
                            <label class="label_radio col-lg-3" for="radio-02">
                                <input name="active" id="radio-02" value="0" type="radio" <% if(((User) request.getAttribute("selecteduser")).getIsactive()==0){ %> checked <%}%>  /> inactive
                            </label>
							
						</div>
						
						
					</div>
					
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-primary" type="submit" onclick="return checkCodeMeli(document.getElementById('ncode').value)">Save</button>
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
  <script src="/js/codemeli.js"></script>

</body>

</html>
