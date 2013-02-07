import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}
	
	@Test
	public void createAndRetrieveUser() {
		new User( "eduardouflassis@gmail.com", "123", "Eduardo Assis da Silva").save();
		User user = User.find("byEmail", "eduardouflassis@gmail.com").first();
		assertNotNull( user );
		assertEquals( "Eduardo Assis da Silva", user.fullName );
	}
	
	@Test
	public void tryConnectAsUser() {
		
		new User( "bob@gmail.com", "secret", "Bob" ).save();
		assertNotNull( User.connect( "bob@gmail.com", "secret" ) );
		assertNull( User.connect( "bob@gmail.com", "badPassword" ) );
		assertNull( User.connect( "tom@gmail.com", "secret" ) );
	}
	
	@Test
	public void createPost() {
		User bob = new User( "bob@mail.com", "789", "Bob Flingger").save();
		
		new Post( bob, "My first post", "Hello world!").save();
		
		assertEquals( 1, Post.count() );
		
		List<Post> bobPosts = Post.find( "byAuthor", bob ).fetch();
		
		assertEquals( 1,  bobPosts.size() );
		
		Post firstPost = bobPosts.get( 0 );
		
		assertNotNull( firstPost );
		assertEquals( bob, firstPost.author );
		assertEquals( "My first post", firstPost.title );
		assertEquals( "Hello world!", firstPost.content );
		assertNotNull( firstPost.postedAt );
	}
	
	@Test
	public void PostComments() {
		
		User bob = new User( "bob@gmail.com", "secret", "Bob" ).save();
		Post bobPost = new Post( bob, "My first post", "Hello world" ).save();
		
		new Comment( bobPost, "Jeff", "Nice post" ).save();
		new Comment( bobPost, "Tom", "I knew that!" ).save();
		
		List<Comment> bobPostComments = Comment.find( "byPost", bobPost ).fetch();
		
		assertEquals( 2, bobPostComments.size() );
		
		Comment firstComment = bobPostComments.get( 0 );
		assertNotNull( firstComment );
		assertEquals( "Jeff", firstComment.author );
		assertEquals( "Nice post", firstComment.content );
		assertNotNull( firstComment.postedAt );
		
		Comment secondComment = bobPostComments.get( 1 );
		assertNotNull( secondComment );
		assertEquals( "Tom", secondComment.author );
		assertEquals( "I knew that!" , secondComment.content );
		assertNotNull( secondComment.postedAt );
	}
}
