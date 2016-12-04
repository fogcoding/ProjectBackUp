package test;

public class IntegerTest {

	public static void main(String[] args) {
		Integer integer1 = 527046;
		Integer integer2 = 527046;
		Integer integer3 = 0x7fffffff;
		System.out.println(integer3);
		byte[] bits = new byte[8];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = (byte) (integer2 & 0xff);
			integer2 = integer2>>8;
		}
		for(byte b : bits){
			System.out.println(b);
		}
		
		
		
//		int int1 = integer1.intValue();
//		int int2 = integer2.intValue();
//		System.out.println(int1);
//		System.out.println(int2);
//		System.out.println(int2==int1);
//		
//		System.out.println(integer1==integer2);
		
	}

}
