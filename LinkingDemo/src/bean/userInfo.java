package bean;

import java.lang.reflect.Constructor;

public class userInfo {

	private Integer UserID;
	private String UserName;
	private String UserCustomName;
	private Integer SchoolID;
	
	public userInfo() {
	}

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserCustomName() {
		return UserCustomName;
	}

	public void setUserCustomName(String userCustomName) {
		UserCustomName = userCustomName;
	}

	public Integer getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(int schoolID) {
		SchoolID = schoolID;
	}
	
	@Override
	public boolean equals(Object obj) {
		userInfo a = (userInfo) obj;
		if (this.UserID.intValue() == a.getUserID().intValue()) {
			return true;
		}
		return false;
	}
	
}
