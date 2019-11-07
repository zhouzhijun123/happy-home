package model;
import javax.swing.JOptionPane;

import static javafx.application.Platform.exit;

public class menuTool {
    public static String getItemString(String []Item){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < Item.length; i++){
            sb. append(Item[i]);
            sb. append('\n');
        }
        String s = sb.toString();
        return s;
    }
    public static int input_choice(String[] paramStr){
        String items=getItemString(paramStr);
        String str=JOptionPane.showInputDialog(null,items,"选择功能：",JOptionPane.PLAIN_MESSAGE);
        if (str==null||"".equals(str)) {
            JOptionPane.showMessageDialog(null, "欢迎下次再来");
            exit();
        }
            int num=Integer.parseInt(str);
        while(num<0||num>=paramStr.length)
        {
            str=JOptionPane.showInputDialog(null,"请重新输入！","输入错误",JOptionPane.ERROR_MESSAGE);
            num=Integer.parseInt(str);
        }

        return num;

    }
}