package Server;


import bean.Manager;
import bean.gameInfo;
import bean.userInfo;

public class cmdParesing {

	private static Manager manager = new Manager();
	
    public cmdParesing() {
	}
	
    public void paresing(String s){
    	
    	System.out.println("here:"+s);
    	
		try {
			if(s.substring(0, 5).equals("30000") && "12345678".contains(s.substring(5,6)) && s.substring(s.length()-1).equals("\15")){
				
				switch (s.substring(5,6)) {
				case "1":
					One(s);
					break;
				case "2":
					Two(s);
					break;
				case "3":
					Three(s);
					break;
				case "4":
					Four(s);
					break;
				case "5":
//					Five(s);
					break;
				case "6":
					Six(s);
					break;
				case "7":
					Seven(s);
					break;
				case "8":
					Eight(s);
					break;
				default:
					break;
				}
			}	
		} catch (Exception e) {
			System.out.println("some error happened:"+e.toString());
		}	
	
	}

	private static void Eight(String s) {
		System.out.println("锟秸碉拷锟斤拷锟斤拷锟叫碉拷锟藉，锟斤拷锟叫碉拷锟斤拷锟斤拷");
		System.out.println(s);
		
	}

	private static void Seven(String s) {
		System.out.println("锟秸碉拷一锟斤拷锟斤拷");
		System.out.println(s);
		
		
	}

	private static void Six(String s) {
		System.out.println("锟皆撅拷状态锟侥憋拷锟斤拷");
		System.out.println(s);
		
		
	}

	private static void Five(String s) {
		System.out.println("锟皆局斤拷锟斤拷锟斤拷");
		System.out.println(s);
		paresingGameEnd(s);
		
	}

	private static void Four(String s) {
		System.out.println("锟皆撅拷锟斤拷息");
		//锟斤拷锟揭伙拷侄跃锟斤拷锟较�
		System.out.println("锟斤拷前锟皆撅拷锟斤拷锟斤拷为锟斤拷"+manager.getGames().size());
		manager.getGames().add(paresingGameInfo(s));
		
	}

	private static void Three(String s) {
		System.out.println("锟斤拷锟斤拷锟矫伙拷锟诫开锟斤拷");
		System.out.println(s);
		paresingUserLeft(s);
		
	}

	private static void Two(String s) {
		System.out.println("锟斤拷取锟矫伙拷锟斤拷息");
		System.out.println(s);
		
		//锟斤拷取一锟斤拷锟矫伙拷锟斤拷息
		System.out.println("锟斤拷前锟矫伙拷锟斤拷锟斤拷为锟斤拷"+manager.getUsers().size());
		manager.getUsers().add(paresingUerInfo(s));

	}


	private static void One(String s) {
		System.out.println("锟斤拷锟接成癸拷");
		System.out.println(s);
		
		
	}
	
	
	public Manager getManager() {
		return manager;
	}
	
	private static gameInfo paresingGameInfo(String s) {
		gameInfo gameInfo = new gameInfo() {
			@Override
			public String toString() {
				return getGameID() + "\n" + getW_UserName() + "\n" + getW_UserID() + "\n" + getB_UserName()+"\n"+getB_UserID()+"\n"+getRangzi()+"\n"+getTiemu()+"\n"+getStatus()+"\n"+getFreeTime()+"\n"+getGame_Type()+"\n"+getBetIndex();
			}
		};
	
		int[] position = new int[11];
		for (int i = 1, j = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\40') {
				position[j++] = i;
			}
		}

		// 锟皆局憋拷锟�
		gameInfo.setGameID(Integer.parseInt(s.substring(position[0]+1, position[1]), 10));;
		// 锟阶凤拷锟矫伙拷锟斤拷
		gameInfo.setW_UserName(s.substring(position[1]+1, position[2]));
		// 锟阶凤拷ID
		gameInfo.setW_UserID(Integer.parseInt(s.substring(position[2]+1, position[3]), 10));
		// 锟节凤拷锟矫伙拷锟斤拷
		gameInfo.setB_UserName(s.substring(position[3]+1, position[4]));
		//锟节凤拷ID
		gameInfo.setB_UserID(Integer.parseUnsignedInt(s.substring(position[4]+1, position[5]), 10));
		//锟斤拷锟斤拷锟斤拷
		gameInfo.setRangzi(Integer.parseUnsignedInt(s.substring(position[5]+1, position[6]), 10));
		//锟斤拷目
		gameInfo.setTiemu(s.substring(position[6]+1, position[7]));
		//状态
		gameInfo.setStatus(Integer.parseUnsignedInt(s.substring(position[7]+1, position[8]), 10));
		//锟斤拷锟斤拷时锟斤拷
		gameInfo.setFreeTime(Integer.parseUnsignedInt(s.substring(position[8]+1, position[9]), 10));
		//锟斤拷锟斤拷锟斤拷锟斤拷
		gameInfo.setGame_Type(Integer.parseUnsignedInt(s.substring(position[9]+1, position[10]), 10));
		//betIndex
//		gameInfo.setBetIndex(Integer.parseUnsignedInt(s.substring(position[10]+1, s.length()-1), 10));
//		System.out.println(gameInfo.toString());
		return gameInfo;
	}
	
	private static userInfo paresingUerInfo(String s) {
		userInfo userInfo = new userInfo();
		int[] position = new int[4];
		for(int i=0,j=0; i < s.length(); i++){
			if(s.charAt(i) == '\40'){
				position[j++] = i;
			}
		}

		for(int i=1,j=0; i < s.length(); i++){
			if(s.charAt(i) == '\40'){
				position[j++] = i;
			}
		}
		//ID
		userInfo.setUserID(Integer.parseInt(s.substring(position[0]+1 , position[1]).trim(), 10));
		//锟矫伙拷锟斤拷
		userInfo.setUserName(s.substring(position[1]+1, position[2]));
		//锟角筹拷
		userInfo.setUserCustomName(s.substring(position[2]+1, position[3]));
		//学校ID
		userInfo.setSchoolID(Integer.parseInt(s.substring(position[3]+1, s.length()-1).trim(), 10));
		return userInfo;
	}
	
	private static void paresingUserLeft(String s) {
		Integer userID = -1;
		if (s.contains("300003 ") && s.contains("\15")) {
			userID = Integer.parseInt(s.substring(7, s.length()-1));
			System.out.println("delete methods,and ID:"+userID);
		}
		for (userInfo u:manager.getUsers()) {
			if (u.getUserID().intValue() == userID.intValue()) {
				System.out.println("iterator!");
				manager.getUsers().remove(u);
				System.out.println("删锟斤拷ID为锟斤拷"+userID+"锟斤拷锟矫伙拷锟斤拷锟斤拷前锟矫伙拷锟斤拷锟斤拷为锟斤拷"+manager.getUsers().size());
				break;
			}
		}
	}
	
	private static void paresingGameEnd(String s) {
		Integer gameID = -1;
		String res = "";
		if (s.contains("300005 ") && s.contains("\15")) {
			char[] toChar = s.toCharArray();
			int[] position = new int[2];
			for(int i=0,j=0; i< toChar.length ; i++){
				if (toChar[i] == '\40') {
					position[j++] = i;
				}
			}
			gameID = Integer.parseInt(s.substring(position[0]+1, position[1]));
			res = s.substring(position[1], s.length()-1);
			
			//TODO 通知锟节观匡拷锟斤拷锟矫伙拷锟斤拷纸锟斤拷锟�
		}
	}

}
