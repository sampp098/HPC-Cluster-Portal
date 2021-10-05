package bab.mvc.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bab.mvc.Execute;
import bab.mvc.data.entities.pure.Applications;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.PermissionsService;
import bab.mvc.data.services.util.Files;


@Controller
@RequestMapping("/admin")
public class AdministrationController {
	
	
	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactUs(HttpSession session, Model model, HttpServletRequest request) {

		System.out.println("invoking admin contactus :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		try {
			File f = new File(Execute.imageRepository + "contactus.html");
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			System.out.println("text is:"+b.length+"\n"+b.toString());
			model.addAttribute("text", new String(b,StandardCharsets.UTF_8));
			return "editcontactus";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("text", "");
		return "editcontactus";
	}
	
	@RequestMapping(value="/editcontactus",method=RequestMethod.POST)
	public String editContactUs(HttpSession session,Model model, @RequestParam String text) {
		
		System.out.println("invoking admin editcontactus :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		try {
			File f = new File(Execute.imageRepository + "contactus.html");
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(text.getBytes(StandardCharsets.UTF_8));
			fos.close();
			//model.addAttribute("text",// b.toString());
			model.addAttribute("success", "Contact US text has ben saved successfylly.");
			return "redirect:/admin/contactus";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("error", "Contact US text could not be saved!.");
		return "redirect:/admin/contactus";
	}
	@RequestMapping(value = "/edithelp", method = RequestMethod.GET)
	public String editHelp(HttpSession session, Model model, HttpServletRequest request) {

		System.out.println("invoking admin edithelp :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		return "edithelp";
	}
	
	@RequestMapping(value = "/doedithelp", method = RequestMethod.POST)
	public String doEditHelp(HttpSession session, Model model, HttpServletRequest request) {

		System.out.println("invoking admin doedithelp :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "tariffnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		try {
			new Files().saveFile(Execute.imageRepository+"public/Help_register.pdf", "register", request);
			new Files().saveFile(Execute.imageRepository+"public/Help_users.pdf", "user", request);
			new Files().saveFile(Execute.imageRepository+"public/Help_admin.pdf", "admin", request);
			model.addAttribute("success","Help Files have been uploaded successfully" );
			return "edithelp";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("error","cant upload files" );
		return "edithelp";
	}
	
}
