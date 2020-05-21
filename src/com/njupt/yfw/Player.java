package com.njupt.yfw;

import com.njupt.yfw.GameLogic.Rps;

/**
 * ÕÊº“¿‡
 * @author 65699
 *
 */
public class Player {
	private String name;
	private int score;
	private Rps currentRps;
	private boolean cheated = false;
	private boolean isReady = false;
	
	

	public void setRps(Rps r) {
		currentRps = r;
		if (isReady == true) {
			cheated = true;
		} else {
			isReady = true;
		}
	}
	
	public void win() {
		score++;
		reset();
	}
	
	public void loseOrTie() {
		reset();
	}
	
	private void reset() {
		cheated = false;
		isReady = false;
	}
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Rps getCurrentRps() {
		return currentRps;
	}
	public void setCurrentRps(Rps currentRps) {
		this.currentRps = currentRps;
	}
	public boolean isCheated() {
		return cheated;
	}
	public void setCheated(boolean cheated) {
		this.cheated = cheated;
	}
	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	

}
