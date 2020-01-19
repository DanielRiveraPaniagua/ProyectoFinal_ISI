package urjc.isi.grafos;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class PathFinderTest {
	   private Graph G;
	   private String strGraph = "Movie 1/Actor A/Actor B/Actor H;" +
			   					 "Movie 2/Actor B/Actor C;" +
			   					 "Movie 3/Actor A/Actor C/Actor G";
	   private String delimiter = "/";
	   private String eol = ";";
	   private PathFinder pf;
	   private String s = "Actor A";
	   
	   @Before
	   public void setUp()
	   {
	      G = new Graph(strGraph, delimiter, eol);
	      pf = new PathFinder(G, s);
	   }

	   @After
	   public void tearDown()
	   {
	      G = null;
	      pf = null;
	   }
	   
	   @Test
	   public void testForHasPathTo()
	   {
		   String v = "Actor B";
		   assertTrue("Test for HasPathTo", pf.hasPathTo(v));
	   }
	   
	   @Test
	   public void testForDistanceTo()
	   {
		   String v = "Actor G";
		   assertEquals("Test for HasPathTo", 2, pf.distanceTo(v));
	   }
}
