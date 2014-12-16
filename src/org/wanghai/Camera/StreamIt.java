package org.wanghai.Camera;

import java.io.ByteArrayOutputStream;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;

import com.shotsurfaceview.MyThread;

public class StreamIt implements Camera.PreviewCallback {
	private String ipname;
	public ByteArrayOutputStream outstream;

	public StreamIt(String ipname) {
		this.ipname = ipname;
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		//Log.e("StreamIt", "onPreviewFrame");
		Size size = camera.getParameters().getPreviewSize();
		try {
			// 调用image.compressToJpeg（）将YUV格式图像数据data转为jpg格式
			YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width,
					size.height, null);
			if (image != null) {
				this.outstream = new ByteArrayOutputStream();
				image.compressToJpeg(new Rect(0, 0, size.width, size.height),
						80, outstream);
				this.outstream.flush();
				// 启用线程将图像数据发送出去
				//Thread th = new MyThread(this.outstream, ipname);
				Thread th = new MyThread(this, ipname);
				th.start();
			}

		} catch (Exception ex) {
			Log.e("Sys", "Error:" + ex.getMessage());
		}
	}
}