package Regionalizador;

import java.util.*;

import Agm.Kruskal;
import Agm.UnionFind;
import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Provincia;

public class Regionalizador {

    public static List<Region> calcularRegiones(Grafo g, int k) {
        if (g == null) {
            throw new IllegalArgumentException("El grafo no puede ser null");
        }
        
        int n = g.getProvincias().size();
        
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

        UnionFind<Provincia> uf = new UnionFind<>(g.getProvincias().values());
        for (Arista a : aristasFiltradas) {
            uf.union(a.getProvincia1(), a.getProvincia2());
        }

        //agrupar las provincias en regiones
        Map<Provincia, Region> mapaRegiones = new HashMap<>();
        for (Provincia v : g.getProvincias().values()) {
            Provincia representante = uf.find(v);
            if (!mapaRegiones.containsKey(representante)) {
                mapaRegiones.put(representante, new Region());
            }
            mapaRegiones.get(representante).agregarProvincia(v);
        }
        
        //agregar las aristas a sus respectivas regiones
        for (Arista a: aristasFiltradas) {
        	Provincia provinciaPrincipal = uf.find(a.getProvincia1());
        	mapaRegiones.get(provinciaPrincipal).agregarArista(a);
        }
      
        return new ArrayList<>(mapaRegiones.values());
    }
}