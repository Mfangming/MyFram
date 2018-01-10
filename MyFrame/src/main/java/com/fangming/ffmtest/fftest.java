package main.java.com.fangming.ffmtest;

public class fftest {
	static {
		System.loadLibrary("ffmpeg");
		System.loadLibrary("testone");
	}


	public static native String exchange();

	//解码器
	public static native int decode(Object pObject,String inputurl, String outputurl);

//	public static Handler yuvSendHandler;
//
//    public static void yuvSendHandlerSet(Handler h) {
//        yuvSendHandler = h;
//    }
//
//	// jni回调
//	public void myCallbackExchange(String respone) {
//		Message msg = new Message();
//		msg.obj = respone;
//		yuvSendHandler.sendMessage(msg);
//	}

}
