package bab.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import bab.mvc.data.services.BasicsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Execute {
	
	
	//Poeral variables
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPortal
	public static long end = 1680000000000L;
	
	public static int sessionTimoutMin=new BasicsService().getPortalSessionTimout();
	
	public static String imageRepository=new BasicsService().getPortalFileRepository();
	
	public static final int jobMonitoringSchedul_ms=5 *60*1000;
	public static final int activationEmailExpireSchedul_ms=10*60*1000;
	
	public static String portalHost=new BasicsService().getPortalDomain();
	
	public static String gangliaHome=new BasicsService().getGangliaHome();
	//cluster variables
//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCluster
	
	public static String username=new BasicsService().getClusterUser();
	public static String pass=new BasicsService().getClusterPass();
	
	
	public static String host=new BasicsService().getClusterHost();
	
	public static int storagecost= new BasicsService().getClusterStorageCost(); //per Gig in day
	public static int storageSize= new BasicsService().getClusterDefaultStorageSize(); //GB
	public static String homeDir=new BasicsService().getClusterHome();

	private static final int port=new BasicsService().getClusterPort();
//MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMailer
	
	private static final String mail=new BasicsService().getEmailId();
	private static final String mailpass=new BasicsService().getEmailPass();
	
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRResources
	public static int nodes=new BasicsService().getClusterNodes();
	public static int cores=new BasicsService().getClusterCores();
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
	
	
	public String sshExe(String host, String user, String pass, String cmd) throws JSchException, IOException {
		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(pass);
		session.connect();

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(cmd);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream input = channel.getInputStream();
		channel.connect();

		System.out.println("Channel Connected to machine " + host + " server with command: " + cmd);
		String str = "";

		InputStreamReader inputReader = new InputStreamReader(input);
		BufferedReader bufferedReader = new BufferedReader(inputReader);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			str += line + "\n";
		}
		bufferedReader.close();
		inputReader.close();

		System.err.println(str);

		channel.disconnect();
		session.disconnect();

		return str;
	}
	
	public List<String> sshExe(String host, String user, String pass, List<String> cmds) throws JSchException, IOException {
		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(pass);
		session.connect();
		
		List< String> strs=new ArrayList<String>();
		
		InputStreamReader inputReader=null;
		BufferedReader bufferedReader=null;
		
		for (String cmd : cmds) {
			
			Channel channel = session.openChannel("exec");
			
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			
			((ChannelExec) channel).setCommand(cmd);
			InputStream input = channel.getInputStream();
			
			channel.connect();

			//System.out.println("Channel Connected to machine " + host + " server with command: " + cmd);
			String str = "";
			
			inputReader = new InputStreamReader(input);
			bufferedReader = new BufferedReader(inputReader);
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				str += line + "\n";
			}
			
			strs.add(str);
			bufferedReader.close();
			inputReader.close();
			channel.disconnect();
		}
		
		

		//System.err.println(str);

		
		session.disconnect();

		return strs;
	}
	
	public void upload(String username, String pass,InputStream from, String fileName, String toPath) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, Execute.port);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(pass);
        session.connect();

        
        
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.cd(toPath);
        channel.put(from, fileName);

        channel.disconnect();
    }
	
	public void download(String username, String pass, String fileName, String Path, OutputStream outputStream) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, Execute.port);
        System.out.println("-------------step0-------->");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(pass);
        session.connect();

        System.out.println("-------------step0--------connect>");
        
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        System.out.println("-------------step0--------channel>");
        channel.connect();
        System.out.println("-------------step0--------channel connect>"+ " filename:"+fileName+"  path:"+Path);
        System.out.println("----------------------------PWD1: "+channel.pwd()+" filename:"+fileName+"  path:"+Path);
        channel.cd(Path);
        System.out.println("----------------------------PWD: "+channel.pwd()+" filename:"+fileName);
        //channel.put(from, fileName);
        channel.lstat(fileName);
        channel.get(fileName,outputStream);
        
        //System.out.println("----------------------------PWD: "+channel.pwd()+" filename:"+fileName+" size:"+inputStream.available());
        channel.disconnect();
        //return inputStream;
    }
	
	public Vector<LsEntry> listFiles(String username, String pass, String path, String types) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, Execute.port);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(pass);
        session.connect();

        
        
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        System.out.println("----------------ChannelSftp------------> path: "+path);
        channel.cd(path);
        Vector<LsEntry> vector = (Vector<LsEntry>) channel.ls(types);

        channel.disconnect();
        
        return vector;
    }
	
	public boolean deleteFile(String username, String pass, String path, String filename) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, Execute.port);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(pass);
        session.connect();

        
        
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        System.out.println("----------------ChannelSftp------------> path: "+path);
        channel.cd(path);
        channel.rm(filename);
        channel.disconnect();
        
        return true;
    }
	
	public void uploadToServer(String lfile,InputStream finputstream,String host, String user, String pass, String rfile)
			throws JSchException, IOException {
		/*String[] arg = { "M:\\Help lwip zturn.txt", "sampp@192.168.134.242:file2.txt" };
		if (arg.length != 2) {
			System.err.println("usage: java ScpTo file1 user@remotehost:file2");
			System.exit(-1);
		}*/

		//FileInputStream fis = null;

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(pass);
		session.connect();

		boolean ptimestamp = true;

		// exec 'scp -t rfile' remotely
		String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + "\"" + rfile + "\"";
		System.out.println("command: " + command);
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		// get I/O streams for remote scp
		OutputStream out = channel.getOutputStream();
		InputStream in = channel.getInputStream();

		channel.connect();

		if (checkAck(in) != 0) {
			System.exit(0);
		}

		File _lfile = new File(lfile);

		if (ptimestamp) {
			command = "T " + (_lfile.lastModified() / 1000) + " 0";
			// The access time should be sent here,
			// but it is not accessible with JavaAPI ;-<
			command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
			out.write(command.getBytes());
			out.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}
		}

		// send "C0644 filesize filename", where filename should not include '/'
		long filesize = _lfile.length();
		command = "C0644 " + filesize + " ";
		if (lfile.lastIndexOf('/') > 0) {
			command += lfile.substring(lfile.lastIndexOf('/') + 1);
		} else {
			command += lfile;
		}
		command += "\n";
		out.write(command.getBytes());
		out.flush();
		if (checkAck(in) != 0) {
			System.exit(0);
		}

		// send a content of lfile
		InputStream fis = finputstream;
		System.out.println("InputStream fis = finputstream;");
		byte[] buf = new byte[1024];
		while (true) {
			int len = fis.read(buf, 0, buf.length);
			if (len <= 0) {
				break;
			}
			out.write(buf, 0, len); // out.flush();
		}
		System.out.println("while (true)");
		fis.close();
		System.out.println("fis.close();");
		fis = null;
		System.out.println("fis = null;");
		// send '\0'
		buf[0] = 0;
		out.write(buf, 0, 1);
		System.out.println("out.write(buf, 0, 1);");
		out.flush();
		System.out.println("out.flush();");
		if (checkAck(in) != 0) {
			System.out.println("if (checkAck(in) != 0)");
			System.exit(0);
		}
		System.out.println("if (checkAck(in) != 0) true");
		out.close();
		System.out.println("out.close();");
		
		channel.disconnect();
		session.disconnect();

		if (fis != null) {
			fis.close();

		}
	}
	public void uploadFromLocalToServer(String lfile,String host, String user, String pass, String rfile)
			throws JSchException, IOException {
		/*String[] arg = { "M:\\Help lwip zturn.txt", "sampp@192.168.134.242:file2.txt" };
		if (arg.length != 2) {
			System.err.println("usage: java ScpTo file1 user@remotehost:file2");
			System.exit(-1);
		}*/

		FileInputStream fis = null;

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(pass);
		session.connect();

		boolean ptimestamp = true;

		// exec 'scp -t rfile' remotely
		String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + "\"" + rfile + "\"";
		System.out.println("command: " + command);
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		// get I/O streams for remote scp
		OutputStream out = channel.getOutputStream();
		InputStream in = channel.getInputStream();

		channel.connect();

		if (checkAck(in) != 0) {
			System.exit(0);
		}

		File _lfile = new File(lfile);

		if (ptimestamp) {
			command = "T " + (_lfile.lastModified() / 1000) + " 0";
			// The access time should be sent here,
			// but it is not accessible with JavaAPI ;-<
			command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
			out.write(command.getBytes());
			out.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}
		}

		// send "C0644 filesize filename", where filename should not include '/'
		long filesize = _lfile.length();
		command = "C0644 " + filesize + " ";
		if (lfile.lastIndexOf('/') > 0) {
			command += lfile.substring(lfile.lastIndexOf('/') + 1);
		} else {
			command += lfile;
		}
		command += "\n";
		out.write(command.getBytes());
		out.flush();
		if (checkAck(in) != 0) {
			System.exit(0);
		}

		// send a content of lfile
		fis = new FileInputStream(lfile);
		byte[] buf = new byte[1024];
		while (true) {
			int len = fis.read(buf, 0, buf.length);
			if (len <= 0) {
				break;
			}
			out.write(buf, 0, len); // out.flush();
		}
		fis.close();
		fis = null;
		// send '\0'
		buf[0] = 0;
		out.write(buf, 0, 1);
		out.flush();
		if (checkAck(in) != 0) {
			System.exit(0);
		}
		out.close();

		channel.disconnect();
		session.disconnect();

		if (fis != null) {
			fis.close();

		}
	}
	private int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0) {
			return b;
		}
		if (b == -1) {
			return b;
		}

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}
	
	public boolean sendMail(String usermails, String subject, String text) {

        //Setting up configurations for the email connection to the Google SMTP server using TLS

        Properties props = new Properties();
//Armin Added
        props.put("mail.smtp.ssl.trust", "*");
        
        props.put("mail.smtp.host", "true");

        props.put("mail.smtp.starttls.enable", "true");

//        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "172.16.5.72");

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.", "true");
        //Establishing a session with required user details

        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mail, mailpass);

            }

        });

        try {

            //Creating a Message object to set the email content

            MimeMessage msg = new MimeMessage(session);

            //Storing the comma seperated values to email addresses

            String to = usermails;

            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email

            addresses in an array of InternetAddress objects*/

            InternetAddress[] address = InternetAddress.parse(to, true);

            //Setting the recepients from the address variable

            msg.setRecipients(Message.RecipientType.TO, address);

            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());

            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(mail));

            msg.setSentDate(new Date());

            msg.setText(text);

            msg.setHeader("XPriority", "1");

            Transport.send(msg);

            System.out.println("Mail has been sent successfully");
            return true;
        } catch (MessagingException mex) {

            System.out.println("Unable to send an email" + mex);
            return false;

        }

    }
	
	public static String getMD5Hex(final String inputString) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(inputString.getBytes());

	    byte[] digest = md.digest();

	    return convertByteToHex(digest);
	}

	private static String convertByteToHex(byte[] byteData) {

	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < byteData.length; i++) {
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();
	}
	
	public static void backupDB() {
		//mysqldump -u root -p --database hpc > sam.sql
	}
}
