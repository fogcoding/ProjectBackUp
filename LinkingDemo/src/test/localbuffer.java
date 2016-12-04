package test;

import Server.cmdParesing;
import inteface.remoteMessage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class localbuffer implements remoteMessage {
	private int start = 0;
	private int end = 0;
	private static int bufferPosition = 0;
	private int crossNumber = 0;
	private byte[] localbuffer = new byte[1024 * 40];
	private FileWriter writer;

	private Socket socket;
	private cmdParesing f = new cmdParesing();

	public static void main(String[] args) {
		localbuffer fLocalbuffer = new localbuffer();
		fLocalbuffer.start();

	}

	public void start() {
		try {
			// socket = new Socket("182.92.170.147",8000);
			socket = new Socket("123.56.152.238", 8000);
			writer = new FileWriter("C:\\Users\\Andrew\\Desktop\\log.txt", true);
			socket.setKeepAlive(true);
			handlerData();
			InputStream in = socket.getInputStream();

			byte[] get = new byte[1024];
			int i = 0;
			while (true) {
				while ((i = in.read(get)) != -1) {
					if (new String(get).contains("200001 IDN:") && new String(get).contains(":NDI\15")) {
						socket.getOutputStream().write("Xinbo_Weiqi_Server\15".getBytes());
						byte b = 0;
						Arrays.fill(localbuffer, b);
						Arrays.fill(get, b);
						continue;
					}
					if (bufferPosition + i < 1024 * 40) {
						System.arraycopy(get, 0, localbuffer, bufferPosition, i);
						bufferPosition += i;
						System.out.println("load buffer!!!!");
					} else if (bufferPosition + i == 1024 * 40) {
						System.arraycopy(get, 0, localbuffer, bufferPosition, i);
						bufferPosition = 0;
					} else {
						int diff = 1024 * 40 - bufferPosition;
						int newPosition = i - diff;
						System.arraycopy(get, 0, localbuffer, bufferPosition, diff);
						bufferPosition = 0;
						System.arraycopy(get, diff, localbuffer, bufferPosition, newPosition);
						bufferPosition += newPosition;
						// 超过长度的次数
						crossNumber++;
					}
					// System.out.println(new String(localbuffer));

				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void handlerData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					while (bufferPosition < 1) {
						try {
							Thread.sleep(10);
							System.out.println("waits~~~");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (end >= localbuffer.length) {
						end = 0;
						System.out.println("before:" + crossNumber);
						crossNumber--;
						System.out.println("after:" + crossNumber);
					}
					if (localbuffer[end] == '\15') {
						if (end < start) {
							byte[] bs1 = Arrays.copyOfRange(localbuffer, start, localbuffer.length);
							byte[] bs2 = Arrays.copyOfRange(localbuffer, 0, end + 1);
							byte[] cmd = byteMerger(bs1, bs2);
							System.out.print(new String(cmd));
							 f.paresing(new String(cmd));
							start = end + 1;
							end = start;
						}

						byte[] cmd = Arrays.copyOfRange(localbuffer, start, end + 1);
						System.out.print(new String(cmd));
						 f.paresing(new String(cmd));

						start = end + 1;
						end = start;
					}
					end++;

				}
			}
		}).start();

	}

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public byte[] bufferClear(byte[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}

	@Override
	public void tcpMessage(String meassage) {
		// try {
		// System.out.println("log:"+meassage);
		// System.out.println("log:"+this.webSocketTest);
		//// this.webSocketTest.getSession().getBasicRemote().sendText(meassage);
		// System.out.println("after log!");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

}
