package main.java.com.fm.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OpenGLView extends GLSurfaceView {

    public OpenGLView(Context context) {
        super(context);
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    
    @Override
    protected void onAttachedToWindow() {
        android.util.Log.i("GLFrameRenderer","surface onAttachedToWindow()");
        super.onAttachedToWindow();
        // setRenderMode() only takes effectd after SurfaceView attached to window!
        // note that on this mode, surface will not render util GLSurfaceView.requestRender() is
        // called, it's good and efficient -v-
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        android.util.Log.i("GLFrameRenderer","surface setRenderMode RENDERMODE_WHEN_DIRTY");
    }

//	@Override
//	public boolean onTouchEvent(final MotionEvent event) {
//		queueEvent(new Runnable() {
//			
//			@Override
//			public void run() {
//				mRenderer.setColor(event.getX()/getWidth(), event.getY()/getHeight(), 1.0f);				
//			}
//		});
//		return super.onTouchEvent(event);
//	}
}
