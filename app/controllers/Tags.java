package controllers;

import play.mvc.Controller;
import play.mvc.With;
import controllers.CRUD;

@Check( "admin" )
@With( Secure.class )
public class Tags extends CRUD {

}
