package main.java.com.fm.opengl;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.DisplayMetrics;
import android.util.Log;

public class OpenGLRenderer implements Renderer {
	private String _TAG = "OpenGLRenderer";
	private GLSurfaceView mTargetSurface;
	private GLProgram prog = new GLProgram(0);
	private int mVideoWidth = -1, mVideoHeight = -1;
	private int mScreenWidth, mScreenHeight;
	private ByteBuffer y;
	private ByteBuffer u;
	private ByteBuffer v;

    public OpenGLRenderer(ISimplePlayer callback, GLSurfaceView surface, DisplayMetrics dm) {
        //mParentAct = callback;
        mTargetSurface = surface;
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }
    
    public OpenGLRenderer(ISimplePlayer callback, GLSurfaceView surface, int widthPixels, int heightPixels) {
        mTargetSurface = surface;
        mScreenWidth = widthPixels;
        mScreenHeight = heightPixels;
        Log.d("GLFrameRenderer", "widthPixels:"+widthPixels+", heightPixels"+heightPixels);
    }
	

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// ������GLSurfaceViewʱ�����ã�ֻ����һ�Σ������������ִ��ֻ����һ�εĶ���
		Log.i(_TAG, "GLFrameRenderer :: onSurfaceCreated");
		if (!prog.isProgramBuilt()) {
			prog.buildProgram();
			Log.i(_TAG, "GLFrameRenderer :: buildProgram done");
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// ��GLSurfaceView������ı�ʱϵͳ���ô˷���������GLSurfaceView�Ĵ�С�ı���豸��Ļ�ķ���ı䣮ʹ�ô˷�������ӦGLSurfaceView�����ı仯
		Log.i(_TAG, "GLFrameRenderer :: onSurfaceChanged");
		GLES20.glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// ϵͳ��ÿ���ػ�GLSurfaceViewʱ���ô˷������˷����ǻ���ͼ�ζ������Ҫ��ִ�е�
		synchronized (this) {
			if (y != null) {
				// reset position, have to be done
				y.position(0);
				u.position(0);
				v.position(0);
				prog.buildTextures(y, u, v, mVideoWidth, mVideoHeight);
				GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
				GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
				prog.drawFrame();
			}
		}
	}

	/** 
     * this method will be called from native code, it happens when the video is about to play or 
     * the video size changes. 
     */  
    public void update(int w, int h) {  
    	Log.i("OpenGLRenderer","INIT E");
        if (w > 0 && h > 0) {  
            if (mScreenWidth > 0 && mScreenHeight > 0) {
                float f1 = 1f * mScreenHeight / mScreenWidth;
                float f2 = 1f * h / w;
                if (f1 == f2) {
                    prog.createBuffers(GLProgram.squareVertices);
                } else if (f1 < f2) {
                    float widScale = f1 / f2;
                    prog.createBuffers(new float[] { -widScale, -1.0f, widScale, -1.0f, -widScale, 1.0f, widScale,
                            1.0f, });
                } else {
                    float heightScale = f2 / f1;
                    prog.createBuffers(new float[] { -1.0f, -heightScale, 1.0f, -heightScale, -1.0f, heightScale, 1.0f,
                            heightScale, });
                }
            }
            if (w != mVideoWidth && h != mVideoHeight) {  
                this.mVideoWidth = w;  
                this.mVideoHeight = h;  
                int yarraySize = w * h;  
                int uvarraySize = yarraySize / 4;  
                synchronized (this) {  
                    y = ByteBuffer.allocate(yarraySize);  
                    u = ByteBuffer.allocate(uvarraySize);  
                    v = ByteBuffer.allocate(uvarraySize);  
                }  
            }  
        }  
  
    	Log.i("OpenGLRenderer","INIT X");
    } 
	
    public void update(ByteBuffer dataY, ByteBuffer dataU, ByteBuffer dataV, int w, int h) {
        synchronized (this) {
            /*
            y.clear();
            u.clear();
            v.clear();
            y.put(dataY, 0, w*h);
            u.put(dataU, 0, w*h*5/4 - w*h);
            v.put(dataV, 0, w*h*3/2 - w*h*5/4);
            */
            y=dataY;
            u=dataU;
            v=dataV;

        }

        // request to render
        mTargetSurface.requestRender();
    }
    
    /** 
     * this method will be called from native code, it's used for passing yuv data to me. 
     */  
    public void update(byte[] ydata, byte[] udata, byte[] vdata) {  
        synchronized (this) {  
            y.clear();  
            u.clear();  
            v.clear();  
            y.put(ydata, 0, ydata.length);  
            u.put(udata, 0, udata.length);  
            v.put(vdata, 0, vdata.length);  
        }  
  
        // request to render  
        mTargetSurface.requestRender();  
    }  

    

}
