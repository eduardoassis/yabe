import org.hibernate.mapping.Map;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");
		System.out.println("Here");
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
		
		//assertEquals( 2, frontPost.comments.size() );
		
		frontPost.addComment( "Jim", "Hello guys" );
		//assertEquals( 3, frontPost.comments.size() );
		assertEquals( 4, Comment.count() );
		
	}
	
	@Test
	public void recuperarListaDePosts() {
		List<Post> posts = Post.findAll();
		
		for (Post post : posts) {
			System.out.println(post.id +" "+ post.author.fullname );
		}
		
		assertNotNull(posts);
	}
	
	@Test
	public void testTags() {
		User bob = new User( "bob@gmail.com", "secret", "Bob" ).save();
		
		Post bobPost = new Post( bob, "My first post", "Hello world!" );
		Post anotherBobPost = new Post( bob, "Hop", "Hello world!" ).save();
		
		assertEquals( 0, Post.findTaggedWith( "Red" ).size() );
		
		bobPost.tagItWith( "Red" ).tagItWith( "Blue" ).save();
		anotherBobPost.tagItWith( "Red" ).tagItWith( "Green" ).save();
		
		assertEquals( 2, Post.findTaggedWith( "Red" ).size() );
		assertEquals( 1, Post.findTaggedWith( "Blue" ).size() );
		assertEquals( 1, Post.findTaggedWith( "Green" ).size() );
		
		assertEquals( 1, Post.findTaggedWith( "Red", "Green" ).size() );
		assertEquals( 1, Post.findTaggedWith( "Red", "Green" ).size() );
		assertEquals( 0, Post.findTaggedWith( "Red", "Green", "Blue" ).size() );
		assertEquals( 0, Post.findTaggedWith( "Green", "Blue" ).size() );
		
		List<Map> cloud = Tag.getCloud();
		assertEquals( "[{tag=Blue, pound=1}, {tag=Green, pound=1}, {tag=Red, pound=2}]", cloud.toString() );
	}
}
