package bab.mvc.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bab.mvc.data.entities.pure.Applications;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.ApplicationsService;
import bab.mvc.data.services.HPCTariffService;
import bab.mvc.data.services.PermissionsService;


@Controller
@RequestMapping("/apps")
public class AppController {
	
	
	@RequestMapping(value="/appslist",method=RequestMethod.GET)
	public String appsList(HttpSession session,Model model, HttpServletRequest request) {
		
		System.out.println("invoking Applications appslist :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		List<Applications> apps=new ApplicationsService().ReadAll();
		model.addAttribute("apps", apps);
		return "appslist";
	}
	
	
	@RequestMapping(value="/editapp",method=RequestMethod.POST)
	public String editapp(HttpSession session,Model model, HttpServletRequest request, @RequestParam int appid) {
		
		System.out.println("invoking Applications editapp :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		List<Applications> apps=new ApplicationsService().ReadByAppidAll(appid);
		Applications app=apps.get(0);
		app.setScripts(app.getScripts().replace("\\\"","\"").replace("\\$","$").replace("\\`","`"));
		app.setExports(app.getExports().replace("\\\"","\"").replace("\\$","$").replace("\\`","`"));
		
		
		model.addAttribute("app", app);
		return "editapp";
	}
	
	@RequestMapping(value="/activeapp",method=RequestMethod.POST)
	public String activeapp(HttpSession session,Model model, HttpServletRequest request, @RequestParam int appid) {
		
		System.out.println("invoking Applications editapp :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		List<Applications> apps=new ApplicationsService().ReadByAppidAll(appid);
		Applications app=apps.get(0);
		app.setActive(app.getActive()==0?1:0);
		new ApplicationsService().update(app);
		
		return "redirect:/apps/appslist";
	}
	
	@RequestMapping(value="/doeditapp",method=RequestMethod.POST)
	public String doEditApp(HttpSession session,HttpServletRequest request,Model model,
														    @RequestParam int	   appid,
															@RequestParam String   appname,
															@RequestParam String[] script,
															@RequestParam String[] export,
															@RequestParam String[] type){
		System.out.println("invoking Applications donewapp :post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		Applications app=new Applications();
		app.setAppid(appid);
		app.setAppname(appname);
		
		String scripts="";
		String exports="";
		String types="";
		for (String s : script) {
			scripts+="::"+s;
			System.out.println("script:"+s);
		}
		if(scripts.length()>2) {
			app.setScripts(scripts.substring(2));
		}else {
			app.setScripts(scripts);
		}
		
		
		for (String e : export) {
			exports+="::"+e;
			System.out.println("export:"+e);
		}
		if(exports.length()>2) {
			app.setExports(exports.substring(2));
		}else {
			app.setExports(exports);
		}
		
		
		for (String t : type) {
			types+="::"+t;
			System.out.println("type:"+t);
		}
		if(types.length()>2) {
			app.setFiles(types.substring(2));
		}else {
			app.setFiles(types);
		}
		
		new ApplicationsService().update(app);
		
		return "redirect:/apps/appslist";
	}
	
	@RequestMapping(value="/newapp",method=RequestMethod.GET)
	public String newApp(HttpSession session,Model model, HttpServletRequest request) {
		
		System.out.println("invoking Applications newapp :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		return "newapp";
	}
	
	@RequestMapping(value="/donewapp",method=RequestMethod.POST)
	public String doNewApp(HttpSession session,HttpServletRequest request,Model model,
															@RequestParam String   appname,
															@RequestParam String[] script,
															@RequestParam String[] export,
															@RequestParam String[] type){
		System.out.println("invoking Applications donewapp :post ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		Applications app=new Applications();
		app.setAppname(appname);
		
		String scripts="";
		String exports="";
		String types="";
		for (String s : script) {
			scripts+="::"+s.replace("\"","\\\"").replace("$", "\\$").replace("`", "\\`");
			System.out.println("script:"+s);
		}
		if(scripts.length()>2) {
			app.setScripts(scripts.substring(2));
		}else {
			app.setScripts(scripts);
		}
		
		
		for (String e : export) {
			exports+="::"+e.replace("\"","\\\"").replace("$", "\\$").replace("`", "\\`");
			System.out.println("export:"+e);
		}
		if(exports.length()>2) {
			app.setExports(exports.substring(2));
		}else {
			app.setExports(exports);
		}
		
		
		for (String t : type) {
			types+="::"+t;
			System.out.println("type:"+t);
		}
		if(types.length()>2) {
			app.setFiles(types.substring(2));
		}else {
			app.setFiles(types);
		}
		app.setActive(1);
		new ApplicationsService().create(app);
		
		return "redirect:/apps/appslist";
	}
}
