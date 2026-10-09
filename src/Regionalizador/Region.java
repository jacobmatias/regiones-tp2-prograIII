package Regionalizador;

import java.util.*;

import Grafo.Arista;
import Grafo.Provincia;

public class Region {
    private final Set<Provincia> provincias = new LinkedHashSet<>();
    private final List<Arista> aristas = new ArrayList<>();

    public void agregarProvincia(Provincia v) {
        if (v == null) throw new IllegalArgumentException("El vértice no puede ser null");
        provincias.add(v);
    }

    public Set<Provincia> getProvincias() {
        return Collections.unmodifiableSet(provincias);
    }

    @Override
    public String toString() {
        return "Región: " + provincias.toString();
    }
    
    public void agregarArista(Arista a) {
    	if(a == null) {
    		throw new IllegalArgumentException("La arista no puede ser null");
    	}
    	aristas.add(a);
    }
    
    public List<Arista> getAristas() {
        return Collections.unmodifiableList(aristas);
    }
}
