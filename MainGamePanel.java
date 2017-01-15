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

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {
	private static final String TAG = MainGamePanel.class.getSimpleName();

	public long past;
	public long tick = 8000;

	private MainThread thread;

	private Character character;

	private ArrayList<Ghost> ghostArrayList = new ArrayList<Ghost>();

	private ArrayList<Laser> laserArrayList = new ArrayList<Laser>();

	private ArrayList<Explosion> explosionArrayList = new ArrayList<Explosion>();

	private ArrayList<Coin> coins = new ArrayList<Coin>();

	int I = 0;

	private Context mContext;

	private int score;
	private TextView scoreTxt;

	public MainGamePanel(Context context) {
		super(context);
		this.mContext = context;
		getHolder().addCallback(this);

		character = new Character(BitmapFactory.decodeResource(getResources(),
				R.drawable.characternew), 50, 300);

		thread = new MainThread(getHolder(), this);
		setFocusable(true);

		scoreTxt = (TextView) findViewById(R.id.editText1);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN && character != null) {

			character.handleActionDown((int) event.getX(), (int) event.getY());

			laserArrayList.add(new Laser(character.getX() + 40, character
					.getY() + 5));

			// if (event.getY() > getHeight() - 50) {
			// thread.setRunning(false);
			// ((Activity) getContext()).finish();
			// } else {
			//
			// Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			// }
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE && character != null) {

			if (character.isTouched()) {

				character.setX((int) event.getX());
				character.setY((int) event.getY());

			}

		}

		if (event.getAction() == MotionEvent.ACTION_UP && character != null) {
			// touch was released
			if (character.isTouched()) {
				character.setTouched(false);
			}

		}
		return true;
	}

	Paint r = new Paint();
	Paint y = new Paint();

	public void render(Canvas canvas) {
		r.setTextSize((float) 45);
		r.setColor(Color.RED);
		y.setColor(Color.YELLOW);
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Score: " + this.getScore(score), (float) 20.0,
				(float) 50.0, r);
		if (character != null) {
			if (I == 1) {
				canvas.drawCircle(character.getX(), character.getY(), 150, y);
			}

			for (int x = 0; x < ghostArrayList.size(); x++) {
				if (ghostArrayList != null) {
					ghostArrayList.get(x).draw(canvas);
				}
			}

			if (character != null) {
				character.draw(canvas);
			}

			if (coins != null) {
				for (int x = 0; x < coins.size(); x++) {
					coins.get(x).draw(canvas);
				}
			}

			for (int x = 0; x < laserArrayList.size(); x++) {
				if (laserArrayList != null) {
					canvas.drawCircle(laserArrayList.get(x).getX(),
							laserArrayList.get(x).getY(), 10, r);
				}
			}

			if (explosionArrayList != null) {
				for (int x = 0; x < explosionArrayList.size(); x++) {
					explosionArrayList.get(x).draw(canvas);
				}
			}
		}
	}

	public int randomX = 0;
	public int randomY = 0;

	public void update() {
		randomX = (int) (Math.random() * 1200);
		if (Math.random() <= 0.5) {
			randomY = -20;
		} else {
			randomY = 920;
		}

		if (tick > 1000) {
			tick = tick - 2;
		}

		if (score > 1000 && score < 2000 || score > 4000 && score < 5000
				|| score > 10000 && score < 12000) {
			I = 1;
		} else {
			I = 0;
		}

		if (character != null) {
			if (System.currentTimeMillis() > past + tick) {
				ghostArrayList
						.add(new Ghost(BitmapFactory.decodeResource(
								getResources(), R.drawable.newghost), randomX,
								randomY));
				past = System.currentTimeMillis();

			}

			for (int x = 0; x < ghostArrayList.size(); x++) {
				// Ghost
				// check collision with right wall if heading right
				if (ghostArrayList.get(x).getxDirection() == ghostArrayList
						.get(x).DIRECTION_RIGHT
						&& ghostArrayList.get(x).getX()
								+ ghostArrayList.get(x).getBitmap().getWidth()
								/ 2 >= getWidth()) {
					ghostArrayList.get(x).toggleXDirection();
				}
				// check collision with left wall if heading left
				if (ghostArrayList.get(x).getxDirection() == ghostArrayList
						.get(x).DIRECTION_LEFT
						&& ghostArrayList.get(x).getX()
								- ghostArrayList.get(x).getBitmap().getWidth()
								/ 2 <= 0) {
					ghostArrayList.get(x).toggleXDirection();
				}
				// check collision with bottom wall if heading down
				if (ghostArrayList.get(x).getyDirection() == ghostArrayList
						.get(x).DIRECTION_DOWN
						&& ghostArrayList.get(x).getY()
								+ ghostArrayList.get(x).getBitmap().getHeight()
								/ 2 >= getHeight()) {
					ghostArrayList.get(x).toggleYDirection();
				}
				// check collision with top wall if heading up
				if (ghostArrayList.get(x).getyDirection() == ghostArrayList
						.get(x).DIRECTION_UP
						&& ghostArrayList.get(x).getY()
								- ghostArrayList.get(x).getBitmap().getHeight()
								/ 2 <= 0) {
					ghostArrayList.get(x).toggleYDirection();
				}

			}

			int Xbuffer = 100;
			int Ybuffer = 100;

			if (ghostArrayList != null) {
				for (int x = 0; x < ghostArrayList.size(); x++) {
					if (!(ghostArrayList.get(x).getX() - Xbuffer <= character
							.getX()
							&& character.getX() <= ghostArrayList.get(x).getX()
									+ Xbuffer
							&& ghostArrayList.get(x).getY() - Ybuffer <= character
									.getY() && character.getY() <= ghostArrayList
							.get(x).getY() + Ybuffer)) {
						ghostArrayList.get(x).update();
						character.update();
					} else {
						ghostArrayList.get(x).update();
						if (I == 1) {
							explosionArrayList.add(new Explosion(ghostArrayList
									.get(x).getX(), ghostArrayList.get(x)
									.getY()));
							ghostArrayList.remove(x);
							score = score + 100;
						} else {
							character = null;
						}

					}

				}
			}

			if (coins != null && character != null) {
				for (int w = 0; w < coins.size(); w++) {
					if (character.getX() - 20 <= coins.get(w).getX()
							&& coins.get(w).getX() <= character.getX() + 20
							&& character.getY() - 20 <= coins.get(w).getY()
							&& coins.get(w).getY() <= character.getY() + 20) {
						coins.remove(w);
						score += 500;
					}
				}
			}

			if (laserArrayList != null && ghostArrayList != null
					&& character != null) {
				for (int x = 0; x < laserArrayList.size(); x++) {
					for (int w = 0; w < ghostArrayList.size(); w++) {
						if (!(laserArrayList.get(x).getX() - 20 <= ghostArrayList
								.get(w).getX()
								&& ghostArrayList.get(w).getX() <= laserArrayList
										.get(x).getX() + 20
								&& laserArrayList.get(x).getY() - 20 <= ghostArrayList
										.get(w).getY() && ghostArrayList.get(w)
								.getY() <= laserArrayList.get(x).getY() + 20)) {
							laserArrayList.get(x).update();
						} else {
							explosionArrayList.add(new Explosion(ghostArrayList
									.get(w).getX(), ghostArrayList.get(w)
									.getY()));

							if (score % 1000 == 0) {
								coins.add(new Coin(BitmapFactory
										.decodeResource(getResources(),
												R.drawable.coin),
										ghostArrayList.get(w).getX(),
										ghostArrayList.get(w).getY()));
							}
							ghostArrayList.remove(w);
							score = score + 100;
							laserArrayList.remove(x);
							break;
						}
					}
				}
				for (int x = 0; x < laserArrayList.size(); x++) {
					laserArrayList.get(x).update();
				}
			}
			for (int w = 0; w < ghostArrayList.size(); w++) {
				ghostArrayList.get(w).update();
			}

			if (explosionArrayList != null) {
				for (int x = 0; x < explosionArrayList.size(); x++) {
					explosionArrayList.get(x).update();
				}
			}
		} else {
			thread.setRunning(false);
			Intent scoreScreenIntent = new Intent(mContext, HighScores.class);
			mContext.startActivity(scoreScreenIntent);

		}

	}

	private String getScore(int score) {
		score = this.score;
		Integer newScore = new Integer(score);
		return newScore.toString();

	}
}
