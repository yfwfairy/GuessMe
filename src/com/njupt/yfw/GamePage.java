package com.njupt.yfw;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.njupt.yfw.GameLogic.GameType;
import com.njupt.yfw.GameLogic.Result;
import com.njupt.yfw.GameLogic.Rps;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.security.Init;

/**
 * 游戏主体界面
 * 
 * @author 65699
 *
 */
public class GamePage extends JFrame implements KeyListener {

	JLabel topScoreLabel;
	JLabel p1ResultLabel;
	JLabel p2ResultLabel;
	JLabel countingLabel;
	BottomPanel bottomPanel;

	Player p1;
	Player p2;
	GameType gameType;

	boolean acceptKeyEvent = false;

	static final String p1Tips = "z = ROCK  x = PAPER  c = SCISSORS";
	static final String p2Tips = "1 = ROCK  2 = PAPER  3 = SCISSORS";
	static final String aiTips = "我是一个机器人哈哈哈";

	public GamePage(String p1Name) {
		initFrame();
		gameType = GameType.P2E;
		p1 = new Player(p1Name);
		p2 = new AIGamer("机器人小玮");
		bottomPanel.setP1Name(p1.getName());
		bottomPanel.setP2Name(p2.getName());
		checkAndReset();
		p1ResultLabel.setText(p1Tips);
		if (gameType == GameType.P2E) {
			p2ResultLabel.setText(aiTips);
		} else {
			p2ResultLabel.setText(p2Tips);
		}
	}

	public GamePage(String p1Name, String p2Name) {
		initFrame();
		gameType = GameType.P2P;
		p1 = new Player(p1Name);
		p2 = new Player(p2Name);
		bottomPanel.setP1Name(p1.getName());
		bottomPanel.setP2Name(p2.getName());
		checkAndReset();
		p1ResultLabel.setText(p1Tips);
		if (gameType == GameType.P2E) {
			p2ResultLabel.setText(aiTips);
		} else {
			p2ResultLabel.setText(p2Tips);
		}
	}

	private void initFrame() {
		topScoreLabel = new JLabel("0:0");
		topScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(topScoreLabel, BorderLayout.NORTH);

		p1ResultLabel = new JLabel("P1的结果");
		p2ResultLabel = new JLabel("P2的结果");
		add(p1ResultLabel, BorderLayout.WEST);
		add(p2ResultLabel, BorderLayout.EAST);

		countingLabel = new JLabel("游戏规则");
		countingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(countingLabel, BorderLayout.CENTER);

		bottomPanel = new BottomPanel(new PlayAcitonListener());
		add(bottomPanel, BorderLayout.SOUTH);

		setVisible(true);
		setSize(500, 300);
		setLocation(500, 200);
		setFocusable(true);
		addKeyListener(this);

	}

	private class PlayAcitonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			acceptKeyEvent = true;
			bottomPanel.switchPlayState(true);
			if (gameType == GameType.P2E) {
				p2.autoPlay();
				bottomPanel.setP2Ready(true);
			}
			TimeUtils.startCountDownTask(3, new PerformAtInterval() {

				@Override
				public void performWhenTiming(int leftTime) {
					// TODO Auto-generated method stub
					countingLabel.setText(String.valueOf(leftTime));
					repaint();
				}

				@Override
				public void performAtEnd() {
					List<Result> resList = GameLogic.judgePlayer(p1, p2);
					Result p1Result = resList.get(0);
					Result p2Result = resList.get(1);
					Rps p1Rps = p1.getCurrentRps();
					Rps p2Rps = p2.getCurrentRps();
					setResultLabel(p1ResultLabel, p1Result, p1Rps);
					setResultLabel(p2ResultLabel, p2Result, p2Rps);
					countingLabel.setText("time out");
					bottomPanel.switchPlayState(false);
					acceptKeyEvent = false;
					checkAndReset();
				}
			});
		}

	}

	public void checkAndReset() {
		p1.reset();
		p2.reset();
		topScoreLabel.setText(p1.getScore() + ":" + p2.getScore());
		bottomPanel.switchPlayState(false);
		bottomPanel.update(p1, p2);
	}

	public void setResultLabel(JLabel label, Result result, Rps rps) {
		switch (result) {
		case WIN:
			label.setText(rps + ",赢啦!");
			break;
		case TIE:
			label.setText(rps + ",平局");
			break;
		case LOSE:
			label.setText(rps + ",输啦");
			break;
		case CHEAT:
			label.setText("作弊！对方得分！");
			break;
		case NOREADY:
			label.setText("怎么没出呢？");
			break;
		case OTHERNOREADY:
			label.setText("对面小笨蛋没出");
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (acceptKeyEvent) {
			if (c == 'z') {
				if (p1 != null) {
					p1.setRps(Rps.ROCK);
					bottomPanel.setP1Ready(true);
				}
			}
			if (c == 'x') {
				if (p1 != null) {
					p1.setRps(Rps.PAPER);
					bottomPanel.setP1Ready(true);
				}
			}
			if (c == 'c') {
				if (p1 != null) {
					p1.setRps(Rps.SCISSORS);
					bottomPanel.setP1Ready(true);
				}
			}

			if (gameType == GameType.P2P) {
				if (c == '1') {
					if (p2 != null) {
						p2.setRps(Rps.ROCK);
						bottomPanel.setP2Ready(true);
					}
				}
				if (c == '2') {
					if (p2 != null) {
						p2.setRps(Rps.PAPER);
						bottomPanel.setP2Ready(true);
					}
				}
				if (c == '3') {
					if (p2 != null) {
						p2.setRps(Rps.SCISSORS);
						bottomPanel.setP2Ready(true);
					}
				}
			}
		} else {
			if (c == ' ') {
				bottomPanel.play();
			}
		}
	}

}
