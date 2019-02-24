package mgureken.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mgureken.database.model.User;

public class ConnectionManager {
	Connection conn = null;
	public void getConnection()
	{
		System.out.println("---connection testing--------------");
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
		User temp = new User();
		/*temp.setId(8);
		temp.setUsername("DONALD");
		temp.setPassword("456");*/

		temp.setId(1);
		temp.setUsername("DEGISTI");
		temp.setPassword("456");
		
		deleteUser(temp);
		//updateUser(temp);
		//insertUser(temp);
		getAllUsers();
	}

	public void updateUser(User temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "update kullanici set usrname='"+temp.getUsername()+"' where id="+temp.getId();
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

	public void deleteUser(User temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from kullanici where id="+temp.getId();
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
	
	public void insertUser(User temp)
	{
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into kullanici(id,usrname,password) values("+temp.getId()+",'"+temp.getUsername()+"','"+temp.getPassword()+"')";
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
			String sql = "select usrname, password from kullanici ";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(sql);

			while(rs.next())
			{
				String kadi = rs.getString("usrname");
				String sifre = rs.getString("password");
				System.out.println("Kullanici:"+kadi+" sifre:"+sifre);
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
