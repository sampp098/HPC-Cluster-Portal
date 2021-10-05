package bab.mvc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:system.properties")
@ConfigurationProperties
public class Properties {
	
	// storage repositories
	public static final String imageRepository="/home/uuntu/ImageStorage/";
	
	// scheduler variables
	public static final int jobMonitoringSchedul_ms=5*60*1000;
	public static final int activationEmailExpireSchedul_ms=10*60*1000;
	
	//portal variables
	
	public static final String portalHost="https://192.168.134.91:8080/";
	
	//cluster variables
	
	public static final String username="masghari";
	public static final String pass="sam@@@@52242";
	
	public static final String host="cluster.hpc.ipm.ac.ir";
//	public static final String homeDir="/home/";
	public static final String homeDir="/share/users/";
	
//	public static final String shellFiles="/root/shellFiles/";
	public static final String shellFiles="/share/users/masghari/shellFiles/";
	
//	private static final int port=24222;
	private static final int port=22;
	//mailer
	private static final String mail="hpc.center.sampp@gmail.com";
	private static final String mailpass="sam52242";
}
