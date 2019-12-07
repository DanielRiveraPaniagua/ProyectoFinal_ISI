package urjc.etsit.isi.entity.test;
import static org.junit.Assert.*;

import org.junit.*;

import urjc.etsit.isi.exception.*;
import urjc.etsit.isi.entity.*;

import java.util.*;

public class PersonaTest {
	
	private int id;
	private String nombre;
	private String apellido;
	private Date fNacimiento;
	private Date fMuerte;
	private String paisOrigen;

	@Before     
	public void setUp()
	{
	   id = 0;
	   nombre = "";
	   apellido = "";
	   paisOrigen = "";
	}
	
	@After     
	public void tearDown()
	{
	   id = 0;
	   nombre = "";
	   apellido = "";
	   paisOrigen = "";
	}
	
	@Test()
	public void testIdGreaterThan1()
	{
		id = 23;
		Persona person = new Persona();
		person.setId(id);
		assertEquals(23,person.getId());
		
	}

	
	@Test(expected=InvalidParameter.class)
	public void testIdLessThan1()
	{
		id = 0;
		Persona person = new Persona();
		person.setId(id);
	}
	
	@Test(expected=InvalidParameter.class)
	public void testNullName()
	{
		nombre = null;
		Persona person = new Persona();
		person.setNombre(nombre);
	}

	@Test(expected=InvalidParameter.class)
	public void testNullSurname()
	{
		apellido = null;
		Persona person = new Persona();
		person.setApellido(apellido);
	}

	
	@Test(expected=InvalidParameter.class)
	public void testNullPaisOrigen()
	{
		paisOrigen = null;
		Persona person = new Persona();
		person.setApellido(paisOrigen);
	}

}