package mgureken.database.runner;

import mgureken.database.dao.ConnectionManager;

public class Runner {
	public static void main(String[] args) {
		ConnectionManager mng = new ConnectionManager();
		mng.getConnection();
	}	
}
