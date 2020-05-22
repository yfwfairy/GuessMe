package com.njupt.yfw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.njupt.yfw.GameLogic.GameType;



/**
 * 首页选择模式 人人/人机
 * @author 65699
 *
 */
public class HomePage extends JFrame{
	private JLabel titleLabel;
	private JButton p2eButton;
	private JButton p2pButton;
	
	
	
	public HomePage() {
		initFrame();
	}
	
	public void initFrame() {
		setLayout(new GridLayout(3,1));
		titleLabel = new JLabel("Guess My RPS");
		titleLabel.setFont(new Font("楷体", Font.ITALIC, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.RED);
		p2pButton = new JButton("双人模式");
		p2pButton.setFont(new Font("楷体", Font.PLAIN, 25));
		p2eButton = new JButton("人机对弈");
		p2eButton.setFont(new Font("楷体", Font.PLAIN, 25));
		
		p2pButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InputPlayerNameFrame(GameType.P2P);
				dispose();
			}
		});
		
		p2eButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InputPlayerNameFrame(GameType.P2E);
				dispose();
			}
		});
		
		
		
		add(titleLabel);
		add(p2eButton);
		add(p2pButton);
		
		setTitle("猜拳游戏");
		setSize(500,500);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args)
	{
		HomePage homePage = new HomePage();
	}
}
