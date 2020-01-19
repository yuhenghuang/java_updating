import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineEditor{
    final int MAX_LINE=10;
    int i=0;
    StringBuffer[] strBuf=new StringBuffer[MAX_LINE];
    public LineEditor(){
        BufferedReader buf;
        String str;
        try {
            buf = new BufferedReader(new InputStreamReader(System.in));
            for (i=0; i<MAX_LINE; i++){
                System.out.print("Input in line No."+i+"\t");
                str=buf.readLine();
                strBuf[i]=new StringBuffer(str);
            }
        } catch (IOException e){}
    }

    public void edit(){
        System.out.println("append, delete, modifyLine, list, replace");
        String input="";
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                input=buf.readLine();
            } catch(IOException e) {}

            String[] args = input.split("\\s+");
            switch(args[0]){
                case "a": append(args[1]); break;
                case "m": modifyLine(Integer.parseInt(args[1]), args[2]); break;
                case "r": replace(Integer.parseInt(args[1]), args[2], args[3]); break;
                case "l": list(); break;
                case "d": delete(Integer.parseInt(args[1])); break;
                case "e": System.out.println("Exiting the program...");; System.exit(0); 
                default: System.out.println("Invalid input");
            }
        }
    }

    public void append(String newString){
        for (i=0; i<MAX_LINE; i++){
            if (strBuf[i].length()==0 || i==MAX_LINE-1){
                strBuf[i] = new StringBuffer(newString);
                break;
            }
        }
    }

    public void delete(int linenum){
        for (i=linenum; i<MAX_LINE-1; i++){
            strBuf[i]=strBuf[i+1];
        }
        if (linenum==MAX_LINE-1) strBuf[linenum]=new StringBuffer();
    }

    public void modifyLine(int linenum, String newString){
        strBuf[linenum] = new StringBuffer(newString);
    }

    public void replace(int linenum, String s, String d){
        int st, ed;
        st = strBuf[linenum].indexOf(s);
        if (st>-1){
            while (st>-1){
                ed=st+s.length();
                strBuf[linenum].replace(st, ed, d);
                st = strBuf[linenum].indexOf(s);
            }
            System.out.println("The line after replaced is\n"+strBuf[linenum]);
        } else {
            System.out.println(s+" is not found in line "+linenum);
        }
    }

    public void list(){
        for (i=0; i<MAX_LINE; i++){
            if (strBuf[i].length()>0){
                System.out.println("line "+i+": "+strBuf[i]);
            }
        }
    }

    public static void main(String[] args) {
        LineEditor le=new LineEditor();
        le.edit();
    }
}