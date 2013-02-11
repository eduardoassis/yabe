package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;

import play.mvc.Controller;

public class Posts extends Controller {

	public static void list() {
		List<Post> posts = new ArrayList<Post>();
		posts = Post.findAll();
		renderJSON(posts);
		//List<User> users = User.findAll();
		//renderJSON( users );
	}
}
