<%@page import="bab.mvc.Execute"%>
<%@page import="bab.mvc.data.entities.pure.Permissions"%>
<%@page import="java.util.List"%>
<%@page import="bab.mvc.data.services.PermissionsService"%>
<%@page import="java.util.Date"%>
<%@page import="bab.mvc.data.entities.pure.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<aside>
	  
	  <LINK REL="stylesheet" TYPE="text/css" 
 MEDIA="print" HREF="/css/hidemenu.css">
      <div id="sidebar" class="nav-collapse hidemenu">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu">
          <li class="active">
            <a class="" href="<spring:url value="/user/hpchome"/>">
                          <i class="icon_house_alt"></i>
                          <span>Dashboard</span>
                      </a>
          </li>
          
          <% 
          List<String> permissionList=(List<String>) session.getAttribute("permisions"); 
          User u=(User) session.getAttribute("currentuser");
          if(u.getIsactive()==1 && u.getExptime().getTime()>(new Date()).getTime()){
          
        	  
          /* ------------------------ User Menue */
          if(PermissionsService.exist(permissionList, "userlist") ){%>
          <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_contacts"></i>
                          <span>Users</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <li><a class="" href="<spring:url value="/user/userlist" />">Manage User</a></li>
            </ul>
          </li>
		 <%}
          
          /* ------------------------ Group Menue */
          if(PermissionsService.exist(permissionList, "hpcgrouplist") || PermissionsService.exist(permissionList, "hpcgroupnew")){ %>
		 <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_piechart"></i>
                          <span>Groups</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <%if(PermissionsService.exist(permissionList, "hpcgrouplist")){ %><li><a class="" href="/group/hpcgrouplist">HPC Groups</a></li><%} %>
              <%if(PermissionsService.exist(permissionList, "hpcgroupnew")){ %><li><a class="" href="/group/newhpcgroup">New Group</a></li><%} %>
            </ul>
          </li>
          
          <%}
          
          /* ------------------------ Tariff Menue */
          if(PermissionsService.exist(permissionList, "tariffnew") || PermissionsService.exist(permissionList, "tarifflist")){ %>
 		 <li class="sub-menu">
             <a href="javascript:;" class="">
                           <i class="icon_table"></i>
                           <span>Fees & Tariff</span>
                           <span class="menu-arrow arrow_carrot-right"></span>
                       </a>
             <ul class="sub">
               <%if(PermissionsService.exist(permissionList, "tarifflist")){ %><li><a class="" href="/tariff/tarifflist">HPC Tariffs</a></li><%} %>
               <%if(PermissionsService.exist(permissionList, "tariffnew")){ %><li><a class="" href="/tariff/newtariff">New HPC Tariff</a></li><%} %>
             </ul>
           </li>
           
           <%}
          /* ------------------------ Applications Menue */
          if(PermissionsService.exist(permissionList, "tariffnew") || PermissionsService.exist(permissionList, "tarifflist")){ %>
 		 <li class="sub-menu">
             <a href="javascript:;" class="">
                           <i class="icon_table"></i>
                           <span>Applications</span>
                           <span class="menu-arrow arrow_carrot-right"></span>
                       </a>
             <ul class="sub">
               <%if(PermissionsService.exist(permissionList, "tarifflist")){ %><li><a class="" href="/apps/appslist">Applications</a></li><%} %>
               <%if(PermissionsService.exist(permissionList, "tariffnew")){ %><li><a class="" href="/apps/newapp">New Application</a></li><%} %>
               
               
             </ul>
           </li>
           
           <%}
          
          /* ------------------------ Administration */
          if(PermissionsService.exist(permissionList, "tariffnew") || PermissionsService.exist(permissionList, "tarifflist")){ %>
 		 <li class="sub-menu">
             <a href="javascript:;" class="">
                           <i class="icon_table"></i>
                           <span>Administration</span>
                           <span class="menu-arrow arrow_carrot-right"></span>
                       </a>
             <ul class="sub">
               <%if(PermissionsService.exist(permissionList, "tarifflist")){ %><li><a class="" href="/admin/contactus">Edit Contact us</a></li><%} %>
               <%if(PermissionsService.exist(permissionList, "tariffnew")){ %><li><a class="" href="/admin/edithelp">Edit HELP Files</a></li><%} %>
			   <%if(PermissionsService.exist(permissionList, "tariffnew")){ %><li><a target="_blank" class="" href="<%=Execute.gangliaHome %>">Monitoring</a></li><%} %>
			   <%if(PermissionsService.exist(permissionList, "tariffnew")){ %><li><a target="_blank" class="" href="<spring:url value="/user/documentation/Help_admin.pdf" />" >Help Admin?</a></li><%} %>          
             </ul>
           </li>

           <%}
          
          /* ------------------------ Cluster Report */
          if(PermissionsService.exist(permissionList, "hpcgrouplist") || PermissionsService.exist(permissionList, "hpcgroupnew")){ %>
		 <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_piechart"></i>
                          <span>Cluster Report</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <%if(PermissionsService.exist(permissionList, "jobreport3")){ %><li><a class="" href="/reports/reportgroups">Cluster Report</a></li><%} %>
            </ul>
          </li>
          
          <%}
          
          /* ------------------------ My Group Menue */
          if(PermissionsService.exist(permissionList, "usermemberslist") || PermissionsService.exist(permissionList, "jobreport2") || PermissionsService.exist(permissionList, "jobmemberslist")){ %>
		 <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_piechart"></i>
                          <span>My Group</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <%if(PermissionsService.exist(permissionList, "usermemberslist")){ %><li><a class="" href="/group/myhpcgroupmembers">My Group Members</a></li><%} %>
              <%-- <%if(PermissionsService.exist(permissionList, "jobreport2")){ %><li><a class="" href="/job/mygroupreport">My Group Report</a></li><%} %> --%>
              <%if(PermissionsService.exist(permissionList, "jobreport2")){ %><li><a class="" href="/reports/reportgroup">My Group Report</a></li><%} %>
              <%--<%if(PermissionsService.exist(permissionList, "jobmemberslist")){ %><li><a class="" href="/jobs/mygroupjobs">My Group Jobs</a></li><%} %> --%>
            </ul>
          </li>
		 <%}
          
          /* ------------------------ Job Menue */
          if(PermissionsService.exist(permissionList, "jobnew") || PermissionsService.exist(permissionList, "jobmylist") || PermissionsService.exist(permissionList, "jobreport1")){
          %>
          <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_genius"></i>
                          <span>Jobs</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <%if(PermissionsService.exist(permissionList, "jobnew")){ %><li><a class="" href="<spring:url value="/job/attachfiles" />">Submit a Job</a></li><%} %>
              <%if(PermissionsService.exist(permissionList, "jobmylist")){ %><li><a class="" href="<spring:url value="/job/myjobs" />" >My Jobs List</a></li><%} %>
              <%if(PermissionsService.exist(permissionList, "jobreport1")){ %><li><a class="" href="<spring:url value="/reports/reportuser" />" >User Report</a></li><%} %>
              <%if(PermissionsService.exist(permissionList, "jobmylist")){ %><li><a class="" target="_blank" href="<spring:url value="/job/myfiles" />" >My Files</a></li><%} %>
            </ul>
          </li>
          <%}
          
          /* ------------------------ News Menue */
          if(PermissionsService.exist(permissionList, "newsnew") || PermissionsService.exist(permissionList, "newslist")){%>
          <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>News</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <%if(PermissionsService.exist(permissionList, "newsnew")){ %><li><a class="" href="<spring:url value="/messaging/createnews" />">Submit News</a></li><%} %>
              <%if(PermissionsService.exist(permissionList, "newslist")){ %><li><a class="" href="<spring:url value="/messaging/newslist" />" >News List</a></li><%} %>
            </ul>
          </li>
          <%}%>
          
          
          <!-- Help menu  /user/documentation/Help_users.pdf-->
          <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>Help</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <li><a target="_blank" class=""  href="<spring:url value="/user/documentation/Help_users.pdf" />">Help File</a></li>
              <li><a  class="" href="<spring:url value="/user/contact" />" >Contact us</a></li>
            </ul>
          </li>
		 <%
		 }%>
        </ul>
      <!-- sidebar menu end-->
      </div>
    </aside>
