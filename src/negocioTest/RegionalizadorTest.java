package negocioTest;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import grafo.Grafo;
import grafo.Vertice;
import negocio.Region;
import negocio.Regionalizador;

public class RegionalizadorTest {
    private Grafo grafo;
    private Vertice v1, v2, v3, v4;

    @Before
    public void setUp() {
        grafo = new Grafo();
        v1 = new Vertice("A", 0, 0);
        v2 = new Vertice("B", 1, 1);
        v3 = new Vertice("C", 2, 2);
        v4 = new Vertice("D", 3, 3);

        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);
        grafo.agregarVertice(v4);

        grafo.agregarArista(v1, v2, 10.0);
        grafo.agregarArista(v2, v3, 50.0);
        grafo.agregarArista(v3, v4, 20.0);
    }

    @Test
    public void testCasoK1() {
        List<Region> regiones = Regionalizador.calcularRegiones(grafo, 1);
        assertEquals(1, regiones.size());
        assertEquals(4, regiones.get(0).getProvincias().size());
    }

    @Test
    public void testCasoKMaximo() {
        List<Region> regiones = Regionalizador.calcularRegiones(grafo, 4);
        assertEquals(4, regiones.size());
        for (Region r : regiones) {
            assertEquals(1, r.getProvincias().size());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKInvalidoMenorA1() {
        Regionalizador.calcularRegiones(grafo, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKInvalidoMayorAN() {
        Regionalizador.calcularRegiones(grafo, 5);
    }

    @Test
    public void testRegionalizacionCorrectaK2() {
        List<Region> regiones = Regionalizador.calcularRegiones(grafo, 2);
        assertEquals(2, regiones.size());

        for (Region r : regiones) {
            if (r.getProvincias().contains(v1)) {
                assertTrue(r.getProvincias().contains(v2));
                assertFalse(r.getProvincias().contains(v3));
            } else {
                assertTrue(r.getProvincias().contains(v3));
                assertTrue(r.getProvincias().contains(v4));
            }
        }
    }
}