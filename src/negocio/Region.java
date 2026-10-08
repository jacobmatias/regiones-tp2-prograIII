package negocio;

import java.util.*;
import grafo.Vertice;

public class Region {
    private final Set<Vertice> provincias = new LinkedHashSet<>();

    public void agregarProvincia(Vertice v) {
        if (v == null) throw new IllegalArgumentException("El vértice no puede ser null");
        provincias.add(v);
    }

    public Set<Vertice> getProvincias() {
        return Collections.unmodifiableSet(provincias);
    }

    @Override
    public String toString() {
        return "Región: " + provincias.toString();
    }
}
