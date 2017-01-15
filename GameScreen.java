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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		
		Thread main = new Thread();
		try{
			main.run();
		}
		catch(Exception E){
			Intent openHighScores = new Intent(GameScreen.this, HighScores.class);
		}
	}

}
