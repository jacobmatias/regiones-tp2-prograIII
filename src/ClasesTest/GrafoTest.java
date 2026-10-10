package ClasesTest;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import Grafo.Grafo;
import Grafo.Provincia;

public class GrafoTest {

    private Grafo g;
    private Provincia a, b, c;

    @Before
    public void setUp() {
        g = new Grafo();
        a = new Provincia("A", 500, 200);
        b = new Provincia("B", 0, 0);
        c = new Provincia("C", 10, 20);
        g.agregarProvincia(a);
        g.agregarProvincia(b);
        g.agregarProvincia(c);
    }

    @Test
    public void agregarArista() {
        g.agregarArista(a, b, 3);
        assertEquals(1, g.getAristas().size());
        assertTrue(g.getVecinos(a).contains(b));
        assertTrue(g.getVecinos(b).contains(a));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConsigoMisma() {
        g.agregarArista(a, a, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaDuplicadaEnCualquierOrden() {
        g.agregarArista(a, b, 1);
        g.agregarArista(b, a, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeInexistente() {
        Provincia z = new Provincia("Z", 10, 0);
        g.agregarArista(a, z, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeNull() {
        g.agregarArista(a, null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agregarVerticeNull() {
        g.agregarProvincia(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticeRepetidoNoSeDuplica() {
        g.agregarProvincia(new Provincia("A", 500, 200));
    }

    @Test
    public void recorrerAlcanzaSoloLosVerticesDeLaComponente() {
        g.agregarArista(a, b, 1);
        Set<Provincia> alcanzados = g.recorrer(a);
        assertEquals(2, alcanzados.size());
        assertTrue(alcanzados.contains(b));
        assertFalse(alcanzados.contains(c));
    }

    @Test
    public void conexo() {
        g.agregarArista(a, b, 1);
        g.agregarArista(b, c, 1);
        assertTrue(g.esConexo());
    }

    @Test
    public void noConexo() {
        g.agregarArista(a, b, 1);
        assertFalse(g.esConexo());
    }

    @Test
    public void grafoVacioEsConexo() {
        assertTrue(new Grafo().esConexo());
    }

    @Test
    public void grafoDeUnSoloVerticeEsConexo() {
        Grafo solo = new Grafo();
        solo.agregarProvincia(new Provincia("X", 0, 0));
        assertTrue(solo.esConexo());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAristasNoPermiteModificarDesdeAfuera() {
        g.getAristas().add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getVecinosNoPermiteModificarDesdeAfuera() {
        g.getVecinos(a).add(b);
    }
}
