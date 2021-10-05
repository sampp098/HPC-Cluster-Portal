
package bab.mvc;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bab.mvc.data.services.UserService;
import bab.mvc.data.ssh.DiskSSH;
import bab.mvc.data.ssh.Qstat;

@Component
public class JobScheduler {
	

    @Scheduled(fixedRate = Execute.activationEmailExpireSchedul_ms) 
	private void activationEmailExpiered() {
		new UserService().deleteActivationExpired();
		new DiskSSH().StorageEndTimeJob();
		new DiskSSH().expired();
	}
	
    @Scheduled(fixedRate = Execute.jobMonitoringSchedul_ms)
	private void jobMonitoring() {
		Qstat.applyMonitorAll();
	}
	
}
