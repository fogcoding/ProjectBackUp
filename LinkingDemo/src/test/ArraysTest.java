
package test;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import bean.gameInfo;
import bean.userInfo;
import Server.cmdParesing;

public class ArraysTest {

	public static void main(String[] args) {
		byte[] localbuffer = new byte[100];
		byte[] test = new byte[2];
		int start = -1;
		int end = -1;
		int position = 0;
		String l = "300001 1 \15"+"300001 1 \15";
		System.arraycopy(l.getBytes(), 0, test, 0, 2);
		System.out.println(new String(test));
		System.out.println(new String(Arrays.copyOfRange(l.getBytes(), 1, 2)));
		
		

	}


}
