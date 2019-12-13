package urjc.etsit.isi.InterfacesDAOImpl;

import static org.junit.Assert.*;

import org.junit.*;

import urjc.etsit.isi.exception.*;
import urjc.etsit.isi.InterfacesDAOImpl.*;

import java.util.*;

public class GenericDAOImplTest {
	
	GenericDAOImpl objetoPrueba;

	@Before     
	public void setUp()
	{
	   
	}
	
	@After     
	public void tearDown()
	{
	
	}
	
	@Test()
	public void testConnection()
	{
		objetoPrueba = new GenericDAOImpl();
		objetoPrueba.connect();
	}

}
