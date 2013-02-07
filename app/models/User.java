package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String email;
	public String password;
	public String fullName;
	public boolean isAdmin;
	
	public User( String email, String password, String fullName ) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}
	
	public static User connect( String email, String password ) {
		return find( "byEmailAndPassword", email, password ).first();
	}
}
