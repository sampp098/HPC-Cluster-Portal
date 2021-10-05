package bab.mvc.data.ssh;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;
import bab.mvc.data.entities.pure.User;

public class JobSSH {
	public int JobAdd(String username,String password,String jobname,String appHome, String script) {
		//String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt; cecho -e "\n\n" | /root/userman/create_user.sh milad;
		String cmd1="mkdir -p "+appHome+";cd "+appHome+"; echo \""+script+"\"> "+appHome+jobname+".sh;"+"qsub "+appHome+jobname+".sh";
		try {
			String out=new Execute().sshExe(Execute.host, username, password, cmd1);
			System.out.println(out+": "+out.split("[.]")[0]+"  "+out.split("[.]")[1]);
			return Integer.parseInt(out.split("[.]")[0]);
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
		
		return -1;
	}
	
}
