package org.wanghai.Camera;

import org.wanghai.CameraTest.R;

//import com.shotsurfaceview.ScreenshotActivity;
//import com.shotsurfaceview.SurfaceViewActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class GetIP extends Activity {
	private String ipname = null;
	private EditText iptext;
	private Bundle data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// …Ë÷√»´∆¡
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.ip_main);

		iptext = (EditText) findViewById(R.id.ipedittext);
		Button camera = (Button) findViewById(R.id.camera);
		//Button surfaceview = (Button) findViewById(R.id.surfaceview);
		//Button activity_view = (Button) findViewById(R.id.activity_view);

		//ipname = iptext.getText().toString().trim();
		//data = new Bundle();
		//data.putString("ipname", ipname);

		camera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ipname = iptext.getText().toString().trim();
				data = new Bundle();
				data.putString("ipname", ipname);
				Intent intent = new Intent(GetIP.this, CameraTest.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});

//		surfaceview.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				data.putString("ipname", ipname);
//				// Intent intent = new Intent(GetIP.this,CameraTest.class);
//				Intent intent = new Intent(GetIP.this, SurfaceViewActivity.class);
//				intent.putExtras(data);
//				startActivity(intent);
//			}
//		});
//
//		activity_view.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				data.putString("ipname", ipname);
//				// Intent intent = new Intent(GetIP.this,CameraTest.class);
//				Intent intent = new Intent(GetIP.this, ScreenshotActivity.class);
//				intent.putExtras(data);
//				startActivity(intent);
//			}
//		});

	}

}