package controllers;

import models.Post;
import play.mvc.With;

@Check( "admin" )
@With( Secure.class )
@CRUD.For(Post.class)
public class AdminPosts extends CRUD {
}
