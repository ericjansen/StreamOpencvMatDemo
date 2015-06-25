package com.example.server2;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Handler handler;
	public Serverthread serverthread;
	public ImageView image;

	public Handler getHandler(){
		return this.handler;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageView) findViewById(R.id.image);
		handler=new Handler(){
			public void handleMessage(Message msg){
				Bitmap getimage=(Bitmap)msg.obj;
				image.setImageBitmap(getimage);


			}
		};
	}
	// start server socket button
	public void startsocket(View v) {
		serverthread =new Serverthread(this);
		serverthread.start();
		Log.i("socket", "start socket server");
		((Button)findViewById(R.id.startsocket)).setEnabled(false);

	}
}
