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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



public class Particle {

    public static final int STATE_ALIVE = 0;    // particle is alive
    public static final int STATE_DEAD = 1;     // particle is dead
    public static final int DEFAULT_LIFETIME    = 200;  // play with this
    public static final int MAX_DIMENSION       = 5;    // the maximum width or height
    public static final int MAX_SPEED           = 10;   // maximum speed (per update)
    private int state;          // particle is alive or dead
    private float width;        // width of the particle
    private float height;       // height of the particle
    private float x, y;         // horizontal and vertical position
    private double xv, yv;      // vertical and horizontal velocity
    private int age;            // current age of the particle
    private int lifetime;       // particle dies when it reaches this value
    private int color;          // the color of the particle
    private Paint paint;        // internal use to avoid instantiation

    public Particle(int x, int y) {
    	    this.x = x;
    	    this.y = y;
    	    this.state = Particle.STATE_ALIVE;
    	    if(Math.random() <= 0.5){
    	    	this.xv = (Math.random()*MAX_SPEED);
    	    }else{
    	    	this.xv = -(Math.random()*MAX_SPEED);
    	    }
    	    if(Math.random() <= 0.5){
    	    	 this.yv = (Math.random()*MAX_SPEED);
    	    }else{
    	    	this.yv = -(Math.random()*MAX_SPEED);
    	    }
    	    int W = (int)(Math.random()*20);
    	    this.width = W;
    	    this.height = this.width;
    	    this.lifetime = DEFAULT_LIFETIME;
    	    this.age = 0;
    	    
    	   
    	    // smoothing out the diagonal speed
    	    int R = (int)(Math.random()*256);
    	    int G = (int)(Math.random()*256);
    	    int B= (int)(Math.random()*256);
    	    this.color = Color.rgb(R,G,B);
    	    this.paint = new Paint(this.color);
    	}
    
    public void update() {
    	    if (this.state != STATE_DEAD) {
    	        this.x += this.xv;
    	        this.y += this.yv;
    	        // extract alpha
    	        int a = this.color >>> 24;
    	        a -= 2; // fade by 2
    	        if (a <= 0) { // if reached transparency kill the particle
    	            this.state = STATE_DEAD;
    	        } else {
    	            this.color = (this.color & 0x00ffffff) + (a << 24);       // set the new alpha
    	            this.paint.setAlpha(a);
    	            this.age++; // increase the age of the particle
    	        }
    	        if (this.age >= this.lifetime) { // reached the end if its life
    	            this.state = STATE_DEAD;
    	        }
    	    }
    	}
    public void draw(Canvas canvas) {
    	    paint.setColor(this.color);
    	    canvas.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
    	}

}

