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

public class Explosion {
    public static final int STATE_ALIVE     = 0;    // at least 1 particle is alive
    public static final int STATE_DEAD      = 1;    // all particles are dead
    private Particle[] particles;           // particles in the explosion
    private int x, y;                       // the explosion's origin
    private int size;                       // number of particles
    private int state;                      // whether it's still active or not
    
    public Explosion(int x, int y) {
       	    this.state = STATE_ALIVE;
    	    this.particles = new Particle[50];
    	    for (int i = 0; i < this.particles.length; i++) {
    	        Particle p = new Particle(x, y);
    	        this.particles[i] = p;
    	    }
    	    this.size = 30;
    	}
    public void update(){
    	for(int x=0;x<particles.length; x++){
    		particles[x].update();
    	}
    	
    }
    public void draw(Canvas canvas){
    	for(int x=0;x<particles.length; x++){
    		particles[x].draw(canvas);
    	}
    	
    }
}

