package urjc.isi.grafos;

import static org.junit.Assert.assertEquals;

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
	  	assertEquals(1,st.size());
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
	  	assertEquals(-1,st.size());
	}
}
