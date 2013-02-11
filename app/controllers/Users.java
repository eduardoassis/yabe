package controllers;

import java.util.List;

import models.User;

import play.mvc.Controller;

public class Users extends Controller {

	public static void list() {
		List<User> users = User.findAll();
		renderJSON( users );
	}
}
