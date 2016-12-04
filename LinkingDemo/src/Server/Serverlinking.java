package Server;

import inteface.remoteMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Serverlinking implements remoteMessage {
	private byte[] lock = new byte[0];
	private byte[] localbuffer = new byte[0];
	private static int bufferPosition = 0;
	private Socket socket;
	private boolean runing = false;
	private cmdParesing f = new cmdParesing();

//	public void start() {
//		try {
//			// socket = new Socket("182.92.170.147",8000);
//			socket = new Socket("123.56.152.238", 8000);
//			socket.setKeepAlive(true);
//			InputStream in = socket.getInputStream();
//			FileWriter writer = new FileWriter(new File("C:\\Users\\Andrew\\Desktop\\log.txt"), true);
//
//			byte[] get = new byte[1024];
//			int i = 0;
//			while ((i = in.read(get)) != -1) {
//				if (new String(get).contains("\15")) {
//					localbuffer = byteMerger(localbuffer, get);
//					String temp = new String(get);
//					// writer.write(temp);
//					// System.out.println(temp);
//
//					if (temp.contains("200001 IDN:") && temp.contains(":NDI\15")) {
//						System.out.println("entered!");
//						socket.getOutputStream().write("Xinbo_Weiqi_Server\15".getBytes());
//						localbuffer = new byte[0];
//						byte b = 0;
//						Arrays.fill(get, b);
//						continue;
//					}
//
//					this.bufferPosition += i;
//					handlerData();
//					byte b = 0;
//					Arrays.fill(get, b);
//					continue;
//					
//				}
//					this.localbuffer = byteMerger(localbuffer, get);
//					this.bufferPosition += i;
//
//			}
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	private synchronized void handlerData() {
		int end = 1;
		for (byte c : localbuffer) {
			if (c == '\15') {
				System.out.println("localbuffer size:"+this.localbuffer.length);
				System.out.println("length:" + this.bufferPosition);
				System.out.println("end:" + end);
				break;
			}
			end++;
		}
		byte[] cmd = Arrays.copyOfRange(localbuffer, 0, end);

		tcpMessage(new String(cmd));

		f.paresing(new String(cmd));
		localbuffer = Arrays.copyOfRange(localbuffer, end, this.bufferPosition);
		this.bufferPosition -= end;
		// �����Ȼ������û�д����꣬���������
		if (new String(localbuffer).contains("" + '\15')) {
			handlerData();
		}
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

	}

}
