package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controlador.Controlador;

public class ControladorTest {

	private Controlador controlador;

    @Before
    public void setUp() {

        controlador = new Controlador();
        controlador.agregarProvincia("Buenos Aires", 10, 20);
        controlador.agregarProvincia("Córdoba", 30, 40);
        controlador.agregarProvincia("Mendoza", 50, 60);

        controlador.agregarConexion("Buenos Aires", "Córdoba", 0.20);
        controlador.agregarConexion("Córdoba", "Mendoza", 0.70);
        controlador.agregarConexion("Buenos Aires", "Mendoza", 0.50);
    }

    @Test
    public void testObtenerSimilaridades() {

        String resultado = controlador.obtenerSimilaridades();

        assertTrue(resultado.contains("(0.20)"));
        assertTrue(resultado.contains("(0.50)"));
        assertTrue(resultado.contains("(0.70)"));
        assertEquals(3, resultado.trim().split("\\R").length);
    }

    @Test
    public void testObtenerAGM() {

        String resultado = controlador.obtenerAGM();
        assertTrue(resultado.contains("(0.20)"));
        assertTrue(resultado.contains("(0.50)"));
        assertFalse(resultado.contains("(0.70)"));
        assertEquals(2, resultado.trim().split("\\R").length);
    }

    @Test
    public void testRegionalizar() {

        String resultado = controlador.regionalizar(2);
        assertEquals(2, resultado.split("REGIÓN ").length - 1);
        assertTrue(resultado.contains("(0.20)"));
        assertFalse(resultado.contains("(0.50)"));
        assertFalse(resultado.contains("(0.70)"));
        
        assertTrue(resultado.contains("Mendoza"));
        assertTrue(resultado.contains("Sin conexiones"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegionalizarConCantidadInvalida() {
        controlador.regionalizar(4);
    }

}
