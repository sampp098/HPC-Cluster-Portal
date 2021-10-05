package bab.mvc.data.ssh;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.RequestParam;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;
import bab.mvc.data.entities.pure.User;

public class TariffSSH {
	public boolean tariffAdd(String name, int maxnodes, int maxcores, int maxmemory, int queuesize, int maxuserjobs,
			int maxusercjobs, int maxcputime, int maxwalltime) {

		// Queue_Name Qeue_Size Max_user_Queue_Jobs Max_exec_Jobs_CPU_time
		// Max_mem_Size(GB) Max_Core_Size_Per_Node Max_nodes Max_Walltime
		// Concorent_Jobs_For_one_User
		// String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt;
		// cecho -e "\n\n" | /root/userman/create_user.sh milad;
		String cmd1 = "ssh root@" + Execute.host + " /root/portal/userman/create_tariff.sh " + name + " " + queuesize
				+ " " + maxuserjobs + " " + maxcputime + " " + maxmemory + " " + maxcores + " " + maxnodes + " "
				+ maxwalltime + " " + maxusercjobs + " ";
		try {
			String out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			// System.out.println(out+": "+out.split("[.]")[0]+" "+out.split("[.]")[1]);
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

		return false;
	}

	public boolean tariffUpdate(String name, int maxnodes, int maxcores, int maxmemory, int queuesize, int maxuserjobs,
			int maxusercjobs, int maxcputime, int maxwalltime) {

		// Queue_Name Qeue_Size Max_user_Queue_Jobs Max_exec_Jobs_CPU_time
		// Max_mem_Size(GB) Max_Core_Size_Per_Node Max_nodes Max_Walltime
		// Concorent_Jobs_For_one_User
		// String cmd1="useradd -d "+Execute.homeDir+username+" "+username +" > tmp.txt;
		// cecho -e "\n\n" | /root/userman/create_user.sh milad;
		String cmd1 = "ssh root@" + Execute.host + " /root/portal/userman/create_tariff.sh " + name + " " + queuesize
				+ " " + maxuserjobs + " " + maxcputime + " " + maxmemory + " " + maxcores + " " + maxnodes + " "
				+ maxwalltime + " " + maxusercjobs + " ";
		try {
			String out = new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmd1);
			// System.out.println(out+": "+out.split("[.]")[0]+" "+out.split("[.]")[1]);
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

		return false;
	}

}
