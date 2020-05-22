package com.njupt.yfw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.njupt.yfw.GameLogic.Rps;

/**
 * �˻���սʱ��AI
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
		//�������ǰ���ȭ���ݣ�Ԥ����һ�Σ�������
		
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
