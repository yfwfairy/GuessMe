package com.njupt.yfw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.njupt.yfw.GameLogic.Rps;

/**
 * 人机对战时的AI
 * @author 65699
 *
 */
public class AIGamer extends Player {
	
	private List<Rps> playerRps;

	public AIGamer(String n) {
		super(n);
		playerRps = new ArrayList<>();
	}
	
	public void addPlayerRps(Rps rps) {
		playerRps.add(rps);
	}
	
	public void autoPlay() {
		Rps rps = analyzeAndDeside();
		setRps(rps);
	}
	
	public Rps analyzeAndDeside() {
		//根据玩家前面出拳数据，预测下一次，待补充
		
		int i = (int) (Math.random() * 3);
		switch (i) {
		case 0:
			return Rps.ROCK;
		case 1:
			return Rps.PAPER;
		case 2:
			return Rps.SCISSORS;
		default:
			return Rps.ROCK;
		}
	}

}
