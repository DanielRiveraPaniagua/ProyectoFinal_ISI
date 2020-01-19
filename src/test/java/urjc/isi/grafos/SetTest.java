package urjc.isi.grafos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class SetTest {
	@Test(expected=IllegalArgumentException.class)
	public void addIllegalArgument()
	{		
		SET<String> st = new SET<String>();
		String key = null;
	  	st.add(key);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void containsIllegalArgument()
	{		
		SET<String> st = new SET<String>();
		String key = null;
	  	st.contains(key);
	}
	@Test
	public void containsIsContained()
	{		
		SET<String> st = new SET<String>();
		st.add("hola");
		st.add("cat");
		st.add("dog");
		String key = "hola";
	  	assertEquals(true,st.contains(key));
	}
	@Test
	public void containsIsNOTContained()
	{		
		SET<String> st = new SET<String>();
		st.add("hola");
		st.add("cat");
		st.add("dog");
		String key = "bat";
	  	assertEquals(false,st.contains(key));
	}

	@Test(expected=IllegalArgumentException.class)
	public void deleteIllegalArgument()
	{		
		SET<String> st = new SET<String>();
		String key = null;
	  	st.delete(key);
	}

	@Test
	public void sizeOK()
	{		
		SET<String> st = new SET<String>();
		st.add("hola");
		st.add("cat");
		st.add("dog");
	  	assertEquals(3,st.size());
	}
	@Test(expected=NoSuchElementException.class)
	public void maxNoSuchElementException()
	{		
		SET<String> st = new SET<String>();
	  	st.max();
	}
	@Test
	public void maxOK()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(0);
		st.add(1);
		st.add(-1);
	  	assertTrue(1==st.max());
	}
	@Test(expected=NoSuchElementException.class)
	public void minNoSuchElementException()
	{		
		SET<String> st = new SET<String>();
	  	st.min();
	}
	@Test
	public void minOK()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(-1);
		st.add(0);
		st.add(1);
	  	assertTrue(-1==st.min());
	}
	@Test(expected=NoSuchElementException.class)
	public void ceilingNoSuchElementException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(-1);
		st.add(0);
		st.add(1);
		st.ceiling(2);
	}
	@Test(expected=IllegalArgumentException.class)
	public void ceilingIllegalArgumentException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.ceiling(null);
	}
	@Test
	public void ceilingOK()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(-2);
		st.add(-1);
		st.add(0);
		st.add(1);
		st.add(2);
	  	assertTrue(0==st.ceiling(0));
	}
	@Test(expected=NoSuchElementException.class)
	public void floorNoSuchElementException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(-1);
		st.add(0);
		st.add(1);
		st.floor(-2);
	}
	@Test(expected=IllegalArgumentException.class)
	public void floorIllegalArgumentException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.floor(null);
	}
	@Test
	public void floorOK()
	{		
		SET<Integer> st = new SET<Integer>();
		st.add(-2);
		st.add(-1);
		st.add(0);
		st.add(1);
		st.add(2);
	  	assertTrue(0==st.floor(0));
	}
	@Test(expected=IllegalArgumentException.class)
	public void unionIllegalArgumentException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.union(null);
	}
	@Test
	public void unionOK()
	{		
		SET<String> st = new SET<String>();
		SET<String> ost = new SET<String>();
		SET<String> union  = new SET<String>();
		SET<String> cmp  = new SET<String>();
		st.add("Hello");
		ost.add("World!");
		cmp.add("Hello");
		cmp.add("World!");
		union=st.union(ost);
		assertTrue(union.equals(cmp));
	}
	@Test
	public void unionEmpty()
	{		
		SET<String> st = new SET<String>();
		SET<String> ost = new SET<String>();
		SET<String> union  = new SET<String>();
		SET<String> cmp  = new SET<String>();;
		union=st.union(ost);
		assertTrue(union.equals(cmp));
	}
	@Test(expected=IllegalArgumentException.class)
	public void intersectsIllegalArgumentException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.intersects(null);
	}
	@Test
	public void intersectsOK()
	{		
		SET<String> st = new SET<String>();
		SET<String> ost = new SET<String>();
		SET<String> union  = new SET<String>();
		SET<String> cmp  = new SET<String>();
		st.add("Hello");
		st.add("World");
		ost.add("Hello");
		ost.add("Pedro");
		cmp.add("Hello");
		union=st.intersects(ost);
		assertTrue(union.equals(cmp));
	}
	@Test
	public void intersectsEmpty()
	{		
		SET<String> st = new SET<String>();
		SET<String> ost = new SET<String>();
		SET<String> union  = new SET<String>();
		SET<String> cmp  = new SET<String>();;
		union=st.union(ost);
		assertTrue(union.equals(cmp));
	}
	@Test
	public void intersectsNotCoincidence()
	{		
		SET<String> st = new SET<String>();
		SET<String> ost = new SET<String>();
		SET<String> union  = new SET<String>();
		SET<String> cmp  = new SET<String>();
		st.add("World");
		ost.add("Hello");
		ost.add("Pedro");
		union=st.intersects(ost);
		assertTrue(union.equals(cmp));
	}
	@Test(expected=UnsupportedOperationException.class)
	public void hasCodeException()
	{		
		SET<Integer> st = new SET<Integer>();
		st.hashCode();
	}
	@Test
	public void toStringOK()
	{		
		SET<String> st = new SET<String>();
		st.add("World");
		st.add("Hello");
		st.add("Pedro");
		assertTrue("{ Hello, Pedro, World }".equals(st.toString()));
	}
}
