package urjc.etsit.isi.entity.test;
import static org.junit.Assert.*;

import org.junit.*;

import urjc.etsit.isi.exception.*;
import urjc.etsit.isi.entity.*;

import java.util.*;

public class GeneroTest {
	
	private int id;
	private String nombre;

	@Before     
	public void setUp()
	{
	   id = 0;
	   nombre = "";
	}
	
	@After     
	public void tearDown()
	{
	   id = 0;
	   nombre = "";
	}
	
	@Test()
	public void testIdGreaterThan1()
	{
		id = 23;
		Genero genre = new Genero();
		genre.setId(id);
		assertEquals(23,genre.getId());
		
	}

	
	@Test(expected=InvalidParameter.class)
	public void testIdLessThan1()
	{
		id = 0;
		Genero genre = new Genero();
		genre.setId(id);
	}
	
	@Test(expected=InvalidParameter.class)
	public void testNullName()
	{
		nombre = null;
		Genero genre = new Genero();
		genre.setNombre(nombre);
	}



}