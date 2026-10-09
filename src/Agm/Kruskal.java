package Agm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Provincia;

public class Kruskal {

    // Devuelve las aristas del AGM de menor a mayor similaridad.
    // No modifica el grafo recibido.
    public static List<Arista> arbolGeneradorMinimo(Grafo g) {
        if (g.getProvincias().isEmpty())
            return new ArrayList<>();
        if (!g.esConexo())
            throw new IllegalArgumentException("El grafo no es conexo: no existe arbol generador mínimo");

        List<Arista> ordenadas = new ArrayList<>(g.getAristas());
        ordenadas.sort(Comparator.comparingDouble(Arista::getSimilaridad));

        UnionFind<Provincia> uf = new UnionFind<>(g.getProvincias());
        List<Arista> arbol = new ArrayList<>();

        for (Arista a : ordenadas) {
            if (uf.union(a.getProvincia1(), a.getProvincia2()))
                arbol.add(a);
        }
        return arbol;
    }
}
