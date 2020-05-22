package com.njupt.yfw;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.njupt.yfw.GameLogic.GameType;

public class InputPlayerNameFrame extends JFrame{
	
	JLabel p1Label;
	JLabel p2Label;
	JTextField p1NameTxf;
	JTextField p2NameTxf;
	JButton confirmButton;
	JButton backButton;
	GameType gameType;
	


	public InputPlayerNameFrame(GameType type) {
		
		confirmButton = new JButton("确定");
		backButton = new JButton("返回");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomePage();
				dispose();
			}
		});
		
		if (type == GameType.P2E) {
			setLayout(new GridLayout(2,2));
			p1Label = new JLabel("您的尊姓大名:");
			p1NameTxf = new JTextField();
			confirmButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String p1Name;
					if (isEmpty(p1NameTxf)) {
						p1Name = "无名大侠";
					} else {
						p1Name = p1NameTxf.getText();
					}
					GamePage gamePage = new GamePage(p1Name);
					dispose();
				}
			});
			add(p1Label);
			add(p1NameTxf);
			add(confirmButton);
			add(backButton);
			
		} else {
			setLayout(new GridLayout(3,2));
			p1Label = new JLabel("玩家1的尊姓大名:");
			p1NameTxf = new JTextField();
			p2Label = new JLabel("玩家2的尊姓大名:");
			p2NameTxf = new JTextField();
			confirmButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String p1Name;
					String p2Name;
					if (isEmpty(p1NameTxf)) {
						p1Name = "数字大侠小1";
					} else {
						p1Name = p1NameTxf.getText();
					}
					if (isEmpty(p2NameTxf)) {
						p2Name = "数字大侠小2";
					} else {
						p2Name = p2NameTxf.getText();
					}
					GamePage gamePage = new GamePage(p1Name,p2Name);
					dispose();
				}
			});
			
			add(p1Label);
			add(p1NameTxf);
			add(p2Label);
			add(p2NameTxf);
			add(confirmButton);
			add(backButton);
		}
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		setLocation(500, 200);
	}
	
	private static boolean isEmpty(JTextField t) {
		if ( t== null || t.getText() == null || t.getText().length() == 0) {
			return true;
		}
		return false;
	}
}
