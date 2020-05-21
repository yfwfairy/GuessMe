package com.njupt.yfw;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 负责处理游戏中逻辑问题
 * @author 65699
 *
 */
public class GameLogic {
	public static enum GameType{
		P2E(0),P2P(1);
		private int value;
		GameType(int i) {
			this.value = i;
		}
	};
	
	public static enum GameFormat{
		WIN3OF5(1),WIN2OF3(0);
		private int value;
		GameFormat(int i) {
			this.value = i;
		}
	};
	
	public static enum Rps {
		ROCK(0),PAPER(1),SCISSORS(2);
		
		private int value;
		private Rps(int i) {
			value = i;
		}
	}
	
	public static enum Result {
		WIN(0),TIE(1),LOSE(2),CHEAT(3),NOREADY(4),OTHERNOREADY(5);
		private int value;
		private Result(int i) {
			value = i;
		}
	}
	
	/**
	 * 1、返回两个Player的结果
	 * 2、更新两个Player
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static List<Result> judgePlayer(Player p1, Player p2) {
		List<Result> list = new ArrayList<>();
		if (!p1.isReady() && !p2.isReady()) {
			list.add(Result.NOREADY);
			list.add(Result.NOREADY);
			p1.loseOrTie();
			p2.loseOrTie();
			return list;
		}
		if (!p1.isReady()) {
			list.add(Result.NOREADY);
			list.add(Result.OTHERNOREADY);
			p1.loseOrTie();
			p2.loseOrTie();
			return list;
		}
		if (!p2.isReady()) {
			list.add(Result.OTHERNOREADY);
			list.add(Result.NOREADY);
			p1.loseOrTie();
			p2.loseOrTie();
			return list;
		}
		if (p1.isCheated() && p2.isCheated()) {
			list.add(Result.CHEAT);
			list.add(Result.CHEAT);
			p1.loseOrTie();
			p2.loseOrTie();
			return list;
		}
		if (p1.isCheated()) {
			list.add(Result.CHEAT);
			list.add(Result.WIN);
			p1.loseOrTie();
			p2.win();
			return list;
		}
		if (p2.isCheated()) {
			list.add(Result.WIN);
			list.add(Result.CHEAT);
			p1.win();
			p2.loseOrTie();
			return list;
		}
		Result result = judge(p1.getCurrentRps(), p2.getCurrentRps());
		switch (result) {
		case WIN:
			list.add(Result.WIN);
			list.add(Result.LOSE);
			p1.win();
			p2.loseOrTie();
			return list;
		case TIE:
			list.add(Result.TIE);
			list.add(Result.TIE);
			p1.loseOrTie();
			p2.loseOrTie();
			return list;
		case LOSE:
			list.add(Result.LOSE);
			list.add(Result.WIN);
			p1.loseOrTie();
			p2.win();
			return list;
		default:
			break;
		}
		
		return list;
	}
	
	private static Result judge(Rps p1, Rps p2) {
		switch (p1) {
		case ROCK:
			switch (p2) {
			case ROCK:
				return Result.TIE;
			case PAPER:
				return Result.LOSE;
			case SCISSORS:
				return Result.WIN;
			default:
				break;
			}
			break;
		case PAPER:
			switch (p2) {
			case ROCK:
				return Result.WIN;
			case PAPER:
				return Result.TIE;
			case SCISSORS:
				return Result.LOSE;
			default:
				break;
			}
			break;
		case SCISSORS:
			switch (p2) {
			case ROCK:
				return Result.LOSE;
			case PAPER:
				return Result.WIN;
			case SCISSORS:
				return Result.TIE;
			default:
				break;
			}
			break;
		default:
			break;
		}
		System.out.println("发生了意外，没有得到结果");
		return Result.TIE;
	}

}
