package bab.mvc.data.services.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;

public class Files {
	public void saveFile(String destPath, String inputName, HttpServletRequest request)
			throws ServletException, IOException {

		Part filePart = request.getPart(inputName); // Retrieves <input type="file" name="file">
		
		if (filePart != null) {
			if(filePart.getSize() >= 30*1024*1024 || filePart.getSize()<10) {
				return;
			}
			//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();
			byte[] b = new byte[fileContent.available()];
			fileContent.read(b);
			fileContent.close();
			FileOutputStream fo = new FileOutputStream(destPath);
			fo.write(b);
			fo.close();
		}
	}
	public void saveFiles(String destPath, HttpServletRequest request)
			throws ServletException, IOException {
		 
		List<Part> fileParts =(List<Part>) request.getParts(); // Retrieves <input type="file" name="file">
		for (Part filePart : fileParts) {
			if (filePart != null) {
				if(filePart.getSize() >= 30*1024*1024 || filePart.getSize()<10) {
					continue;
				}
				//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				String fileName = extractFileName(filePart);
				if(fileName.equals("")) {
	            	continue;
	            }
	            // refines the fileName in case it is an absolute path
	            fileName = new File(fileName).getName();
				
	            
	            	
				InputStream fileContent = filePart.getInputStream();
				
				byte[] b = new byte[fileContent.available()];
				fileContent.read(b);
				fileContent.close();
				FileOutputStream fo = new FileOutputStream(destPath+fileName);
				fo.write(b);
				fo.close();
			}
		}
		
	}
	public boolean uploadFilesToServer(HttpServletRequest request, String app,String username, String pass)
			throws ServletException, IOException {
		 
		List<Part> fileParts =(List<Part>) request.getParts(); // Retrieves <input type="file" name="file">
		for (Part filePart : fileParts) {
			if (filePart != null) {
				// // MSIE fix.
				String fileName = extractFileName(filePart);
				if(fileName.equals("")) {
	            	continue;
	            }
				InputStream fileContent = filePart.getInputStream();

					try {
						System.out.println("------------------>uploading:"+Execute.homeDir+username.toLowerCase()+"/"+app+" "+fileName+" user:"+username);
						new Execute().upload(username.toLowerCase(), pass, fileContent, fileName,Execute.homeDir+username.toLowerCase()+"/"+app);
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
			}
		}
		return false;
		
	}

	public void downloadFilesFromServer(String app, String username, String pass, String fileName, OutputStream outputStream )
			throws ServletException, IOException {

		try {
			System.out.println("------------------>downloading:" + Execute.homeDir + username.toLowerCase() + "/"
					+ app + " " + fileName + " user:" + username);
			 new Execute().download(username.toLowerCase(), pass, fileName,
					Execute.homeDir + username.toLowerCase() + "/" + app,outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
	}
	
	
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
