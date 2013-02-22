package controllers;


import java.util.List;

import models.User;

import play.mvc.With;

@Check( "admin" )
@With( Secure.class )
public class Users extends CRUD {
	
	public static void list() {
		List<User> users = User.findAll();
		renderJSON( users );
	}
}
