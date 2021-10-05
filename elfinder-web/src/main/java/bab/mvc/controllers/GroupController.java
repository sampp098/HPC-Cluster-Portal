package bab.mvc.controllers;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bab.mvc.Execute;
import bab.mvc.data.entities.complex.FeesGroupComplex;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.GroupUsers;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.FeesGroupComplexService;
import bab.mvc.data.services.FeesGroupService;
import bab.mvc.data.services.FeesGroupUsersService;
import bab.mvc.data.services.GroupUsersService;
import bab.mvc.data.services.PermissionsService;
import bab.mvc.data.services.UserService;
import bab.mvc.data.ssh.DiskSSH;
import bab.mvc.data.ssh.UserSSH;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	
	@RequestMapping(value="/hpcgrouplist",method=RequestMethod.GET)
	public String hpcgrouplist(HttpSession session, HttpServletRequest request) {
		
		System.out.println("invoking hpcgroups group :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgrouplist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		
		//int uid=((User) session.getAttribute("currentuser")).getUid();
		List<FeesGroupComplex> groupComplexs= new FeesGroupComplexService().Read();
		request.setAttribute("groupcomplex", groupComplexs);
		
		return "hpcgrouplist";
	}
	
	@RequestMapping(value="/hpcgrouplistsearch", method=RequestMethod.POST)
	public String hpcgrouplistsearch(HttpSession session,Model model, @RequestParam String fgname) {
		System.out.println("invoking hpcgrouplistsearch group :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgrouplist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		List<FeesGroupComplex> groupComplexs= new FeesGroupComplexService().Read(fgname);
		model.addAttribute("groupcomplex", groupComplexs);
		
		return "hpcgrouplist";
	}
	
	@RequestMapping(value="/edithpcgroup",method=RequestMethod.POST)
	public String edithpcgroup(HttpSession session,Model model, @RequestParam int fgid, @RequestParam String button ) {
		
		System.out.println("invoking edithpcgroup group :POST ");

		FeesGroup fg= new FeesGroupService().ReadByfgid(fgid).get(0);
        
        User owner=new UserService().getUserByUid(fg.getUid());
        
		String username =owner.getUserName();
		String name =owner.getFname()+" "+owner.getLname();
		  
        String fgname=fg.getFgname();
        double charge=fg.getCharge();


        model.addAttribute("username",username);
        model.addAttribute("name",name);
        model.addAttribute("fgname",fgname);
        model.addAttribute("charge",charge);
        model.addAttribute("fgid",fgid);
        
        if(button.equals("edit")) {
        	if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    		}
        	return "edithpcgroup";
        }else if(button.equals("members")) {
        	if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    		}
        	
        	List<Object[]> members= new UserService().readHPCGroupMembers(fgid);
        	model.addAttribute("members", members);
        	return "hpcgroupmemberslist";
        }else {
        	if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    		}
        	
        	List<User> users= new UserService().readWithoutHPCGroups();
    		model.addAttribute("users", users);
        	return "hpcgroupaddmember";
        	//int ownerid=Integer.parseInt(request.getParameter("ownerid"));
        }
	}
	
	@RequestMapping(value="/hpcgroupmembers",method=RequestMethod.POST)
	public String members(HttpSession session,HttpServletRequest request,Model model, @RequestParam int fgid, @RequestParam String button) {
		
		System.out.println("invoking hpcgroupmembers group :POST ");
		
		
		FeesGroup fg= new FeesGroupService().ReadByfgid(fgid).get(0);

        
        User owner=new UserService().getUserByUid(fg.getUid());
        
		String username =owner.getUserName();
		String name =owner.getFname()+" "+owner.getLname();
		  
        String fgname=fg.getFgname();
        double charge=fg.getCharge();

        model.addAttribute("username",username);
        model.addAttribute("name",name);
        model.addAttribute("fgname",fgname);
        model.addAttribute("charge",charge);
        
        model.addAttribute("fgid",fgid);

        
    	
    	
    	if(button.equals("add member")) {
    		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    		}
    		int memberid=Integer.parseInt((String)request.getParameter("memberid"));
    		System.out.println("invoking hpcgroupmembers group adding member:"+memberid+" fgid:"+fgid+":POST ");
    		Date now=new Date();
    		new FeesGroupUsersService().create(new FeesGroupUsers(fgid, memberid,0,Execute.storageSize,now,now));
    	}
    	
    	List<Object[]> members= new UserService().readHPCGroupMembers(fgid);
    	model.addAttribute("members", members);
    	return "redirect:/group/hpcgrouplist";

	}
	@RequestMapping(value="/deletemember",method=RequestMethod.POST)
	public String deletemember(HttpSession session,HttpServletRequest request,Model model, @RequestParam int fgid) {
		
		System.out.println("invoking hpcgroupmembers group :POST ");
		
		
		FeesGroup fg= new FeesGroupService().ReadByfgid(fgid).get(0);

        
        User owner=new UserService().getUserByUid(fg.getUid());
        
		String username =owner.getUserName();
		String name =owner.getFname()+" "+owner.getLname();
		  
        String fgname=fg.getFgname();
        double charge=fg.getCharge();

        model.addAttribute("username",username);
        model.addAttribute("name",name);
        model.addAttribute("fgname",fgname);
        model.addAttribute("charge",charge);
        
        model.addAttribute("fgid",fgid);


    		if(!(PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")
    			|| PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobmemberdelete"))) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    		}
    		int memberid=Integer.parseInt((String)request.getParameter("memberid"));
    		new FeesGroupUsersService().delete(new FeesGroupUsers(fgid, memberid,0,Execute.storageSize,null,null));

    	List<Object[]> members= new UserService().readHPCGroupMembers(fgid);
    	model.addAttribute("members", members);
    	return "hpcgroupmemberslist";

	}
	@RequestMapping(value="/myhpcgroupmembers",method=RequestMethod.GET)
	public String mymembers(HttpSession session,HttpServletRequest request,Model model) {
		
		System.out.println("invoking hpcgroupmembers group :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "usermemberslist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		
		FeesGroup fg= new FeesGroupService().ReadByOwnerUid(uid).get(0);

        
        User owner=new UserService().getUserByUid(fg.getUid());
        
		String username =owner.getUserName();
		String name =owner.getFname()+" "+owner.getLname();
		  
        String fgname=fg.getFgname();
        double charge=fg.getCharge();

        model.addAttribute("username",username);
        model.addAttribute("name",name);
        model.addAttribute("fgname",fgname);
        model.addAttribute("charge",charge);
        
        model.addAttribute("fgid",fg.getFgid());

    	List<Object[]> members= new UserService().readHPCGroupMembers(fg.getFgid());
    	model.addAttribute("members", members);
    	
    	return "myhpcgroupmemberslist";

	}
	
	@RequestMapping(value="/newhpcgroup",method=RequestMethod.POST)
	public String newhpcgroup(HttpSession session,HttpServletRequest request,Model model,
												               @RequestParam String button,
															   //@RequestParam String sfname,
			 												   //@RequestParam String slname,
			 												   //@RequestParam String susername,
			 												   @RequestParam String fgname,
			 												   @RequestParam double charge) {
		
		System.out.println("invoking newhpcgroup Group :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		if(button.equals("search")) {

			model.addAttribute("users", new UserService().getOwnerableUsers("", "", ""));
		
			model.addAttribute("fgname",fgname);
			model.addAttribute("charge",charge);

			
			model.addAttribute("type",1);
			System.out.println("invoking newhpcgroup Group :post type:1 ");

			return "newhpcgroup";
			
		}else if(button.equals("save")) {
			int ownerid=Integer.parseInt(request.getParameter("ownerid"));
			User owner= new UserService().getUserByUid(ownerid);
			
			new UserSSH().userAdd(owner.getUserName(), owner.getPass(),Execute.storageSize);
			
			FeesGroup fg=new FeesGroup(fgname,ownerid,charge,"description",1);
			int fgid=new FeesGroupService().create(fg);
			if(fgid==-1) {
				model.addAttribute("message", "Please choose a unique name for group name and try again!");
				return "errorpage";
			}
			
			new GroupUsersService().create(new GroupUsers(3, ownerid));
			Date now=new Date();
			new FeesGroupUsersService().create(new FeesGroupUsers(fgid, ownerid,1,Execute.storageSize,now,now));
			
			return "redirect:/group/hpcgrouplist";
		}
		return "redirect:/group/hpcgrouplist";
	}
	
	@RequestMapping(value="/newhpcgroup",method=RequestMethod.GET)
	public String newhpcgroup(HttpSession session, Model model) {
		
		System.out.println("invoking newhpcgroup Group :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		List<User> users= new UserService().getOwnerableUsers("", "", "");
		model.addAttribute("users", users);

		model.addAttribute("type",0);
		
		return "newhpcgroup";
	}
	
	
	@RequestMapping(value="/activegroup",method=RequestMethod.POST)
	public String activeGroup(HttpSession session,HttpServletRequest request,Model model,
			 												   @RequestParam int fgid,
			 												   @RequestParam String button) {
		
		System.out.println("invoking Active Group :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		FeesGroup fg=new FeesGroupService().ReadByfgid(fgid).get(0);
		if(button.equals("deactivate")) {
			fg.setActive(0);
		}else {
			fg.setActive(1);
		}
		new FeesGroupService().update(fg);
			
		return "redirect:/group/hpcgrouplist";
	}
	
	@RequestMapping(value="/saveedithpcgroup",method=RequestMethod.POST)
	public String updatehpcgroup(HttpSession session,HttpServletRequest request,Model model,
			 												   @RequestParam int fgid,
			 												   @RequestParam double charge) {
		
		System.out.println("invoking newhpcgroup Group :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "hpcgroupupdate")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
			FeesGroup fg=new FeesGroupService().ReadByfgid(fgid).get(0);

			fg.setCharge(charge);
			new FeesGroupService().update(fg);
			
			return "redirect:/group/hpcgrouplist";
	}
	
	@RequestMapping(value="/changestorage",method=RequestMethod.POST)
	public String changeStorage(HttpSession session,HttpServletRequest request,Model model,
			 												   @RequestParam int fguid,
			 												   @RequestParam int storage) {
		
		System.out.println("invoking changestorage Group :post");
		if(!(PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobmemberdelete"))) {
    			session.removeAttribute("currentuser");
    			session.removeAttribute("permisions");
    			return "redirect:/user/login";
    	}
		
		FeesGroupUsers fgu=new FeesGroupUsersService().ReadByFguid(fguid).get(0);
		FeesGroup fg=new FeesGroupService().ReadByfgid(fgu.getFgid()).get(0);
		
		User member= new UserService().getUserByUid(fgu.getUid());
		if(new DiskSSH().editSpace(member.getUserName(), storage)) {
			Date now = new Date();
			int prevExtra=fgu.getStorage() - Execute.storageSize;
			int nowExtra=storage - Execute.storageSize;
			int toGiveBackCost=0;
			long start=now.getTime();
			long end=fgu.getEnd().getTime();
			if(end > start) {
				int diffDays = (int)((end-start) / (1000 * 60 * 60 * 24))+1;
				toGiveBackCost=Execute.storagecost * diffDays * prevExtra;
			}
			int days=30;
			fgu.setStorage(storage);
			fgu.setStart(now);
			fgu.setEnd(addDay(now, days));
			int cost=toGiveBackCost - (Execute.storagecost * days * nowExtra);
			
			
			new FeesGroupUsersService().update(fgu);
			fg.setCharge(fg.getCharge()+cost);
			new FeesGroupService().update(fg);
		}
		
		return "redirect:/group/myhpcgroupmembers";
	}
	
	@RequestMapping(value="/activemember",method=RequestMethod.POST)
	public String activeMember(HttpSession session,HttpServletRequest request,Model model,
			 												   @RequestParam int fguid) {
		
		System.out.println("invoking activemember Group :post");
		if(!(PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobmemberdelete"))) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
			FeesGroupUsers fgu=new FeesGroupUsersService().ReadByFguid(fguid).get(0);

			fgu.setState(fgu.getState()==1?0:1);
			new FeesGroupUsersService().update(fgu);
			
			return "redirect:/group/myhpcgroupmembers";
	}
	
	public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
	
}
