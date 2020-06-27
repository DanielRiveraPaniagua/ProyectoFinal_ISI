package urjc.isi.generostest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jetty.client.api.Request;
import org.junit.Test;

import urjc.isi.entidades.Generos;
import urjc.isi.service.GenerosService;

public class GenerosServiceTest {

	private GenerosService gs = mock(GenerosService.class);
	
	@Test
	public void getAllTestList() {
		try {
			when(gs.getAllGeneros()).thenReturn(new ArrayList<Generos>());
			assertTrue(gs.getAllGeneros()!= null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = SQLException.class)
	public void getAllTestException() throws SQLException {
		when(gs.getAllGeneros()).thenThrow(new SQLException());
		gs.getAllGeneros();
	}
	
	@Test
	public void uploadTableTest() throws SQLException {
		spark.Request req = mock(spark.Request.class);
		when(gs.uploadTable(req)).thenReturn("File uploaded!");
		assertEquals("File uploaded!",gs.uploadTable(req));
	}
	
}
