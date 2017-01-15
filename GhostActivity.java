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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GhostActivity extends Activity implements OnClickListener {

	private static final String TAG = GhostActivity.class.getSimpleName();
	
	private int score;
	private TextView scoreTxt;
	
	private SharedPreferences gamePrefs;
	public static final String GAME_PREFS = "ArithmeticFile";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		gamePrefs = getSharedPreferences(GAME_PREFS, 0);
//		scoreTxt = (TextView) findViewById(R.id.editText1);


		FrameLayout game = new FrameLayout(this);
		
		SurfaceView gameView = new MainGamePanel(this);
		TextView scoreTxt = new TextView(this);
		ImageView coin = new ImageView(this); 
		coin.setImageResource(R.drawable.coin);
		coin.setX(10);
		coin.setY(10);
		
		
		
		scoreTxt.setGravity(Gravity.TOP);

//		Button one = new Button(this);
//		one.setWidth(100);
//		one.setHeight(100);
		
		
//		one.setMaxWidth(40);
//		one.setMinWidth(10);
//		one.setMaxHeight(40);
//		one.setMinHeight(10);
//		one.setGravity(Gravity.BOTTOM);
//		 one.setX(200);
//		 one.setY(100);
		
//		 Button two = new Button(this);
//		two.setMaxWidth(40);
//		two.setMinWidth(10);
//		two.setMaxHeight(40);
//		two.setMinHeight(10);
//		two.setX(200);
//		two.setY(50);
		
		game.addView(gameView);
//		game.addView(one);
//		game.addView(two);
		game.addView(scoreTxt);
		//game.addView(coin);

		setContentView(game);

		Log.d(TAG, "View added");
		
//		one.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		two.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
		setHighScore();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	private void setHighScore(){
		//set high score
		int exScore = getScore();
		List<Score> scoreStrings = new ArrayList<Score>();
		if(exScore>0){
			SharedPreferences.Editor scoreEdit = gamePrefs.edit();
			DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
			String dateOutput = dateForm.format(new Date());
			String scores = gamePrefs.getString("highScores", "");
			if(scores.length()>0){
				String[] exScores = scores.split("\\|");
				for(String eSc : exScores){
					String[] parts = eSc.split(" - ");
					scoreStrings.add(new Score(parts[0], Integer.parseInt(parts[1])));
				}
				Score newScore = new Score(dateOutput, exScore);
				scoreStrings.add(newScore);
				Collections.sort(scoreStrings);
				StringBuilder scoreBuild = new StringBuilder("");
				for(int s=0; s<scoreStrings.size(); s++){
					if(s>=10) break;
					if(s>0) scoreBuild.append("|");
					scoreBuild.append(scoreStrings.get(s).getScoreText());
				}
				scoreEdit.putString("highScores", scoreBuild.toString());
				scoreEdit.commit();

			}
			else{
				scoreEdit.putString("highScores", ""+dateOutput+" - "+exScore);
				scoreEdit.commit();
			}
		}
		}

	private int getScore() {
		String scoreStr = scoreTxt.getText().toString();
		return Integer
				.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ") + 1));
	


}
}