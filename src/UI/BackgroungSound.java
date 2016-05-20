package UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.audio.AudioStream;

/**
 * 背景音乐类
 * @author PfC
 *
 */
public class BackgroungSound extends Thread{
	public String musicName;//音乐名称及路径
	private InputStream in;
	private AudioStream ais;
	public BackgroungSound(String name) throws IOException{
		musicName=name;
		in=new FileInputStream(musicName);
		ais=new AudioStream(in);
		this.start();
	}
	public void run(){
		  
		  //循环播放
		  while(true){
			   sun.audio.AudioPlayer.player.start(ais);//开始播放
			   try{
			    Thread.sleep(5000);//音频播放时间
			   }catch(Exception e){
				   System.out.println(e);
			   }
		   }
	}
}
