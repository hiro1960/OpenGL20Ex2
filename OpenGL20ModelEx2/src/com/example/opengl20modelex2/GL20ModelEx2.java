package com.example.opengl20modelex2;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;

public class GL20ModelEx2 extends Activity {
	
	private GLSurfaceView glView;
	
	//�A�N�e�B�r�e�B�������ɌĂ΂��
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        //GL�T�[�t�F�C�X�r���[
        glView=new GLSurfaceView(this);
        glView.setEGLContextClientVersion(2);
        glView.setRenderer(new GLRenderer(this));
        setContentView(glView);
    }
    
    //�A�N�e�B�r�e�B���W���[�����ɌĂ΂��
    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
    }
    
    //�A�N�e�B�r�e�B�|�[�Y���ɌĂ΂��
    @Override
    public void onPause() {
        super.onPause();
        glView.onPause();
    }

}
