package com.njupt.yfw;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {

	JLabel p1NameLabel;
	JLabel p2NameLabel;
	JLabel p1ReadyLabel;
	JLabel p2ReadyLabel;
	JButton playButton;
	ActionListener actionListener;
	Icon readyIcon;
	Icon unReadyIcon;
	Icon playIcon;
	Icon playingIcon;

	public BottomPanel(String p1Name, String p2Name, ActionListener actionListener) {
		initPanel();
		setP1Name(p1Name);
		setP2Name(p2Name);
		playButton.addActionListener(actionListener);
	}

	public BottomPanel(ActionListener actionListener) {
		initPanel();
		playButton.addActionListener(actionListener);
	}

	private void initPanel() {
		p1NameLabel = new JLabel();
		p2NameLabel = new JLabel();
		readyIcon = new ImageIcon("resource/ready.png");
		unReadyIcon = new ImageIcon("resource/unReady.png");
		playIcon = new ImageIcon("resource/play.png");
		playingIcon = new ImageIcon("resource/playing.png");
		p1ReadyLabel = new JLabel();
		p1ReadyLabel.setIcon(unReadyIcon);
		p2ReadyLabel = new JLabel();
		p2ReadyLabel.setIcon(unReadyIcon);

		playButton = new JButton();
		playButton.setIcon(playIcon);

		p1NameLabel.setSize(100, 50);
		p2NameLabel.setSize(100, 50);
		p1ReadyLabel.setSize(30, 30);
		p2ReadyLabel.setSize(30, 30);
		playButton.setSize(50, 50);

		setLayout(new FlowLayout(1, 5, 5));
		add(p1NameLabel);
		add(p1ReadyLabel);
		add(playButton);
		add(p2ReadyLabel);
		add(p2NameLabel);
		setVisible(true);
	}

	public void setP1Name(String name) {
		p1NameLabel.setText(name);
	}

	public void setP2Name(String name) {
		p2NameLabel.setText(name);
	}

	public void setBothReady(boolean p1, boolean p2) {
		p1ReadyLabel.setIcon(p1 ? readyIcon : unReadyIcon);
		p2ReadyLabel.setIcon(p2 ? readyIcon : unReadyIcon);
		repaint();
	}

	public void setP1Ready(boolean p1) {
		p1ReadyLabel.setIcon(p1 ? readyIcon : unReadyIcon);
		repaint();
	}

	public void setP2Ready(boolean p2) {
		p2ReadyLabel.setIcon(p2 ? readyIcon : unReadyIcon);
		repaint();
	}

	public void update(Player p1, Player p2) {
		setBothReady(p1.isReady(), p2.isReady());
		setP1Name(p1.getName());
		setP2Name(p2.getName());
		repaint();
	}

	public void switchPlayState(boolean playing) {
		if (playing) {
			playButton.setIcon(playingIcon);
			playButton.setEnabled(false);
		} else {
			playButton.setIcon(playIcon);
			playButton.setEnabled(true);
		}
	}

	public void play() {
		playButton.doClick();
	}

}
