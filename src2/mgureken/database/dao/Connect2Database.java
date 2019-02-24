package mgureken.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mgureken.database.model.Points;
import mgureken.database.model.User;

public class Connect2Database {
	Connection conn = null;
	public void getConnection()
	{
		System.out.println("--------connection testing--------------");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("where is your postgreSQL driver?"+" include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver registered!");

		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/KullaniciYonetimi", "postgres", "root");
		} catch (SQLException e) {
			System.out.println("connection failed!");
			e.printStackTrace();
			return;
		}

		if(conn != null)
		{
			System.out.println("You made it, take control your database now!");
		}
		else
		{
			System.out.println("Failed to make connections");
		}
		Points temp = new Points();
		/*temp.setId(1);
		temp.setLevelNo(1);
		temp.setColorNumber(5);
		temp.setColor("BLUE");
		temp.setStartPosition(0);
		temp.setEndPosition(21);
		insertUser(temp);
		temp.setId(2);
		temp.setLevelNo(1);
		temp.setColorNumber(5);
		temp.setColor("RED");
		temp.setStartPosition(2);
		temp.setEndPosition(16);
		insertUser(temp);
		temp.setId(3);
		temp.setLevelNo(1);
		temp.setColorNumber(5);
		temp.setColor("YELLOW");
		temp.setStartPosition(4);
		temp.setEndPosition(18);
		insertUser(temp);*/
		/*temp.setId(4);
		temp.setLevelNo(1);
		temp.setColorNumber(5);
		temp.setColor("GREEN");
		temp.setStartPosition(9);
		temp.setEndPosition(23);
		updateUser(temp);*/
		temp.setId(5);
		temp.setLevelNo(1);
		temp.setColorNumber(5);
		temp.setColor("ORANGE");
		temp.setStartPosition(9);
		temp.setEndPosition(23);
		//insertUser(temp);
		deleteUser(temp);
		getAllUsers();
	}

	public void updateUser(Points temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "update flowfree set startposition="+temp.getStartPosition()+", endposition="+temp.getEndPosition()+" where id="+temp.getId();
			int count = stmt.executeUpdate(sql);
			if(count<=0)
			{
				System.out.println("Islem gerceklesmedi!...");
			}
			else
			{
				System.out.println("Islem gerceklesti.");
			}

		} catch (Exception e) {
			e.printStackTrace();		
		}		
	}

	public void deleteUser(Points temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from flowfree where id="+temp.getId();
			int count = stmt.executeUpdate(sql);
			if(count<=0)
			{
				System.out.println("Islem gerceklesmedi!...");
			}
			else
			{
				System.out.println("Islem gerceklesti.");
			}

		} catch (Exception e) {
			e.printStackTrace();		
		}		
	}
	
	public void insertUser(Points temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into flowfree(id,levelno,colornumber,color,startposition,endposition) values("+temp.getId()+","+
					temp.getLevelNo()+","+temp.getColorNumber()+",'"+temp.getColor() +"',"+temp.getStartPosition()+","+temp.getEndPosition()+")";
			int count = stmt.executeUpdate(sql);
			if(count<=0)
			{
				System.out.println("Islem gerceklesmedi!...");
			}
			else
			{
				System.out.println("Islem gerceklesti.");
			}

		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

	public void getAllUsers()
	{
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select levelno, colornumber, color, startposition, endposition from flowfree";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(sql);

			while(rs.next())
			{
				int levelNo = rs.getInt("levelNo");
				int colorNo = rs.getInt("colorNumber");
				int strPos = rs.getInt("startPosition");
				int endPos = rs.getInt("endPosition");
				String clr = rs.getString("color");
				System.out.println("Level:"+levelNo+" NoOfColor:"+colorNo+" Color:"+clr+" strPos:"+strPos+" endPos:"+endPos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
