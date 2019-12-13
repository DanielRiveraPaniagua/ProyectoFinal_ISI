package urjc.etsit.isi.entity.test;
import static org.junit.Assert.*;

import org.junit.*;

import urjc.etsit.isi.exception.*;
import urjc.etsit.isi.entity.*;

import java.util.*;

public class PeliculaTest {
	
	private int id;
	private String nombre;
	private Date anno;
	private double duracion;
	private double rating;

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
		Pelicula film = new Pelicula();
		film.setId(id);
		assertEquals(23,film.getId());
		
	}

	
	@Test(expected=InvalidParameter.class)
	public void testIdLessThan1()
	{
		id = 0;
		Pelicula film = new Pelicula();
		film.setId(id);
	}
	
	@Test(expected=InvalidParameter.class)
	public void testNullName()
	{
		nombre = null;
		Pelicula film = new Pelicula();
		film.setNombre(nombre);
	}

	@Test(expected=InvalidParameter.class)
	public void testDurationLessThan0()
	{
		duracion = -1.0;
		Pelicula film = new Pelicula();
		film.setNombre(nombre);
	}

	@Test(expected=InvalidParameter.class)
	public void testRatingLessThan0()
	{
		rating= -1.0;
		Pelicula film = new Pelicula();
		film.setRating(rating);
	}

	@Test(expected=InvalidParameter.class)
	public void testRatingMoreThan5()
	{
		rating= 6.0;
		Pelicula film = new Pelicula();
		film.setRating(rating);
	}


}