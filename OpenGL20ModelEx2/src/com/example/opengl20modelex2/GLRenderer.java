package com.example.opengl20modelex2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import OpenGL20common.Figure;
import OpenGL20common.GLES;
import OpenGL20common.ObjLoader;
import OpenGL20common.Object3D;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class GLRenderer implements GLSurfaceView.Renderer {
	
	//�V�X�e��
    private float aspect;//�A�X�y�N�g��
    private int   tick;  //���Ԍo��
    
    //���f��
    private Object3D model=new Object3D();
    private Object3D handL=new Object3D();
    private Object3D handR=new Object3D();
    private Object3D legL =new Object3D();
    private Object3D legR =new Object3D();

  //�R���X�g���N�^
    public GLRenderer(Context context) {
        GLES.context=context;
    }
    
    //�T�[�t�F�C�X�������ɌĂ΂��
    @Override
    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {
        //�v���O�����̐���
        GLES.makeProgram();
        
        //�f�v�X�o�b�t�@�̗L����
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        
        //�����̗L����
        GLES20.glUniform1i(GLES.useLightHandle,1);
        
        //�����F�̎w��
        GLES20.glUniform4f(GLES.lightAmbientHandle,0.2f,0.2f,0.2f,1.0f);
        GLES20.glUniform4f(GLES.lightDiffuseHandle,0.7f,0.7f,0.7f,1.0f);
        GLES20.glUniform4f(GLES.lightSpecularHandle,0.9f,0.9f,0.9f,1.0f);
        
        //���f���̓ǂݍ���
        try {
            //��
            Figure handFigure=ObjLoader.load("droid_hand.obj");
            handL.figure=handFigure;
            handL.position.set(-0.65f,1.24f,0.0f);
            handR.figure=handFigure;
            handR.position.set( 0.65f,1.24f,0.0f);
            
            //��
            Figure legFigure=ObjLoader.load("droid_leg.obj");
            legL.figure=legFigure;
            legL.position.set(-0.2f,0.5f,0.0f);
            legR.figure=legFigure;
            legR.position.set( 0.2f,0.5f,0.0f);

            //��
            model.figure=ObjLoader.load("droid_body.obj");
            model.childs.add(handL);
            model.childs.add(handR);
            model.childs.add(legL);
            model.childs.add(legR);
        } catch (Exception e) {
            android.util.Log.e("debug",e.toString());
            for (StackTraceElement ste:e.getStackTrace()) {
                android.util.Log.e("debug","    "+ste);
            }
        }        
    }
    
    //��ʃT�C�Y�ύX���ɌĂ΂��
    @Override
    public void onSurfaceChanged(GL10 gl10,int w,int h) {
        //�r���[�|�[�g�ϊ�
        GLES20.glViewport(0,0,w,h);
        aspect=(float)w/(float)h;
    }
    
    //���t���[���`�掞�ɌĂ΂��
    @Override
    public void onDrawFrame(GL10 gl10) {
        //��ʂ̃N���A
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);  
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|
            GLES20.GL_DEPTH_BUFFER_BIT);
                
        //�ˉe�ϊ�
        Matrix.setIdentityM(GLES.pMatrix,0);
        GLES.gluPerspective(GLES.pMatrix,
            45.0f,  //Y�����̉�p
            aspect, //�A�X�y�N�g��
            0.01f,  //�j�A�N���b�v
            100.0f);//�t�@�[�N���b�v                
        
        //�����ʒu�̎w��
        GLES20.glUniform4f(GLES.lightPosHandle,5.0f,5.0f,5.0f,1.0f);
        
        //�r���[�ϊ�
        Matrix.setIdentityM(GLES.mMatrix,0);
        GLES.gluLookAt(GLES.mMatrix,
            0.0f,0.8f,5.0f, //�J�����̎��_
            0.0f,0.8f,0.0f, //�J�����̏œ_
            0.0f,1.0f,0.0f);//�J�����̏����
        
        //���f���ϊ�
        Matrix.rotateM(GLES.mMatrix,0,tick++,0,1,0);
        
        //����E�E��
        int rotate=tick%180;
        rotate=(rotate<90)?rotate-45:180-45-rotate;
        handL.rotate.x=rotate;
        legR.rotate.x=rotate;
        
        //�E��E����
        rotate=(tick+90)%180;
        rotate=(rotate<90)?rotate-45:180-45-rotate;
        handR.rotate.x=rotate;
        legL.rotate.x=rotate;        
        
        //���f���̕`��
        model.draw();        
    }

}
