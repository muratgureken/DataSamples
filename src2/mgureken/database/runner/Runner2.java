package mgureken.database.runner;

import mgureken.database.dao.Connect2Database;

public class Runner2 {

	public static void main(String[] args) {
		Connect2Database mng = new Connect2Database();
		mng.getConnection();
	}

}
