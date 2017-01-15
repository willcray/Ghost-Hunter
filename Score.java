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

public class Score implements Comparable<Score> {
	
	private String scoreDate;
	public int scoreNum;
	
	public Score(String date, int num){
		scoreDate = date;
		scoreNum = num;
	}

	@Override
	public int compareTo(Score other) {
		// TODO Auto-generated method stub
		//return 0 if equal
		//1 if passed greater than this
		//-1 if this greater than passed
		
		return other.scoreNum>scoreNum? 1 : other.scoreNum<scoreNum? -1: 0;
	}
	
	public String getScoreText(){
		return scoreDate+" - "+scoreNum;
	}
	

}

