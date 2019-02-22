package SrtOperate;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jnr.ffi.Struct.int16_t;

import AudioOperate.main;

public class test {
	public static void main(String[] args) {
		FileOperate f = new FileOperate();
		f.file2Map_KoreanAndChinese("F:\\You're Beautiful05.txt");
	}
	 
}
