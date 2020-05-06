package tool;

import java.net.URLEncoder;

public class MyTools 
{
    //将String型数据转换为int型数据的方法
    public static int strToint(String str)
    {	
     	if(str==null||str.equals(""))  str="0";
	int i=0;
	try{
                i=Integer.parseInt(str);
	    }catch(NumberFormatException e)
            {
		i=0;
		e.printStackTrace();
	    }
		return i;		
    }
    public static String encode(String s)
    { String res=null;
        try{
        res=URLEncoder.encode(s,"UTF-8");}
       catch(Exception e){};
       return res;
    }
}
