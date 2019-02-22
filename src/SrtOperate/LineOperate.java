package SrtOperate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AudioOperate.main;

public class LineOperate {// ��ÿ��line���в�������
	static String[] getStartAndEndTime(String line) {// ��ʱ���-->�ֿ��ķ��������ض�ά����
		Pattern p = Pattern.compile("(\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d)");
		Matcher m = p.matcher(line);
		String times[] = new String[2];
		times[0] = m.find() ? m.group() : null;
		times[1] = m.find() ? m.group() : null;
		// System.out.println("Start:" + times[0] + "End:" + times[1]);
		if (times[0] == null || times[1] == null)
			return null;
		return times;
	}

	static int[] getTimes(String inTime) {// ��Stringʱ��ת��Ϊint����
		int[] times = new int[4];
		String tmp[] = inTime.split(",");
		times[3] = Integer.parseInt(tmp[1]);
		String time[] = tmp[0].split(":");
		times[0] = Integer.parseInt(time[0]);
		times[1] = Integer.parseInt(time[1]);
		times[2] = Integer.parseInt(time[2]);
		return times;
	}

	public long timesToMs(int times[]) {// �����淽����int����ת��Ϊ����
		long ms;
		System.out.println(times[0]+" "+times[1]+" "+times[2]+" "+times[3]);
		ms = (times[0] * 3600 + times[1] * 60 + times[2]) * 1000 + times[3];
		return ms;
	}
	public static void main(String[] args) {
		System.out.println(new LineOperate().timesToMs(getTimes("00:19:01,859")));;
	}
}
