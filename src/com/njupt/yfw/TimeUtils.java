package com.njupt.yfw;

/**
 * 负责倒计时等时间相关的工具类
 * @author 65699
 *
 */
public class TimeUtils {
	
	public static void startCountDownTask(int totalTime,PerformAtInterval performAtInterval) {
		CountingThread countingThread = new CountingThread(totalTime, performAtInterval);
		countingThread.start();
	}
	
	private static class CountingThread extends Thread {
		private int totalTime;
		private PerformAtInterval performAtInterval;
		
		public CountingThread(int t, PerformAtInterval p) {
			totalTime = t;
			performAtInterval = p;
		}
		
		public void run() {
			int leftTime = totalTime;
			while (leftTime > 0) {
				performAtInterval.performWhenTiming(leftTime);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				leftTime--;
				if (leftTime == 0) {
					performAtInterval.performAtEnd();
					break;
				}
			}
		}
	}
	
}
