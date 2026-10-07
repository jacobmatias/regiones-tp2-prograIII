package agm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import grafo.Arista;
import grafo.Grafo;
import grafo.Vertice;

public class Kruskal {

    // Devuelve las aristas del AGM de menor a mayor similaridad.
    // No modifica el grafo recibido.
    public static List<Arista> arbolGeneradorMinimo(Grafo g) {
        if (g.getVertices().isEmpty())
            return new ArrayList<>();
        if (!g.esConexo())
            throw new IllegalArgumentException("El grafo no es conexo: no existe arbol generador mínimo");

        List<Arista> ordenadas = new ArrayList<>(g.getAristas());
        ordenadas.sort(Comparator.comparingDouble(Arista::getSimilaridad));

        UnionFind<Vertice> uf = new UnionFind<>(g.getVertices());
        List<Arista> arbol = new ArrayList<>();

        for (Arista a : ordenadas) {
            if (uf.union(a.getVertice1(), a.getVertice2()))
                arbol.add(a);
        }
        return arbol;
    }
}