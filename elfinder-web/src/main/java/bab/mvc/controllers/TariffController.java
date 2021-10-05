package bab.mvc.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bab.mvc.data.entities.pure.HPCTariff;
import bab.mvc.data.services.HPCTariffService;
import bab.mvc.data.services.PermissionsService;
import bab.mvc.data.ssh.TariffSSH;


@Controller
@RequestMapping("/tariff")
public class TariffController {
	
	
	@RequestMapping(value="/tarifflist",method=RequestMethod.GET)
	public String tarifflist(HttpSession session,Model model, HttpServletRequest request) {
		
		System.out.println("invoking tariff list :get ");
		//int uid=((User) session.getAttribute("currentuser")).getUid();
		
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tarifflist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		model.addAttribute("tariffs", new HPCTariffService().readAll());
		return "tarifflist";
	}
	
	@RequestMapping(value="/tarifflist", method=RequestMethod.POST)
	public String edittariff(HttpSession session,Model model,@RequestParam int hpctid) {
		System.out.println("invoking tarifflist :Post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tarifflist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		HPCTariff tariff= new HPCTariffService().ReadByHPCTid(hpctid).get(0);
		model.addAttribute("tariff",tariff);
		return "edittariff";
	}
	@RequestMapping(value="/active", method=RequestMethod.POST)
	public String activeTariff(HttpSession session,Model model,@RequestParam int hpctid) {
		System.out.println("invoking activeTariff /active :Post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tarifflist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		HPCTariff tariff= new HPCTariffService().ReadByHPCTid(hpctid).get(0);
		if(tariff.getActive()==1) {
			tariff.setActive(0);
		}else {
			tariff.setActive(1);
		}
		new HPCTariffService().update(tariff);
		return "redirect:/tariff/tarifflist";
	}
	@RequestMapping(value="/newtariff",method=RequestMethod.GET)
	public String newtariff(HttpSession session,Model model, HttpServletRequest request) {
		
		System.out.println("invoking new tariff :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		//int uid=((User) session.getAttribute("currentuser")).getUid();
		String name=request.getParameter("name");
		model.addAttribute("tariffs", new HPCTariffService().read(name));
		return "newtariff";
	}
	
	@RequestMapping(value="/newtariff",method=RequestMethod.POST)
	public String newtariff(HttpSession session,HttpServletRequest request,Model model,@RequestParam String name,
															//@RequestParam String queue,
															@RequestParam int corecost,
															@RequestParam int memcost,
															@RequestParam int mincharge,
															@RequestParam int maxnodes,
															@RequestParam int maxcores,
															@RequestParam int maxmemory,
															@RequestParam int maxstorage,
															@RequestParam int queuesize,
															@RequestParam int maxuserjobs,
															@RequestParam int maxusercjobs,
															@RequestParam int maxcputime,
															@RequestParam int maxwalltime) {
		
		System.out.println("invoking new tariff :post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}

		
		if(new TariffSSH().tariffAdd(name, maxnodes+1, maxcores, maxmemory, queuesize, maxuserjobs, maxusercjobs, maxcputime, maxwalltime)) {	
			
			
			int hpctid=new HPCTariffService().create(new HPCTariff(name, corecost,memcost, maxnodes, maxcores, maxmemory, maxstorage, mincharge, queuesize, maxuserjobs, maxusercjobs, maxcputime, maxwalltime,1));
			if(hpctid==-1) {
				model.addAttribute("message", "Please choose a unique name for tariff and try again!");
				return "errorpage";
			}
		}
		return "redirect:/tariff/tarifflist";
	}
	@RequestMapping(value="/edittariff",method=RequestMethod.POST)
	public String edittariff(HttpSession session,HttpServletRequest request,Model model,@RequestParam int hpctid,
			@RequestParam int corecost,
			@RequestParam int mincharge,
			@RequestParam int maxnodes,
			@RequestParam int maxcores,
			@RequestParam int maxmemory,
			@RequestParam int maxstorage,
			@RequestParam int queuesize,
			@RequestParam int maxuserjobs,
			@RequestParam int maxusercjobs,
			@RequestParam int maxcputime,
			@RequestParam int maxwalltime) {
		
		System.out.println("invoking new tariff :post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffupdate")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		HPCTariff tariff=new HPCTariffService().ReadByHPCTid(hpctid).get(0);
		
		if(new TariffSSH().tariffAdd(tariff.getName(), maxnodes+1, maxcores, maxmemory, queuesize, maxuserjobs, maxusercjobs, maxcputime, maxwalltime)) {
			//tariff.setQueue(queue);
			tariff.setCorecost(corecost);
			tariff.setMincharge(mincharge);
			tariff.setMaxnodes(maxnodes);
			tariff.setMaxcores(maxcores);
			tariff.setMaxmemory(maxmemory);
			tariff.setMaxstorage(maxstorage);
			tariff.setQueuesize(queuesize);
			tariff.setMaxuserjobs(maxuserjobs);
			tariff.setMaxusercjobs(maxusercjobs);
			tariff.setMaxcputime(maxcputime);
			tariff.setMaxwalltime(maxwalltime);
		}
		
		new HPCTariffService().update(tariff);
		return "redirect:/tariff/tarifflist";
	}
}
