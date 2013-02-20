package controllers;

import models.User;

import org.junit.Before;

import play.mvc.Controller;
import play.mvc.With;

@With( Secure.class )
public class Admin extends Controller{

	@Before
	public static void setConnectedUser() {
		if( Security.isConnected() ) {
			User user = User.find( "byEmail", Security.connected() ).first();
			renderArgs.put( "user", user.fullname );
		}
	}
	
	public static void index() {
		render();
	}
}
