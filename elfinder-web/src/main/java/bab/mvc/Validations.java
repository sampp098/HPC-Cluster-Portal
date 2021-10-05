package bab.mvc;

public class Validations {
	public static boolean ncodeValidation(String ncode){
        int s=0;
        for (int i = 0; i < ncode.length()-1 && ncode.length()-1==9; i++) {
            s+=Integer.parseInt(""+ncode.charAt(i))*(10-i);
        }
        s=s%11;
        //System.out.println(""+s);
        int r=(s>1)?11-s:s;
        
        return (r==Integer.parseInt(""+ncode.charAt(9)))? true:false;
    }
}
