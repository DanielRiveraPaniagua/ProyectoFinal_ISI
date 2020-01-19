package urjc.isi.grafos;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class STTest {
	
	   ST<Integer, String> map;
	   
	   @Before
	   public void setUp()
	   {
		   map = new ST<Integer, String>();
	   }

	   @After
	   public void tearDown()
	   {
		   map = null;
	   }
	   
	   @Test
	   public void testForPutGet()
	   {
		   map.put(18, "Pablo");
		   assertEquals("Test for put and get", "Pablo",map.get(18));
	   }
	   
	   @Test (expected = IllegalArgumentException.class)
	   public void testForInvalidPut()
	   {
		   map.put(null, "Pablo");
	   }
	   
	   @Test (expected = IllegalArgumentException.class)
	   public void testForInvalidGet()
	   {
		   map.get(null);
	   }
	   
	   @Test
	   public void testForContains()
	   {
		   map.put(18, "Pablo");
		   assertTrue("Test for put and get", map.contains(18));
	   }
	   
	   @Test (expected = IllegalArgumentException.class)
	   public void testForInvalidContains()
	   {
		   map.contains(null);
	   }
	   
	   @Test
	   public void testForRemove()
	   {
		   map.put(18, "Pablo");
		   map.remove(18);
		   assertFalse("Test for put and get", map.contains(18));
	   }
	   
	   @Test (expected = IllegalArgumentException.class)
	   public void testForInvalidRemove()
	   {
		   map.remove(null);
	   }

	   @Test
	   public void testForSize()
	   {
		   map.put(18, "Pablo");
		   map.put(20, "María");
		   map.put(3, "Carlos");
		   assertEquals("Test for edges", 3, map.size());
	   }

	   @Test
	   public void testForEmpty()
	   {
		   assertTrue("Test for edges", map.isEmpty());
	   }
	   
	   @Test
	   public void testForMin()
	   {
		   map.put(18, "Pablo");
		   map.put(20, "María");
		   map.put(3, "Carlos");
		   assertTrue("Test for min", 3 == map.min());
	   }
	   
	   @Test (expected = NoSuchElementException.class)
	   public void testForInvalidMin()
	   {
		   map.min();
	   }
	   
	   @Test
	   public void testForMax()
	   {
		   map.put(18, "Pablo");
		   map.put(20, "María");
		   map.put(3, "Carlos");
		   assertTrue("Test for max", 20 == map.max());
	   }
	   
	   @Test (expected = NoSuchElementException.class)
	   public void testForInvalidMax()
	   {
		   map.max();
	   }
	   
	   @Test
	   public void testForCeiling()
	   {
		   map.put(18, "Pablo");
		   map.put(20, "María");
		   map.put(3, "Carlos");
		   assertTrue("Test for ceiling", 18 == map.ceiling(10));
	   }
	   
	   @Test
	   public void testForFloor()
	   {
		   map.put(18, "Pablo");
		   map.put(20, "María");
		   map.put(3, "Carlos");
		   assertTrue("Test for floor", 18 == map.floor(19));
	   }
}
