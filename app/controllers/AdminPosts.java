package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;

import play.libs.Codec;
import play.mvc.Controller;

@CRUD.For(Post.class)
public class AdminPosts extends CRUD {
}
