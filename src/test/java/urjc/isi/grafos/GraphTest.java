package urjc.isi.grafos;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import urjc.isi.grafos.*;

public class GraphTest {
	   private Graph G;
	   private String strGraph = "Movie 1/Actor A/Actor B/Actor H;" +
			   					 "Movie 2/Actor B/Actor C;" +
			   					 "Movie 3/Actor A/Actor C/Actor G";
	   private String delimiter = "/";
	   private String eol = ";";

	   @Before
	   public void setUp()
	   {
	      G = new Graph(strGraph, delimiter, eol);
	   }

	   @After
	   public void tearDown()
	   {
	      G = null;
	   }

	   @Test
	   public void testForVertices()
	   {
		   assertEquals("Test for vertices", 8,G.V());
	   }

	   @Test
	   public void testForEdges()
	   {
		   assertEquals("Test for edges", 8,G.E());
	   }

	   @Test
	   public void testForDegree()
	   {
		  String v = "Actor A";
	      assertEquals("Test for degree", 2,G.degree(v));
	   }

	   @Test
	   public void testForPopularity()
	   {
		  String v = "Actor A";
	      assertTrue("Test for popularity", G.popularity(v) == 1.75);
	   }
	   
	   @Test
	   public void testForHasEdge()
	   {
		  String v = "Movie 1";
		  String w = "Actor A";
	      assertTrue("Test for hasEdges", G.hasEdge(v,w));
	   }
	   
	   @Test
	   public void testForAddEdge()
	   {
		  String v = "Actor A";
		  String w = "Actor B";
		  G.addEdge(v, w);
	      assertTrue("Test for add edges", G.hasEdge(v,w));
	   }
	   
	   @Test
	   public void testForHasVertex()
	   {
		  String v = "Movie 1";
	      assertTrue("Test for hasVertex", G.hasVertex(v));
	   }
	   
	   @Test
	   public void testForAddVertex()
	   {
		  String v = "Actor Z";
		  G.addVertex(v);
	      assertTrue("Test for add vertex", G.hasVertex(v));
	   }
	   
	   @Test
	   public void testForString()
	   {
		  String str = "Actor A: Movie 1 Movie 3 \n" +
				  	   "Actor B: Movie 1 Movie 2 \n" +
				  	   "Actor C: Movie 2 Movie 3 \n" +
				  	   "Actor G: Movie 3 \n" +
				  	   "Actor H: Movie 1 \n" +
				  	   "Movie 1: Actor A Actor B Actor H \n" +
				  	   "Movie 2: Actor B Actor C \n" +
				  	   "Movie 3: Actor A Actor C Actor G \n";
	      assertTrue("Test for string", G.toString().equals(str));
	   }
}
