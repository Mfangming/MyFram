package com.fangming.ffmtest;

public class Jnitest {
	static{
		System.loadLibrary("ffmpeg");
		System.loadLibrary("jnitest");
	}

//	public static native int decode(String url,int pNumOfFrames,Class pObject);
//
//	public static native String encode(String url);
//	public static native String play(String url);
//	播放mp3
//	public static native int getVoice(String path);
//
//	public static native int VideoPlayerStop();

//	public static native int PushVadio(String filename,String url);

	//截取视频帧保存为图片
	public static native int playVidio(Object pObject,
									   String pVideoFileName, int pNumOfFrames);

	//将YUV转成ARGB_8888
	public static native int[] decodeYUV420SP(byte[] data,
											  int width, int height);

	//将YUV转成ARGB_8888
	public static native int[] decodeYUV420SP2(byte[] data,
											   int width, int height);

	//将yuv编码称h264
	public static native int decodeH264(byte[] data,
										int width, int height);

	//testhandler
	public static native void testHandler(Object pObject,
										  String pVideoFileName, int pNumOfFrames);

	//停止播放
	public static native void stoplay();

	public static native void netFramPlay(Object pObject,String url);



}
