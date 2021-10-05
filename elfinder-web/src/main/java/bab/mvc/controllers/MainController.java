package bab.mvc.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bab.mvc.data.entities.pure.News;

import bab.mvc.data.services.NewsService;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("/")
	public String greeting(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("--------------------> in MainController greeting");
		//model.addAttribute("currentProject", project);
		if(request.getParameter("username")!=null && request.getParameter("key")!=null) {
			System.out.println("--------------------> username and key found! it is registration complete requset!");
		}
		
		if(session.getAttribute("currentuser")!=null) {
			List<News> news =new NewsService().readAvailable(new Date());
			model.addAttribute("news", news);
			return "hpchome";
		}
		return "login";
	}
	/*@RequestMapping("/test")
	public String test(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("--------------------> in test");

		return "shell";
	}
	

	@RequestMapping("/myshell")
	//@ResponseBody
	public String shell() {
		System.out.println("--------------------> in test");
		return "shell";
	}*/
}
