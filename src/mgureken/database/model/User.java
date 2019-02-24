package mgureken.database.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -8383264756311745751L;
	/**
	 * 
	 */
	private String username;
	private String password;
	private Integer id;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
