package AudioOperate;

import Python_audio.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import SrtOperate.FileOperate;

public class CutAudio {
	public static void main(String[] args) {// 在此处测试。。
		HashMap<long[], String[]> hm = new FileOperate()
				.file2Map_KoreanAndChinese("I:\\AudioPro\\source\\srt\\111.txt");
		new CutAudio().audio_cut(hm, "I:\\AudioPro\\product\\wavs", "I:\\AudioPro\\product\\words",
				"I:\\AudioPro\\source\\wav\\niceboy.wav");

	}
	Python_audio.test t=new Python_audio.test();
	static long BITS = 320;
	FileOperate fileOperate = new FileOperate();

	
	


	public void audio_cut(HashMap<long[], String[]> hm, String save_path_audio,
			String save_paht_language, String input_audio) {//按照map里的时间段剪辑音乐并且保存字幕、wav文件
		File inputFile = new File(input_audio);
		Set<Entry<long[], String[]>> set = hm.entrySet();
		Iterator<Entry<long[], String[]>> iterator = set.iterator();
		int id = 1;
		while (iterator.hasNext()) {
			Map.Entry<long[], String[]> me = (Map.Entry<long[], String[]>) iterator
					.next();
			String[] language = new String[2];
			language = me.getValue();
			long[] times = new long[2];
			times = me.getKey();
			fileOperate.save_languages(id, language[0], language[1],
					save_paht_language);
			
			//File out = new File(save_path_audio + id + ".mp3");
			String save=save_path_audio+id+".wav";
			//cut1(in, times[0], times[1], out);
			t.wav_cutter((int)times[0], (int)times[1], input_audio, save);
			id++;

		}

	}

}
