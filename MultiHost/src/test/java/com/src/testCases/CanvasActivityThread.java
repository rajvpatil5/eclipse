package com.src.testCases;

import org.apache.commons.compress.archivers.EntryStreamOffsets;

public class CanvasActivityThread implements Runnable {

	@Override
	public void run() {
		TestRunProperties.entryofFirstThread = false;
		while (true) {
			ExecuteTestRun.performActivity(TestRunProperties.elements);
			try {
				Thread.sleep(200000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
