package urjc.isi.generostest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import urjc.isi.entidades.Generos;
import urjc.isi.exceptions.InvalidParameter;

public class GenerosEntityTest {
	
	private Generos genero = new Generos();
	
	@Test (expected = InvalidParameter.class)
	public void nameNull() {
		String name = null;
		genero.setNombre(name);
	}

	@Test
	public void nameOk() {
		String name = "Terror";
		genero.setNombre(name);
	}
	
	@Test
	public void genreEqual() {
		String name = "Terror";
		genero.setNombre(name);
		Generos genero2 = new Generos();
		genero2.setNombre(name);
		assertTrue(genero.equals(genero2));
	}
	
	@Test
	public void genreNotEqual() {
		String name = "Terror";
		genero.setNombre(name);
		Generos genero2 = new Generos();
		genero2.setNombre("Teor");
		assertTrue(false == genero.equals(genero2));
	}
}
