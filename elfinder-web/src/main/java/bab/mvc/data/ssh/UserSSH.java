package bab.mvc.data.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;

public class UserSSH {
	public boolean userAdd(String username, String password, int storage) {

		if(userExist(username)) {
			System.out.println("cant create user: user " + username.toLowerCase() + " is already exists!");
			return false;
		}
		String pass=password;

		String cmd1="ssh root@"+Execute.host+" /root/portal/userman/new_user.sh \""+username+"\" \""+storage+"\"  '"+pass+"' > create.log 2>&1 ; cat create.log | grep done ";
		//String cmd1="ssh root@10.14.20.10 echo \""+pass+"\" > /root/portal/userman/.pass";
		//String cmd2 = "ssh root@server echo -e \"" + password + "\n" + password + "\" | passwd " + username.toLowerCase();
		try {
			String out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			if (out.contains("done")) {
				//out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd2);
				return true;
			}
			System.out.println("cant create user: user " + username.toLowerCase() + "!");
			return false;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cant create user: an error has been occurred!");// occurred
		return false;
	}

	public boolean userExist(String username) {
		// String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt;
		// cecho -e "\n\n" | /root/userman/create_user.sh milad;
		char ctrlD = 4;
		String cmd1 = "cat /etc/passwd | grep \"" + username.toLowerCase() + ":\"";
		try {
			String out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			if (out.length() > 1 && out.charAt(0) != '#') {

				System.out.println("user " + username + " is already exists!");
				return true;
			}
			System.out.println("user " + username + " does not exist!");
			return false;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userssh userexist: An error has been occurred!");// occurred
		return false;
	}
	
	public boolean changePassword(String username,String pass) {
		// String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt;
		// cecho -e "\n\n" | /root/userman/create_user.sh milad;
		
		
		
		
		String changePassword="echo ':pass'>/root/portal/userman/.newpass; "
				+ "ssh root@"+Execute.host+" /root/portal/userman/changePass.sh :username;";
		
		
		//String changePassword="ssh root@hpc \"echo -e ':pass\n:pass' | passwd :username\"";
		//String changePassword="ssh root@hpc \"echo  \\'':pass'\\'\\\\n\\'':pass'\\' >/root/sam.pass\"";
		String cmd1 = changePassword.replace(":pass", pass).replace(":username", username);
		try {
			String out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			if (out.length() > 1 && out.contains("done")) {

				System.out.println("paswword has been changed for user " + username);
				return true;
			}
			System.out.println("cant change password for user " + username);
			return false;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userssh changePassword: An error has been occurred!");// occurred
		return false;
	}
	
	public boolean userDelHome(String username, String password) {

		String pass=password;

		String cmd1=" cd /storage/users/"+username+"; "+" rm -rf /storage/users/"+username+"/*";
		//String cmd1="ssh root@10.14.20.10 echo \""+pass+"\" > /root/portal/userman/.pass";
		//String cmd2 = "ssh root@server echo -e \"" + password + "\n" + password + "\" | passwd " + username.toLowerCase();
		try {
			String out = new Execute().sshExe(Execute.host, username, pass, cmd1);
			return true;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String userGroups(String username) {
		
		try {
			String report1=new Execute().sshExe(Execute.host, Execute.username, Execute.pass,
					"Queue_rep | grep \"All Users\"");
			report1=report1.replace(" = All Users","").replace("\n", ",");
			
			String report=new Execute().sshExe(Execute.host, Execute.username, Execute.pass,
					"groups "+username);
			String str;
			System.out.println("is userGroups ssh length:"+report.length());
			if(report.length()>0) {
				str=report.split(":")[1];
				return (report1+str.replace("  ", " ").replace(" ", ",")).replace(",,", ",");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
