package ClasesTest;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import Agm.Kruskal;
import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Provincia;

public class KruskalTest {

    //private Provincia v(String nombre) {
        //return new Provincia(nombre, 0, 0);
    //}

    // A-B:1, B-C:2, A-C:10

    private Grafo triangulo() {
        Grafo g = new Grafo();
        Provincia a = v("A"), b = v("B"), c = v("C");
        g.agregarProvincia(a);
        g.agregarProvincia(b);
        g.agregarProvincia(c);
        g.agregarArista(a, b, 1);
        g.agregarArista(b, c, 2);
        g.agregarArista(a, c, 10);
        return g;
    }

    @Test
    public void grafoSinVerticesDaArbolVacio() {
        assertTrue(Kruskal.arbolGeneradorMinimo(new Grafo()).isEmpty());
    }

    @Test
    public void grafoDeUnVerticeDaArbolVacio() {
        Grafo g = new Grafo();
        g.agregarProvincia(v("A"));
        assertTrue(Kruskal.arbolGeneradorMinimo(g).isEmpty());
    }

    @Test
    public void elArbolTieneNMenosUnaAristas() {
        assertEquals(2, Kruskal.arbolGeneradorMinimo(triangulo()).size());
    }

    @Test
    public void descartaLaAristaMasPesadaDelCiclo() {
        double suma = 0;
        for (Arista a : Kruskal.arbolGeneradorMinimo(triangulo()))
            suma += a.getSimilaridad();
        assertEquals(3.0, suma, 0.0001);
    }

    @Test
    public void lasAristasSalenOrdenadasDeMenorAMayor() {
        List<Arista> arbol = Kruskal.arbolGeneradorMinimo(triangulo());
        assertTrue(arbol.get(0).getSimilaridad() <= arbol.get(1).getSimilaridad());
    }

    @Test
    public void conPesosIgualesIgualmenteArmaArbol() {
        Grafo g = new Grafo();
        Provincia a = v("A"), b = v("B"), c = v("C");
        g.agregarProvincia(a);
        g.agregarProvincia(b);
        g.agregarProvincia(c);
        g.agregarArista(a, b, 5);
        g.agregarArista(b, c, 5);
        g.agregarArista(a, c, 5);
        assertEquals(2, Kruskal.arbolGeneradorMinimo(g).size());
    }

    @Test
    public void noModificaElGrafoOriginal() {
        Grafo g = triangulo();
        Kruskal.arbolGeneradorMinimo(g);
        assertEquals(3, g.getAristas().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void grafoNoConexoLanzaExcepcion() {
        Grafo g = new Grafo();
        g.agregarProvincia(v("A"));
        g.agregarProvincia(v("B"));
        Kruskal.arbolGeneradorMinimo(g);
    }
}