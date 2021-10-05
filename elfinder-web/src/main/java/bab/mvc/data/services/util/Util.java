package bab.mvc.data.services.util;

public class Util {
	public static String getTime(double hours){
		System.out.println("hours=>:"+hours);
		
		double mins=(int)(hours*60);
		System.out.println("time=>:"+(int)(mins/60)+":"+(int)(mins%60));
		return (int)(mins/60)+":"+(int)(mins%60);
	}
	public static String nullCheck(String str){
		if(str!=null) {
			return str.equals("null")?"":str;
		}
		return "";
	}
	
	
}
