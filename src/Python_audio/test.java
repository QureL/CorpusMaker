package Python_audio;

import java.io.*;
import org.python.*;
public class test {//the class that imports Python Script.
	public static void main(String[] arg) {
		new test().wav_cutter(0, 10000, "I:\\AudioPro\\source\\wav\\niceboy.wav", "I:\\AudioPro\\source\\wav\\niceboy2.wav");
	}
	public void wav_cutter(int starttime,int endtime,String filename,String savename){
		try {
		    String[] args = new String[] { "python", "cutter.py",filename,savename, String.valueOf(starttime), String.valueOf(endtime) };
		    Process proc = Runtime.getRuntime().exec(args);// 执行py文件

		    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		    String line = null;
		    while ((line = in.readLine()) != null) {
		        System.out.println(line);
		    }
		    in.close();
		    proc.waitFor();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}
