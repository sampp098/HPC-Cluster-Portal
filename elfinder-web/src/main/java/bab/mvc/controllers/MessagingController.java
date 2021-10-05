package bab.mvc.controllers;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bab.mvc.data.entities.pure.News;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.NewsService;
import bab.mvc.data.services.PermissionsService;


@Controller
@RequestMapping("/messaging")
public class MessagingController {
	
	
	@RequestMapping(value="/newslist",method=RequestMethod.GET)
	public String newslist(HttpSession session, Model model) {
		
		System.out.println("invoking newslist news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newslist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		List<News> news =new NewsService().read();
		model.addAttribute("news", news);
		
		return "newslist";
	}

	@RequestMapping(value="/createnews",method=RequestMethod.GET)
	public String createnews(HttpSession session, HttpServletRequest request) {
		
		System.out.println("invoking createnews news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newsnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		return "createnews";
	}
	
	@RequestMapping(value="/createnews",method=RequestMethod.POST)
	public String createnews(HttpSession session,Model model,
												  @RequestParam String expire,
												  @RequestParam String title,
												  @RequestParam String text) throws Exception {
		
		System.out.println("invoking createnews news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newsnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		
		new NewsService().create(new News(title, text, uid, new Date(), new SimpleDateFormat("MM-dd-yyyy").parse(expire) ));
		
		List<News> news =new NewsService().read();
		model.addAttribute("news", news);
		
		return "newslist";
	}
	
	@RequestMapping(value="/updatenews",method=RequestMethod.GET)
	public String updatenews(HttpSession session, Model model, @RequestParam int nid) {
		
		System.out.println("invoking createnews news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newsnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		List<News> news =new NewsService().ReadByNid(nid);
		
		model.addAttribute("news",news.get(0));
		return "updatenews";
	}
	
	@RequestMapping(value="/doupdatenews",method=RequestMethod.POST)
	public String updatenews(HttpSession session,Model model,
												  @RequestParam String expire,
												  @RequestParam String title,
												  @RequestParam String text,
												  @RequestParam int nid) throws Exception {
		
		System.out.println("invoking createnews news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newsnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		News n=new News(title, text, uid, new Date(), new SimpleDateFormat("MM-dd-yyyy").parse(expire) );
		n.setNid(nid);
		new NewsService().update(n);
		
		
		
		
		List<News> news =new NewsService().read();
		model.addAttribute("news", news);
		
		return "newslist";
	}
	
	@RequestMapping(value="/deletenews",method=RequestMethod.POST)
	public String deletenews(HttpSession session,Model model,@RequestParam int nid ) throws Exception {
		
		System.out.println("invoking createnews news :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "newsdelete")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		new NewsService().delete(nid);
		
		return "redirect:/messaging/newslist";
	}
	
}
