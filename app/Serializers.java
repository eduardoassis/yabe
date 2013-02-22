import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class Serializers {
    
	public static JSONSerializer postsListSerializer;
     
    static {
    	boolean prettyPrint = Play.mode == Mode.DEV;
    	
    	postsListSerializer = new JSONSerializer().include(
    			"id",
    			"title",
    			"content",
    			"author.id",
    			"author.fullname").exclude("*").prettyPrint(prettyPrint);
    }
}