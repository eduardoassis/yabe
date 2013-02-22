package controllers;

import java.util.List;

import models.Post;
import play.mvc.With;

@Check( "admin" )
@With( Secure.class )
@CRUD.For(Post.class)
public class AdminPosts extends CRUD {
	public static void list() {
		List<Post> posts = Post.findAll();		
		render( posts );
	}
}
