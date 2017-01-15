// Team T100-01
	// Will Cray – wcc8fs
	// Weston Pruitt – wmp2zr
	// Claire Baker - cjb4pf
	// Casey Hartless – cah8ax
	// Daniel Costello- dsc5ad
//Sources
	//http://developer.android.com/index.html
	//http://stackoverflow.com
	//http://www.youtube.com/watch?v=xtsyrKdPZVw&list=PL9E21BFF408167ED6
	//http://www.javacodegeeks.com/tutorials/android-tutorials/android-game-tutorials/

package com.example.test;


import com.example.test.R;
import com.example.test.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splash_activity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
					Intent openMainActivity = new Intent(splash_activity.this, MainActivity.class);
					startActivity(openMainActivity);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
}

