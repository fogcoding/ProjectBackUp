package bean;

import java.util.ArrayList;

public class Manager {

	private ArrayList<gameInfo> games = new ArrayList<>();
	private ArrayList<userInfo> users = new ArrayList<>();
	
	public Manager() {
	}
	
	public ArrayList<gameInfo> getGames() {
		return games;
	}
	public ArrayList<userInfo> getUsers() {
		return users;
	}
	
	
}
