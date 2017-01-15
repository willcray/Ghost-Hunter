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


public class Laser { 
 private int x;   
 private int y;   
 private float xv = 8;   // velocity value on the X axis
 private float yv = 0;   // velocity value on the Y axis
 
public Laser(int nx, int ny){
	x = nx; 
	y = ny;
}
 
 public void update() {
	     
	         x += this.getXv();
	         y += this.getYv();
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

 
 
}
 
 
 

