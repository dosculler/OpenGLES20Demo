package com.ddx.myopengles20;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dingdx on 2018/8/8.
 */

public class MyGLRender implements GLSurfaceView.Renderer{
    private Triangle mTriangle;
    private Square mSquare;
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {//仅调用一次，用于设置view的OpenGL ES环境
        //设置背景色黑色不透明，或者白色不透明(1.0f, 1.0f, 1.0f, 1.0f)
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//parameter:r/g/b/alpha, 0.0f~1.0f.
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {//当view的几何形状发生变化时调用，比如设备屏幕方向改变时
        //绘制窗口
        GLES20.glViewport(0, 0, width, height);

        //初始化三角形
        mTriangle = new Triangle();
        // 初始化正方形
        mSquare = new Square();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {//每次重绘view时调用
        //重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mTriangle.draw();
        //mSquare.draw();
    }

    /*    Shader包含OpenGL Shading Language(GLSL)代码，必须在OpenGL ES环境下先编译再使用。想要编译这些代码，需要在你的Renderer类中创建一个工具类方法*/
    public static int loadShader(int type, String shaderCode){

        //创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        //或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译它
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
