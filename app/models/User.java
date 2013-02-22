package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import controllers.Check;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {

	@Email
	@Required
	public String email;
	
	@Required
	public String password;
	
	public String fullname;
	
	public boolean isAdmin;
	
	@OneToMany( mappedBy = "author" )
	public List<Post> posts;
	
	public User( String email, String password, String fullName ) {
		this.email = email;
		this.password = password;
		this.fullname = fullName;
		this.posts = new ArrayList<Post>();
	}
	
	public static User connect( String email, String password ) {
		return find( "byEmailAndPassword", email, password ).first();
	}
}
