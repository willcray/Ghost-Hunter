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

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Coin {
	
	
	 private Bitmap bitmap; 
	 private int x;   
	 private int y;   
	 
	public Coin(Bitmap bitmap, int x, int y) {
	  this.bitmap = bitmap;
	  this.x = x;
	  this.y = y;
	 }
	
	 public Bitmap getBitmap() {
	  return bitmap;
	 }
	 public void setBitmap(Bitmap bitmap) {
	  this.bitmap = bitmap;
	 }
	

	 public int getX() {
	  return x;
	 }
	 public void setX(int x) {
	  this.x = x;
	 }
	 public int getY() {
	  return y;
	 }
	 public void setY(int y) {
	  this.y = y;
	 }
	 
	 
	 
	 public void draw(Canvas canvas) {
	  canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	 
	 }
}
