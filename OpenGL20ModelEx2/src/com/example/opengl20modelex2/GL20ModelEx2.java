package com.example.opengl20modelex2;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;

public class GL20ModelEx2 extends Activity {
	
	private GLSurfaceView glView;
	
	//アクティビティ生成時に呼ばれる
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        //GLサーフェイスビュー
        glView=new GLSurfaceView(this);
        glView.setEGLContextClientVersion(2);
        glView.setRenderer(new GLRenderer(this));
        setContentView(glView);
    }
    
    //アクティビティレジューム時に呼ばれる
    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
    }
    
    //アクティビティポーズ時に呼ばれる
    @Override
    public void onPause() {
        super.onPause();
        glView.onPause();
    }

}
