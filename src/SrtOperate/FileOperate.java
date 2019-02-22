package SrtOperate;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperate {//srt file Operating class

	public HashMap File2Map(String filename) {//时间轴、中文、外文格式的srt的to map

		HashMap<long[], String[]> map = new HashMap();
		LineOperate lineOperate = new LineOperate();
		File file = new File(filename);
		BufferedReader reader = null;
		InputStreamReader isr = null;
		String line;
		String StringTemp[] = new String[2];
		long TimeTemp[] = new long[2];
		/*
		 * ���ڱ���Ƿ����ʱ�䡢����
		 */
		boolean flag_time = false;
		boolean flag_chinese = false;

		try {
			isr = new InputStreamReader(new FileInputStream(file), "Unicode");
			reader = new BufferedReader(isr);
			String RegexTime = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
			String RegexChinese = ".*[\u4e00-\u9fa5].*.*";// ƥ�人�ֵ�����
			while ((line = reader.readLine()) != null) {
				if (Pattern.matches(RegexTime, line)) {// �ж�ʱ�䣬��ʱ��Ϊ��Ҫ�ж�����
					System.out.println(line);
					String s[] = lineOperate.getStartAndEndTime(line);
					TimeTemp[0] = lineOperate.timesToMs(lineOperate
							.getTimes(s[0]));
					TimeTemp[1] = lineOperate.timesToMs(lineOperate
							.getTimes(s[1]));
					flag_time = true;
					continue;
					// System.out.println(TimeTemp[0]);
				}
				if (flag_time && Pattern.matches(RegexChinese, line)) {
					StringTemp[0] = line;
					flag_chinese = true;
					flag_time = false;
					// System.out.println(line);
					continue;
				}
				if (flag_chinese && !Pattern.matches("\\n", line)) {// ƥ�����ģ������������Ǹ���Ϊ���з��һ��
					StringTemp[1] = line;
					flag_chinese = false;

					String Languages[] = new String[2];
					long[] Times = new long[2];

					Languages[0] = StringTemp[0];
					Times[0] = TimeTemp[0];
					Languages[1] = StringTemp[1];
					Times[1] = TimeTemp[1];
					map.put(Times, Languages);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return map;
	}

	public HashMap File2Map_only_foreign(String filename) {// ����method
															// ��srt�ļ�ת��Ϊmap
		System.out.println("file_only");
		HashMap<long[], String[]> map = new HashMap();
		LineOperate lineOperate = new LineOperate();
		File file = new File(filename);
		BufferedReader reader = null;
		InputStreamReader isr = null;
		String line;
		String StringTemp[] = new String[2];
		long TimeTemp[] = new long[2];
		/*
		 * ���ڱ���Ƿ����ʱ�䡢����
		 */
		boolean flag_time = false;
		boolean flag_chinese = false;

		try {
			isr = new InputStreamReader(new FileInputStream(file), "Unicode");
			reader = new BufferedReader(isr);
			String RegexTime = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
			String RegexChinese = ".*[\u4e00-\u9fa5].*.*";// ƥ�人�ֵ�����
			while ((line = reader.readLine()) != null) {
				if (Pattern.matches(RegexTime, line)) {// �ж�ʱ�䣬��ʱ��Ϊ��Ҫ�ж�����
					System.out.println(line);
					String s[] = lineOperate.getStartAndEndTime(line);
					TimeTemp[0] = lineOperate.timesToMs(lineOperate
							.getTimes(s[0]));
					TimeTemp[1] = lineOperate.timesToMs(lineOperate
							.getTimes(s[1]));
					flag_time = true;
					continue;
					// System.out.println(TimeTemp[0]);
				}

				if (flag_time) {// ƥ�����ģ������������Ǹ���Ϊ���з��һ��
					StringTemp[0] = "是呗";
					StringTemp[1] = line;
					System.out.println(line);

					// flag_chinese = false;

					String Languages[] = new String[2];
					long[] Times = new long[2];

					Languages[0] = StringTemp[0];
					Times[0] = TimeTemp[0];
					Languages[1] = StringTemp[1];
					Times[1] = TimeTemp[1];
					map.put(Times, Languages);
					System.out.println(map.isEmpty());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return map;
	}

	public HashMap file2Map_KoreanAndChinese(String filename) {// ����method ��srt�ļ�ת��Ϊmap
		System.out.println("korean and chinese");
		HashMap<long[], String[]> map = new HashMap();
		LineOperate lineOperate = new LineOperate();
		File file = new File(filename);
		if(!file.exists()){
			System.out.println("文件不存在");
			return null;
		}
		BufferedReader reader = null;
		InputStreamReader isr = null;
		String line;
		String StringTemp[] = new String[2];//暂存字符串的temp数据
		long TimeTemp[] = new long[2];
		
		boolean flag_time = false;
		boolean flag_chinese = false;

		try {
			isr = new InputStreamReader(new FileInputStream(file), "Unicode");//编码方式在此处设置，需谨慎，否则会崩溃。youtube上的字幕可能是ASCII编码
			reader = new BufferedReader(isr);
			String RegexTime = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (Pattern.matches(RegexTime, line)) {// �ж�ʱ�䣬��ʱ��Ϊ��Ҫ�ж�����
					System.out.println(line);
					String s[] = lineOperate.getStartAndEndTime(line);
					TimeTemp[0] = lineOperate.timesToMs(lineOperate
							.getTimes(s[0]));
					TimeTemp[1] = lineOperate.timesToMs(lineOperate
							.getTimes(s[1]));
					flag_time = true;
					System.out.println(line);
					continue;
					// System.out.println(TimeTemp[0]);
				}
				if (flag_time) {
					StringTemp[0] = getChinese(line);
					StringTemp[1]=getKorean(line);
					flag_time = false;
					System.out.println(line);
					
				}
				if (StringTemp[0]!=null && StringTemp[1]!=null) {
					
					String Languages[] = new String[2];//
					long[] Times = new long[2];

					Languages[0] = StringTemp[0];System.out.println(Languages[0]);
					Times[0] = TimeTemp[0];System.out.println(Times[0]);
					Languages[1] = StringTemp[1];System.out.println(Languages[1]);
					Times[1] = TimeTemp[1];System.out.println(Times[1]);
					
					map.put(Times, Languages);
				}
			}
		} catch (Exception e) {
			System.out.println("the error is"+e);
		}

		return map;
	}
	static String getKorean(String s) {
		String regex = "[\uac00-\ud7ff].*[\uac00-\ud7ff]";
		Matcher matcher = Pattern.compile(regex).matcher(s);
		if (matcher.find()) {
			String string = matcher.group(0);
			return string;
		}
		return null;
	}

	static String getChinese(String s) {
		String regex = "[\u4e00-\u9fa5].*[\u4e00-\u9fa5]";
		Matcher matcher = Pattern.compile(regex).matcher(s);
		if (matcher.find()) {
			String string = matcher.group(0);
			return string;
		}
		return null;
	}

	public void save_languages(int id, String chinese, String foreign,
			String path) {
		File output_c = new File(path + id + "_c.txt");
		File output_f = new File(path + id + "_f.txt");
		try {
			FileOutputStream out_c = new FileOutputStream(output_c, true);
			FileOutputStream out_f = new FileOutputStream(output_f, true);
			byte[] c = chinese.getBytes();
			byte[] f = foreign.getBytes();
			out_c.write(c);
			out_f.write(f);
			out_c.close();
			out_f.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		FileOperate foFileOperate=new FileOperate();
		HashMap<long[], String[]> h=foFileOperate.file2Map_KoreanAndChinese("f:\\The.Big.Bang.Theory.S09E03.txt");
	}
}
