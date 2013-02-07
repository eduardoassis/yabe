import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");
	}
	
	@Test
	public void testFull() {
		
		assertEquals( 2, User.count() );
		assertEquals( 3, Post.count() );
		assertEquals( 3, Comment.count() );
		
		assertNotNull( User.connect( "bob@gmail.com", "secret" ) );
		assertNotNull( User.connect( "jeff@gmail.com", "secret" ) );
		assertNull( User.connect( "jeff@gmail.com", "badpassword" ) );
		assertNull( User.connect( "tom@gmail.com", "secret" ) );
		
		List<Post> bobPosts = Post.find( "author.email", "bob@gmail.com" ).fetch();
		assertEquals( 2, bobPosts.size() );
		
		List<Comment> bobPostsComments = Comment.find( "post.author.email", "bob@gmail.com" ).fetch();
		assertEquals( 3, bobPostsComments.size() );
		
		Post frontPost = Post.find( "order by postedAt desc").first();
		
		assertNotNull( frontPost );
		assertEquals( "About the model layer", frontPost.title );
		
		assertEquals( 2, frontPost.comments.size() );
		
		frontPost.addComment( "Jim", "Hello guys" );
		assertEquals( 3, frontPost.comments.size() );
		assertEquals( 4, Comment.count() );
		
	}
}
