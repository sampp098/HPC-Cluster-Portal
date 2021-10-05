package bab.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.ApplicationsService;
import bab.mvc.data.services.FeesGroupService;
import bab.mvc.data.services.HPCTariffService;
import bab.mvc.data.services.JobsService;
import bab.mvc.data.services.ReportsService;
import bab.mvc.data.services.UserService;
import bab.mvc.data.ssh.Qstat;

import java.util.List;

import java.util.logging.Logger;

@Controller
@RequestMapping("/reports")
public class ReportController {
	private static final Logger logger = Logger.getLogger(ReportController.class.getName());
	
	@Autowired
	public UserService userDBService;
	
	
	
/*	@Autowired
	PortalProperties properties;*/
	
	@Autowired
	public ApplicationsService applicationsDBService;
	
	@Autowired
	public HPCTariffService tariffDBService;
	
	@Autowired
	public JobsService jobsDBService;
	
/*	@Autowired
	public JobSSHService jobSSHService;*/
	
	@Autowired
	public FeesGroupService feesGroupDBService;
	
	@Autowired
	public ReportsService reportsDBService;
	
	//**************User*************************
	@RequestMapping(value="/reportuser",method=RequestMethod.GET)
	public String reportUser(Model model,HttpSession session, HttpServletRequest request) {
		
		logger.info("invoking reportuser ");
		User u=(User) session.getAttribute("currentuser");
		if(u==null) {
			return "redirect:/user/login";
		}
		
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		model.addAttribute("jobs",reportsDBService.getUserJobsWithCost(u.getUid()));
		model.addAttribute("jobsPie",reportsDBService.getUserJobsPie(u.getUid()));
		model.addAttribute("jobsLine",reportsDBService.getUserJobsCostPerDay(u.getUid()));
		
		model.addAttribute("member", u);
		
		return "reportuser";
	}
	//**************Owner*************************
	@RequestMapping(value="/reportgroup",method=RequestMethod.GET)
	public String reportGroup(Model model,HttpSession session, HttpServletRequest request) {
		
		logger.info("invoking reportgroup ");
		User u=(User) session.getAttribute("currentuser");
		if(u==null) {
			return "redirect:/user/login";
		}
		
		//Qstat.applyMonitorUser(u);
		List<FeesGroup> fg= feesGroupDBService.ReadByOwnerUid(u.getUid());
		if(fg !=null && fg.size()>0) {
			model.addAttribute("owner",userDBService.getUserByUid(fg.get(0).getUid()));
			model.addAttribute("jobs",reportsDBService.getOwnerJobsWithCost(fg.get(0).getFgid()));
			model.addAttribute("jobsPie",reportsDBService.getOwnerJobsPie(fg.get(0).getFgid()));
			model.addAttribute("jobsLine",reportsDBService.getOwnerJobsCostPerDay(fg.get(0).getFgid()));
			model.addAttribute("group", fg.get(0));
		}
		
		return "reportgroup";
	}
	@RequestMapping(value="/reportmember",method=RequestMethod.POST)
	public String reportUser(Model model,HttpSession session, HttpServletRequest request,@RequestParam int uid) {
		
		logger.info("invoking reportmember ");
		User u=(User) session.getAttribute("currentuser");
		if(u==null) {
			return "redirect:/user/login";
		}
		User member= userDBService.getUserByUid(uid);
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		model.addAttribute("jobs",reportsDBService.getUserJobsWithCost(uid));
		model.addAttribute("jobsPie",reportsDBService.getUserJobsPie(uid));
		model.addAttribute("jobsLine",reportsDBService.getUserJobsCostPerDay(uid));
		model.addAttribute("member", member);
		
		return "reportuser";
	}
	//**************Admin*************************
	@RequestMapping(value="/reportgroups",method=RequestMethod.GET)
	public String reportGroups(Model model,HttpSession session, HttpServletRequest request) {
		
		logger.info("invoking reportgroup ");
		User u=(User) session.getAttribute("currentuser");
		if(u==null) {
			return "redirect:/user/login";
		}
		
		//Qstat.applyMonitorUser(u);

		model.addAttribute("jobs",reportsDBService.getAdminJobsWithCost());
		model.addAttribute("jobsPie",reportsDBService.getAdminJobsPie());
		model.addAttribute("jobsLine",reportsDBService.getAdminJobsCostPerDay());

		return "reportgroups";
	}
	
	@RequestMapping(value="/reportgroup",method=RequestMethod.POST)
	public String reportGroup(Model model,HttpSession session, HttpServletRequest request, @RequestParam int fgid) {
		
		logger.info("invoking reportgroup ");
		User u=(User) session.getAttribute("currentuser");
		if(u==null) {
			return "redirect:/user/login";
		}
		
		//Qstat.applyMonitorUser(u);

		List<FeesGroup> fg= feesGroupDBService.ReadByfgid(fgid);
		
		model.addAttribute("owner",userDBService.getUserByUid(fg.get(0).getUid()));
		model.addAttribute("jobs",reportsDBService.getOwnerJobsWithCost(fgid));
		model.addAttribute("jobsPie",reportsDBService.getOwnerJobsPie(fgid));
		model.addAttribute("jobsLine",reportsDBService.getOwnerJobsCostPerDay(fgid));
		model.addAttribute("group", fg.get(0));
		
		return "reportgroup";
	}
	
	//AJAX Controller
		/*@RequestMapping(value="/nodesstatus",method=RequestMethod.GET)
		@ResponseBody
		public List<DayLog> nodesStatus(HttpSession session, Model model) {
			logger.info("invoking gettemp Report :get ");
			//User u = (User) session.getAttribute("currentuser");
			
			
			//List<Object[]> obj=dayLogDBService.ReadNodeTemp(18);
			List<DayLog> obj=dayLogDBService.ReadNodesLog();
			
			return obj;
		}
		
		@RequestMapping(value="/reportnodes",method=RequestMethod.GET)
		public String reportnodes(Model model,HttpSession session, HttpServletRequest request) {
			
			logger.info("invoking reportgroup ");
			User u=(User) session.getAttribute("currentuser");
			if(u==null) {
				return "redirect:/user/login";
			}
			
			model.addAttribute("nodes",new DayLogDBService().getNodes());
			
			return "reportnodes";
		}
		*/
		
}
