package bab.mvc.controllers;


import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bab.mvc.Execute;
import bab.mvc.StaticData;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.GroupPermissions;
import bab.mvc.data.entities.pure.GroupUsers;
import bab.mvc.data.entities.pure.Groups;
import bab.mvc.data.entities.pure.News;
import bab.mvc.data.entities.pure.Permissions;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.FeesGroupUsersService;
import bab.mvc.data.services.GroupPermissionsService;
import bab.mvc.data.services.GroupUsersService;
import bab.mvc.data.services.GroupsService;
import bab.mvc.data.services.HPCTariffService;
import bab.mvc.data.services.JobsService;
import bab.mvc.data.services.NewsService;
import bab.mvc.data.services.PermissionsService;
import bab.mvc.data.services.UserService;
import bab.mvc.data.services.util.Files;
import bab.mvc.data.ssh.UserSSH;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	public UserService userCRUD;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model,HttpSession session) {
		
		System.out.println("invoking login user :get ");
		if(session.getAttribute("currentuser")!=null) {
			return "redirect:/user/hpchome";
		}
		return "login";
	}
	@RequestMapping(value="/showregister",method=RequestMethod.GET)
	public String showRegister(Model model,HttpSession session) {
		
		System.out.println("invoking login user :get ");
		if(session.getAttribute("currentuser")!=null) {
			return "redirect:/user/hpchome";
		}
		return "register";
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("currentuser");
		session.removeAttribute("permisions");
		session.removeAttribute("rootdir");
		System.out.println("invoking logout user :get ");
		
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String athentication(Model model, HttpSession session, @RequestParam String button, @RequestParam String username, @RequestParam String pass) {
		System.out.println("invoking athentication user :post");
		if(button.equals("login")) {
			User u= userCRUD.auth(username, pass);
			if(u != null && u.getIsactive() !=2) {
				
				//getting permissions
				List<GroupUsers> gus=new GroupUsersService().ReadByUid(u.getUid());
				List<GroupPermissions> gp=new ArrayList<GroupPermissions>();
				List<String> permisions= new ArrayList<String>();
				
				//List<Groups> g=new GroupsService().ReadByGid(""+gu.get(0).getGid());
				for (GroupUsers gu : gus) {
					
					gp = new GroupPermissionsService().ReadByGid(gu.getGid());

					for (GroupPermissions p : gp) {
						List<Permissions> pp = new PermissionsService().ReadByPid( p.getPid());
						permisions.add(pp.get(0).getPname());
					}
				}
				//-------
				
				System.out.println(u);
				session.setAttribute("currentuser", u);
				session.setAttribute("permisions", permisions);
				
				return "redirect:/user/hpchome";
			}else {
				if(u ==null) {
					model.addAttribute("error", "Username Password pair is not exist!");
					return "login";
				}else if(u.getIsactive() ==2){
					model.addAttribute("warning", "An email has been sent to "+u.getEmail()+"! open your inbo");
					return "login";
				}
			}
		}else if(button.equals("register")) {
			return "register";
		}
		model.addAttribute("error", "Sorry, there is a problem! Please try again.");
		return "login";
	}
	
	@RequestMapping(value="/forgotpass",method=RequestMethod.GET)
	public String forgotpass(HttpSession session) {
		System.out.println("invoking forgotpass user :get ");
		
		return "forgotpass";
	}
	@RequestMapping(value="/showdoneforgotpass",method=RequestMethod.GET)
	public String showdoneforgotpass(HttpSession session) {
		System.out.println("invoking forgotpass user :get ");
		
		return "doneforgotpass";
	}
	
	@RequestMapping(value="/forgotpass",method=RequestMethod.POST)
	public String forgotpass(Model model,HttpSession session, @RequestParam String username,@RequestParam String email) {
		System.out.println("invoking forgotpass user :post ");
		User u = new UserService().getUserByEmail(username, email);
		if (u != null) {
			
			String key;
			try {
				key = Encoder(u.getCreatetime().toString()+u.getUserName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("error", "A problem has been occured!");
				return "forgotpass";
			}
			if (new Execute().sendMail(email, "registration",
					"welcome to the HPC Service. click on this link to change password with \nuser: "+username+"\nkey: "+key
							+ "\n"+Execute.portalHost+"user/showdoneforgotpass")) {
				
				model.addAttribute("done",true);
				return "redirect:/user/showdoneforgotpass";
			} else {
				model.addAttribute("error", "We couldn't send Email!");
				return "forgotpass";
			}
		} else {
			model.addAttribute("error", "The username '" + username + "' and email '"+email+"' pair does not exist!");
			return "forgotpass";
		}
	}
	@RequestMapping(value="/doneforgotpass",method=RequestMethod.POST)
	public String doneforgotpass(Model model,HttpSession session, @RequestParam String username,
																  @RequestParam String key,
																  @RequestParam String pass,
																  @RequestParam String cpass) {
		System.out.println("invoking forgotpass user :post ");
		if(!pass.equals(cpass)) {
			model.addAttribute("message", "confirme password and password are not the same!");
			return "errorpage";
		}
		
		User u = new UserService().getUser(username);
		if (u != null) {
			String key2;
			try {
				key2 = Encoder(u.getCreatetime().toString()+u.getUserName());
				if(key2.equals(key)) {
					if(new UserSSH().userExist(username)) {
						if(new UserSSH().changePassword(username, cpass)) {
							u.setPass(cpass);
							userCRUD.update(u);
							model.addAttribute("success", "All Done! your password has been changed successfully");  
							return "login";
						}
					}else {
						u.setPass(cpass);
						userCRUD.update(u);
						model.addAttribute("success", "All Done! your password has been changed successfully");  
						return "login";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("error", "aproblem has been occured!");
		return "doneforgotpass";
	}
	@RequestMapping(value="/hpchome",method=RequestMethod.GET)
	public String hpchome(HttpSession session,Model model) {
		System.out.println("invoking hpchome user :get ");
		User u=(User) session.getAttribute("currentuser");
		if(u!=null) {
				
			
			List<News> news =new NewsService().readAvailable(new Date());
			model.addAttribute("news", news);
			if(PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobreport3")) {
				model.addAttribute("clusterreport",new JobsService().clusterSummary(30));
				model.addAttribute("tariffs",new HPCTariffService().read());
			}else {
				String[] str=new UserSSH().userGroups(u.getUserName()).split(",");
				List<String> groups = new ArrayList<String>();
				String strr="";
				for (String string : str) {
					groups.add(string);
					strr+=",'"+string.replace("\n", "")+"'";
				}
				model.addAttribute("tariffs", new HPCTariffService().getUserTarrifList(strr.substring(1)));
			}
			return "hpchome";
		}
		return "redirect:/user/logout";
	}
	
	@RequestMapping(value="/compregform",method=RequestMethod.GET)
	public String comReg(HttpSession session,Model model) {
		System.out.println("invoking comReg user :get ");

		return "comreg";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(Model model,
						   @RequestParam String fname,
						   @RequestParam String lname,
						   @RequestParam String username,
						   @RequestParam String pass,
						   @RequestParam String cpass,
						   @RequestParam String email) {
		
		System.out.println("invoking register user :post");
			User u= userCRUD.getUser(username);
			if(u != null || new UserSSH().userExist(username)) {
				model.addAttribute("error", "The username '"+username+"' is already taken!");
				return "register";
			}else {
				if(pass.equals(cpass)) {
					//if(new UserSSH().userAdd(username, pass)) {
						java.util.Date createtime=new Date();
						System.out.println("000-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
						String key;
						
						//----
						try {
							
							System.out.println("111-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
							String d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime);
							createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
							System.out.println("222-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
							key = Encoder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
							System.out.println("333-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							model.addAttribute("error", "A problem has been occured! Pleas try again");
							return "register";
						}
						System.out.println("-errr----------------------------->!!!!!");
                         System.out.println("444-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
						 try {
								if(!userCRUD.create(fname, lname, username, pass, email,createtime, 2)) {
									model.addAttribute("error", "the Username or Email is already exist! please choose another one!");
									return "register";
								};
								System.out.println("-------------------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createtime));
							}catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								model.addAttribute("error", "The Username or Email is already exist! please choose another one!");
								return "register";
							} 
						if(new Execute().sendMail(email, "registration", "welcome to the HPC Service. click on this link to complete registration: "
								+ Execute.portalHost+"user/compregform \n then enter requirements \n username="+username+" \nkey="+key+"\nGood Luck \n from: "+Execute.portalHost )) {

							System.out.println("--------------------------------------------->username: "+username);
						
							System.out.println("the username '"+username+"' has been created successfuly!");
							System.out.println("the username '"+username+"' has createtime:"+createtime.toString());
							
							model.addAttribute("success", "All Done! An email has been sent for you. Please folow your email and complete registration.");
							return "login";
						}else {
							model.addAttribute("error", "We couldn't send Email!");
							return "register";
						}
						
						
					//}else {
					//	return "register";
					//}
				}
				model.addAttribute("error", "Password and confirm are not the same!");
				return "register";
			}
	}
	@RequestMapping(value="/completeregistration",method=RequestMethod.POST)
	public String completeRegistration(Model model,@RequestParam String username, @RequestParam String key) {

		System.out.println("invoking completeregistration user :get ");
		
		User u=new UserService().getEarlyRegisterdUser(username);
		if(u==null) {
			model.addAttribute("error", "A problem has been occured! there is no username: "+username);
			return "comreg";
		}
		String key2;
		try {
			System.out.println("------------@@@@@@@@@@@-------------->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(u.getCreatetime()));
			key2 = Encoder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(u.getCreatetime()));
			System.out.println("invoking completeregistration user :get createTime:"+u.getCreatetime().toString());
			System.out.println("invoking completeregistration user :get key2:"+key2);
			System.out.println("invoking completeregistration user :get key1:"+key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", "A problem has been occured!");
			return "comreg";
		}
		if(key2.equals(key)) {
			u.setIsactive(0);
			new UserService().update(u);
			model.addAttribute("success", "All Done! Please Login and complete your profile.");
			new Execute().sendMail(u.getEmail(), "HPC registration report", "Hi dear "+u.getFname()+" "+u.getLname()+"\n"+
			                                     "your registeration has been done\n"+
					                             "username: "+u.getUserName()+"\nemail: "+u.getEmail());
			return "login";
		}
		
		model.addAttribute("error", "you ar not registered or your registration is expired after 24 hours!");
		return "comreg";
	}
	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public String profile(Model model, HttpSession session) {

		System.out.println("invoking profile user :get ");
		
		//if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "usermyprofile")) {
			//session.removeAttribute("currentuser");
			//session.removeAttribute("permisions");
			//return "login";
		//}
		
		model.addAttribute("grade", StaticData.getGrade());
		model.addAttribute("univesity", StaticData.getUniversity());
		model.addAttribute("faculty", StaticData.getFaculty());
		
		User u=(User) session.getAttribute("currentuser");
		
		
		
		
		return "userprofile";
	}
	
	@RequestMapping(value="/deletehome",method=RequestMethod.POST)
	public String deleteHome(Model model, HttpSession session, @RequestParam int uid) {

		System.out.println("invoking deletehome user :POST ");
		
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userlist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		User u=new UserService().getUserByUid(uid);
		new UserSSH().userDelHome(u.getUserName(), u.getPass());
		model.addAttribute("users", new UserService().readUsersComplex());
		return "userlist";
	}
	
	@RequestMapping(value="/profile",method=RequestMethod.POST)
	public String profile(HttpSession session, HttpServletRequest request,Model model,
			   @RequestParam String nationalcode,
			   @RequestParam int grade,
			   @RequestParam int university,
			   @RequestParam int faculty,
			   @RequestParam String field,
			   @RequestParam int state,
			   @RequestParam String sn_pn,
			   @RequestParam String phone,
			   @RequestParam String address,
			   @RequestParam String fname,
			   @RequestParam String lname
			   //@RequestParam String st_pr_card,
			   //@RequestParam String racommandation
			   ) {
		
		//if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "usermyupdate")) {
			//session.removeAttribute("currentuser");
			//session.removeAttribute("permisions");
			//return "login";
		//}
		
		
		System.out.println("invoking profile user :post");
		User u=(User) session.getAttribute("currentuser");
		
		u.setNationalCode(nationalcode);
		System.out.println("------>g:"+grade+", u:"+university+", f:"+faculty);
		u.setGradeid(grade);
		u.setUnid(university);
		u.setFacultyid(faculty);
		u.setField(field);
		u.setState(state);
		u.setSN_PN(sn_pn);
		u.setPhone(phone);
		u.setAddress(address);
		u.setFname(fname);
		u.setLname(lname);
		try {
			
			new Files().saveFile(Execute.imageRepository+u.getUid()+"_myicon.jpg", "pimage", request);
			new Files().saveFile(Execute.imageRepository+u.getUid()+"_myncard.jpg", "nationalcard", request);
			new Files().saveFile(Execute.imageRepository+u.getUid()+"_myst_pr_card.jpg", "st_pr_card", request);
			new Files().saveFile(Execute.imageRepository+u.getUid()+"_myracommandation.jpg", "racommandation", request);
			
		} catch (ServletException e) {
			System.out.println("------> ServletException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("------> IOException");
			e.printStackTrace();
		}
		try {
			new UserService().update(u);
		}catch (Exception e) {
			model.addAttribute("error", "Some parameters such as National code, Phone numbler, Email,... are unique! please check is this user the only one that use them?");
		}
		
		return "redirect:/user/hpchome";
	}
	
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userList(Model model, HttpSession session) {
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userlist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		
		System.out.println("invoking userList user :get ");
		
		model.addAttribute("users", new UserService().readUsersComplex());

		
		return "userlist";
	}
	
	@RequestMapping(value="/userlistsearch",method=RequestMethod.POST)
	public String userListSearch(Model model, HttpSession session,@RequestParam String fname,
											 @RequestParam String lname,
											 @RequestParam String username) {
		
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userlist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		System.out.println("invoking userList user :get ");
		
		model.addAttribute("users", new UserService().readUsersComplex(fname, lname, username));
		
		
		return "userlist";
	}
	
	@RequestMapping(value="/editselected",method=RequestMethod.POST)
	public String editselected(HttpSession session,Model model,
			   @RequestParam String username) {
		System.out.println("invoking edit user :get ");
		
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userprofile")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		
		model.addAttribute("grade", StaticData.getGrade());
		model.addAttribute("univesity", StaticData.getUniversity());
		model.addAttribute("faculty", StaticData.getFaculty());
		
		User selected=new UserService().getUser(username);
		model.addAttribute("selecteduser",selected);
		
		
		List<String> groups=new ArrayList<String>();
		List<GroupUsers> gu=new GroupUsersService().ReadByUid(selected.getUid());
		for (GroupUsers groupUsers : gu) {
			groups.add((new GroupsService().ReadByGid(groupUsers.getGid())).get(0).getGname());
		}

		model.addAttribute("selectedusergroups",groups);
		
		return "useredit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpSession session,HttpServletRequest request, Model model,
			   @RequestParam String username,
			   @RequestParam int active,
			   @RequestParam String expire,
			   
			   @RequestParam String nationalcode,
			   @RequestParam int grade,
			   @RequestParam int university,
			   @RequestParam int faculty,
			   @RequestParam String field,
			   @RequestParam int state,
			   @RequestParam String sn_pn,
			   @RequestParam String phone,
			   @RequestParam String address
			   ) throws Exception {
		
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userupdate")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		
		System.out.println("invoking edit user :post");
		System.out.println("------------>active:"+active+", expire:"+expire+", username:"+username);
		
		
		User su=new UserService().getUser(username);
		su.setIsactive(active);
		su.setNationalCode(nationalcode);
		su.setGradeid(grade);
		su.setUnid(university);
		su.setFacultyid(faculty);
		su.setField(field);
		su.setState(state);
		su.setSN_PN(sn_pn);
		su.setPhone(phone);
		su.setAddress(address);
		
		su.setExptime(new SimpleDateFormat("yyyy-MM-dd").parse(expire));
		
		new UserService().update(su);
		
		if(request.getParameter("isadmin")!=null){
			new GroupUsersService().create(new GroupUsers(1, su.getUid()));
		}
		
		if(request.getParameter("isuser")!=null) {
			List<GroupUsers> gus = new GroupUsersService().ReadByUid(su.getUid());
			boolean isHPCUser=false;
			for (GroupUsers groupUsers : gus) {
				if(groupUsers.getGid()==2) {
					if(new UserSSH().userExist(su.getUserName())){
						return "redirect:/user/userlist";
					}else {
						if(new UserSSH().userAdd(su.getUserName(), su.getPass(),Execute.storageSize)) {
							return "redirect:/user/userlist";
						}else {
							model.addAttribute("error", "A problem has been ocurred! Please check is this user already in cluster or not!");
							return userList(model, session);
						}
					}
				}
			}
			if(new UserSSH().userAdd(su.getUserName(), su.getPass(),Execute.storageSize)) {
				new GroupUsersService().create(new GroupUsers(2, su.getUid()));
			}else {
				model.addAttribute("error","A problem has been ocurred! Please check is this user already in cluster or not!");
				return userList(model, session);
			}
		}
		
		return "redirect:/user/userlist";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String contact(Model model, HttpSession session) {
		System.out.println("invoking contact user :get ");
		try {
			File f = new File(Execute.imageRepository + "contactus.html");
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			model.addAttribute("text", new String(b,StandardCharsets.UTF_8));
			return "contact";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("text", "");
		return "contact";
	}
	
	@RequestMapping(value="/freecontact",method=RequestMethod.GET)
	public String freecontact(Model model, HttpSession session) {
		System.out.println("invoking freecontact user :get ");
		try {
			File f = new File(Execute.imageRepository + "contactus.html");
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			model.addAttribute("text", new String(b,StandardCharsets.UTF_8));
			return "freecontact";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("text", "");
		return "freecontact";
	}
	
	@RequestMapping(value="/ganglia",method=RequestMethod.GET)
	public String ganglia(Model model, HttpSession session) {
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "userlist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "login";
		}
		
		System.out.println("invoking ganglia user :get ");
		return "clusterReport";
	}
	
	@RequestMapping(value = "/documentation/{label}")
	public @ResponseBody byte[] getDocument(HttpServletResponse response,@PathVariable(value = "label") String label, HttpSession session) {
		System.out.println("-----------------------------------------------------/user/documentation/" + label
				+ "----------------------------------->");
			//int uid=((User) session.getAttribute("currentuser")).getUid();
			try {
					File f = new File(Execute.imageRepository+"public/"+label+".pdf");
					FileInputStream fi = new FileInputStream(f);
					byte[] pdf=IOUtils.toByteArray(fi);
					fi.close();
					
					response.setContentType("application/pdf");
					response.addHeader("Content-Disposition", "inline;filename=" + label+".pdf");
					
					return pdf;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		return null;
	}
	@RequestMapping(value = "/imageController/{label}")
	public @ResponseBody byte[] getLocalImage(@PathVariable(value = "label") String label, HttpSession session) {
		System.out.println("-----------------------------------------------------/user/imageController/" + label
				+ "----------------------------------->");
			int uid=((User) session.getAttribute("currentuser")).getUid();
			try {
				if(label.equals("myicon")) {
					File f = new File(Execute.imageRepository+uid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myncard")) {
					File f = new File(Execute.imageRepository+uid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myracommandation")) {
					File f = new File(Execute.imageRepository+uid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myst_pr_card")) {
					File f = new File(Execute.imageRepository+uid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else {
					File f = new File(Execute.imageRepository+"none.jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}
			} catch (FileNotFoundException e) {
				File f = new File(Execute.imageRepository+"none.jpg");
				byte[] im;
				try {
					FileInputStream fi = new FileInputStream(f);
					im = IOUtils.toByteArray(new FileInputStream(f));
					fi.close();
					return im;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		return null;
	}
	
	@RequestMapping(value = "/imageController/{selcteduid}/{label}")
	public @ResponseBody byte[] getLocalImage(@PathVariable(value = "label") String label, @PathVariable(value = "selcteduid") int selcteduid, HttpSession session) {
		System.out.println("-----------------------------------------------------/user/imageController/"+selcteduid+"/"+ label
				+ "----------------------------------->");
			int uid=((User) session.getAttribute("currentuser")).getUid();
			
			List<String> permissionList=(List<String>) session.getAttribute("permisions"); 
			
			if(!PermissionsService.exist(permissionList, "userupdate")) {
				return null;
			}
			
			
			try {
				if(label.equals("myicon")) {
					File f = new File(Execute.imageRepository+selcteduid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myncard")) {
					File f = new File(Execute.imageRepository+selcteduid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myracommandation")) {
					File f = new File(Execute.imageRepository+selcteduid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else if(label.equals("myst_pr_card")) {
					File f = new File(Execute.imageRepository+selcteduid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else {
					File f = new File(Execute.imageRepository+"none.jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}
				
			} catch (FileNotFoundException e) {
				File f = new File(Execute.imageRepository+"none.jpg");
				byte[] im;
				try {
					FileInputStream fi = new FileInputStream(f);
					im = IOUtils.toByteArray(new FileInputStream(f));
					fi.close();
					return im;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		return null;
	}
	
	@RequestMapping(value = "/imageController/icon/{selcteduid}/{label}")
	public @ResponseBody byte[] getLocalImageIcon(@PathVariable(value = "label") String label, @PathVariable(value = "selcteduid") int selcteduid, HttpSession session) {
		System.out.println("-----------------------------------------------------/user/imageController/"+selcteduid+"/"+ label
				+ "----------------------------------->");
			int uid=((User) session.getAttribute("currentuser")).getUid();
			
			List<String> permissionList=(List<String>) session.getAttribute("permisions"); 
			
			if(!(PermissionsService.exist(permissionList, "userupdate") || PermissionsService.exist(permissionList, "usermemberslist"))) {
				return null;
			}
			
			
			try {
				if(label.equals("myicon")) {
					File f = new File(Execute.imageRepository+selcteduid+"_"+label+".jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}else {
					File f = new File(Execute.imageRepository+"none.jpg");
					FileInputStream fi = new FileInputStream(f);
					byte[] im=IOUtils.toByteArray(fi);
					fi.close();
					return im;
				}
				
			} catch (FileNotFoundException e) {
				File f = new File(Execute.imageRepository+"none.jpg");
				byte[] im;
				try {
					FileInputStream fi = new FileInputStream(f);
					im = IOUtils.toByteArray(new FileInputStream(f));
					fi.close();
					return im;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		return null;
	}
	public String Encoder(String input) {
		
		System.out.println("-----------------Encoder--------->>>>");
		System.out.println("#### length:"+input.length());
		System.out.println("#### input:"+input+"|");
		System.out.println("--------------------------------->>>>");
		try{
			return Execute.getMD5Hex(input);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		//return  Base64.getEncoder().encodeToString(input.getBytes());
	}

	
}
