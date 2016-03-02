package fr.esiea.ga.tetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class TotoTest {

	@Test
	public void shouldReturnNotNull() {
		Toto t = new Toto();
		
		assertNotNull(t.titi());
		assertTrue(t.titi().length()<12);
	}
	
}
