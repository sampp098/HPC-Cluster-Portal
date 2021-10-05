package bab.mvc.data.ssh;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.FeesGroupService;
import bab.mvc.data.services.FeesGroupUsersService;
import bab.mvc.data.services.UserService;

public class DiskSSH {
	
	public void StorageEndTimeJob() {
		
		List<FeesGroupUsers> feesGroupUsers=new FeesGroupUsersService().getStorageEndTime();
		for (FeesGroupUsers fgu : feesGroupUsers) {
			fgu.setStorage(Execute.storageSize);
			new FeesGroupUsersService().update(fgu);
			String username = new UserService().getUserByUid(fgu.getUid()).getUserName();
			editSpace(username, Execute.storageSize);
		}
		
	}
	
	public boolean editSpace(String username, int storage) {
		//String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt; cecho -e "\n\n" | /root/userman/create_user.sh milad;
		String cmd1="ssh root@"+Execute.host+" /root/portal/userman/change_qouta.sh  :username :storage";
		cmd1=cmd1.replace(":username", username).replace(":storage", ""+storage);
		//cmd1=cmd1
		//change_qouta.sh
		try {
			String out=new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			if(out.contains("Faild")) {
				
			}else if(out.contains("done")){
				return true;
			}
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*public String usedSpace(String username) {
		//String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt; cecho -e "\n\n" | /root/userman/create_user.sh milad;
		String cmd1="ssh root@"+Execute.host+" /root/portal/userman/report_qouta.sh  :username";
		cmd1=cmd1.replace(":username", username);
		//cmd1=cmd1
		//change_qouta.sh
		try {
			String out=new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			String[] str=out.split("[\n]");
			if(str.length>=1) {
				String s=str[0].replaceAll("^\\s+","");
				
			}
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}*/
	
	public boolean expired() {
		//String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt; cecho -e "\n\n" | /root/userman/create_user.sh milad;
		Date now=new Date(); 
		if(now.getTime() > Execute.end) {
		String cmd1="ssh root@"+Execute.host+" init 0";
		//cmd1=cmd1
		//change_qouta.sh
		try {
			String out=new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			
			return true;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return false;
	}
	
}
