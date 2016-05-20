package UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 小音效类
 * @author PfC
 *
 */
public class MinSound {
	public String musicName;//音乐名称及路径
	private InputStream in;
	private AudioStream ais;
	public MinSound(String name) throws IOException{
		musicName=name;
		in=new FileInputStream(musicName);
		ais=new AudioStream(in);
		AudioPlayer.player.start(ais);
	}
}
