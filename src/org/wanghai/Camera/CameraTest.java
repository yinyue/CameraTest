package org.wanghai.Camera;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.wanghai.CameraTest.R;

import com.shotsurfaceview.MyThread;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class CameraTest extends Activity {
	private String TAG = CameraTest.class.getSimpleName();
	SurfaceView sView;
	SurfaceHolder surfaceHolder;
	int screenWidth, screenHeight;
	Camera camera; // ����ϵͳ���õ������
	boolean isPreview = false; // �Ƿ��������
	private String ipname;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		// ��ȡIP��ַ
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		ipname = data.getString("ipname");

		screenWidth = 640;
		screenHeight = 480;
		sView = (SurfaceView) findViewById(R.id.sView); // ��ȡ������SurfaceView���
		surfaceHolder = sView.getHolder(); // ���SurfaceView��SurfaceHolder

		// ΪsurfaceHolder���һ���ص�������
		surfaceHolder.addCallback(new Callback() {
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				initCamera(); // ������ͷ
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// ���camera��Ϊnull ,�ͷ�����ͷ
				if (camera != null) {
					if (isPreview)
						camera.stopPreview();
					camera.release();
					camera = null;
				}
				System.exit(0);
			}
		});
		// ���ø�SurfaceView�Լ���ά������
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	private void initCamera() {
		if (!isPreview) {
			camera = Camera.open();
		}
		if (camera != null && !isPreview) {
			try {
				Camera.Parameters parameters = camera.getParameters();
				parameters.setPreviewSize(screenWidth, screenHeight); // ����Ԥ����Ƭ�Ĵ�С
				parameters.setPreviewFpsRange(20, 30); // ÿ����ʾ20~30֡
				parameters.setPictureFormat(ImageFormat.NV21); // ����ͼƬ��ʽ
				parameters.setPictureSize(screenWidth, screenHeight); // ������Ƭ�Ĵ�С
				// camera.setParameters(parameters); // android2.3.3�Ժ���Ҫ���д���
				camera.setPreviewDisplay(surfaceHolder); // ͨ��SurfaceView��ʾȡ������
				camera.setPreviewCallback(new StreamIt(ipname)); // ���ûص�����
				camera.startPreview(); // ��ʼԤ��
				camera.autoFocus(null); // �Զ��Խ�
			} catch (Exception e) {
				e.printStackTrace();
			}
			isPreview = true;
		}
	}

}
