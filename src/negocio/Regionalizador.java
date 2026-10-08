package negocio;

import java.util.*;
import agm.Kruskal;
import agm.UnionFind;
import grafo.Arista;
import grafo.Grafo;
import grafo.Vertice;

public class Regionalizador {

    public static List<Region> calcularRegiones(Grafo g, int k) {
        if (g == null) {
            throw new IllegalArgumentException("El grafo no puede ser null");
        }
        
        int n = g.getVertices().size();
        
        if (k <= 0 || k > n) {
            throw new IllegalArgumentException("El valor de k debe estar entre 1 y " + n);
        }
        if (n == 0) {
            return new ArrayList<>();
        }

        List<Arista> agm = Kruskal.arbolGeneradorMinimo(g);

        //elimina las k - 1 aristas de mayor peso
        int aristasAQuedarse = n - k;
        List<Arista> aristasFiltradas = new ArrayList<>();
        for (int i = 0; i < aristasAQuedarse; i++) {
            aristasFiltradas.add(agm.get(i));
        }

        UnionFind<Vertice> uf = new UnionFind<>(g.getVertices());
        for (Arista a : aristasFiltradas) {
            uf.union(a.getVertice1(), a.getVertice2());
        }

        //agrupar los vertices en regiones
        Map<Vertice, Region> mapaRegiones = new HashMap<>();
        for (Vertice v : g.getVertices()) {
            Vertice representante = uf.find(v);
            if (!mapaRegiones.containsKey(representante)) {
                mapaRegiones.put(representante, new Region());
            }
            mapaRegiones.get(representante).agregarProvincia(v);
        }

        return new ArrayList<>(mapaRegiones.values());
    }
}