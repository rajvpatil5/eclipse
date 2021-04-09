package com.src.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.src.testCases.ExecuteTestRun;

public class SSHConnect {

	static ColoredPrinter cp;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void connectToVM(String IP) {

		String host = IP;
		String user = "spring";
		String password = "$pring12345678";
		String command1 = "/home/spring/UbuntuSlave/cmd.sh";

		try {

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			// System.out.println("Connected");

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command1);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];

			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				// System.out.print(new String(tmp, 0, i));
			}
			if (channel.isClosed()) {
				// System.out.println("exit-status: "+channel.getExitStatus());
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ee) {
			}

			channel.disconnect();
			session.disconnect();
			// System.out.println("DONE");
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public static void connectToVM() {

		String cmd = "";
		String filepath = "";
		String password = "";
		try {
			ExecuteTestRun.setCommandPrinter();
			System.out.println("\nEnter Ip address");
			ExecuteTestRun.setInputPrinter();
			String host = br.readLine();
			ExecuteTestRun.setCommandPrinter();
			System.out.println("\nEnter Port number");
			ExecuteTestRun.setInputPrinter();
			String port = br.readLine();
			ExecuteTestRun.setCommandPrinter();
			System.out.println("\nEnter User Name");
			ExecuteTestRun.setInputPrinter();
			String user = br.readLine();
			ExecuteTestRun.setCommandPrinter();
			System.out.println("\nConnect using pem y/n");
			ExecuteTestRun.setInputPrinter();
			cmd = br.readLine();
			if (cmd.equalsIgnoreCase("y") == true) {
				ExecuteTestRun.setCommandPrinter();
				System.out.println("\nEnter pem file path");
				ExecuteTestRun.setInputPrinter();
				filepath = br.readLine();
			} else {
				ExecuteTestRun.setCommandPrinter();
				System.out.println("\nEnter Password");
				ExecuteTestRun.setInputPrinter();
				password = br.readLine();
			}
			boolean exicutemore = false;

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();

			Session session = null;
			ExecuteTestRun.setResponsePrinter();
			System.out.println("\nConnecting...");
			if (filepath.trim().isEmpty() == true) {
				session = jsch.getSession(user, host, Integer.parseInt(port));
				session.setPassword(password);
			} else {
				jsch.addIdentity(filepath);
				session = jsch.getSession(user, host, Integer.parseInt(port));
			}
			session.setConfig(config);
			session.connect();
			System.out.println("\nConnected");

			do {
				Channel channel = session.openChannel("exec");
				ExecuteTestRun.setCommandPrinter();
				System.out.println("\nEnter command to run");
				ExecuteTestRun.setInputPrinter();
				String command1 = br.readLine();
				ExecuteTestRun.setResponsePrinter();
				System.out.println("\nExecuting command...");
				try {

					((ChannelExec) channel).setCommand(command1);
					channel.setInputStream(null);
					((ChannelExec) channel).setErrStream(System.err);

					InputStream in = channel.getInputStream();
					channel.connect();
					byte[] tmp = new byte[1024];
					Thread.sleep(3000);
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						if (i < 0)
							break;
						ExecuteTestRun.setBoxPrinter();
						System.out.print(new String(tmp, 0, i));
					}
					if (channel.isClosed()) {
						// System.out.println("exit-status:
						// "+channel.getExitStatus());
					}
					try {
						Thread.sleep(1000);
					} catch (Exception ee) {
					}
					channel.disconnect();
				} catch (Exception e) {
					// TODO: handle exception
					ExecuteTestRun.setErrorBoxPrinter();
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
	
				ExecuteTestRun.setCommandPrinter();
				System.out.println("\nMore command to execute Y/N");
				ExecuteTestRun.setInputPrinter();
				command1 = br.readLine();
				exicutemore = false;
				if (command1.equalsIgnoreCase("y") == true) {
					exicutemore = true;
				}

			} while (exicutemore);

			session.disconnect();

			// System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
