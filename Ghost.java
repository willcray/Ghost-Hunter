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
import android.view.MotionEvent;

public class Ghost {
 private Bitmap bitmap; 
 private int x;   
 private int y;   
 
 public final int DIRECTION_RIGHT = 1;
 public final int DIRECTION_LEFT  = -1;
 public final int DIRECTION_UP    = -1;
 public final int DIRECTION_DOWN  = 1;

 private float xv = 1;   // velocity value on the X axis
 private float yv = 1;   // velocity value on the Y axis
 private int xDirection = DIRECTION_RIGHT;
 private int yDirection = DIRECTION_DOWN;
 
public Ghost(Bitmap bitmap, int x, int y) {
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
 public void update() {
	     
	         x += (this.getXv() * this.getxDirection());
	         y += (this.getYv() * this.getyDirection());     
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
 
 public float getXv() {
     return xv;
 }
 
 public void setXv(float xv) {
     this.xv = xv;
 }
 
 public float getYv() {
     return yv;
 }
 
 public void setYv(float yv) {
     this.yv = yv;
 }
 
 public int getxDirection() {
     return xDirection;
 }
 
 public void setxDirection(int xDirection) {
     this.xDirection = xDirection;
 }
 public int getyDirection() {
     return yDirection;
 }
 public void setyDirection(int yDirection) {
     this.yDirection = yDirection;
 }
 // changes the direction on the X axis
 public void toggleXDirection() {
     xDirection = xDirection * -1;
 }
 // changes the direction on the Y axis
 public void toggleYDirection() {
     yDirection = yDirection * -1;
 }
 
 public void draw(Canvas canvas) {
  canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
 
 }
 
 
 
 
 
 
 
 

}


