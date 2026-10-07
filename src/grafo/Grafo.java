package grafo;

import java.util.*;

public class Grafo {
    private final Set<Vertice> vertices = new LinkedHashSet<>();
    private final List<Arista> aristas = new ArrayList<>();

    public void agregarVertice(Vertice v) {
        if (v == null) throw new IllegalArgumentException("El vértice no puede ser null");
        vertices.add(v);
    }

    public void agregarArista(Vertice a, Vertice b, double similaridad) {
        verificarExiste(a);
        verificarExiste(b);
        if (a.equals(b))
            throw new IllegalArgumentException("No se permiten aristas de un vértice consigo mismo");
        if (a.getVecinos().contains(b))
            throw new IllegalArgumentException("La arista " + a + "-" + b + " ya existe");

        a.agregarVecino(b);
        b.agregarVecino(a);
        aristas.add(new Arista(a, b, similaridad));
    }

    public Set<Vertice> getVertices() { return Collections.unmodifiableSet(vertices); }
    public List<Arista> getAristas() { return Collections.unmodifiableList(aristas); }

    public Set<Vertice> getVecinos(Vertice v) {
        verificarExiste(v);
        return v.getVecinos();
    }

    public Set<Vertice> recorrer(Vertice inicio) {
        verificarExiste(inicio);
        Set<Vertice> visitados = new LinkedHashSet<>();
        Queue<Vertice> cola = new LinkedList<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            for (Vertice v : cola.remove().getVecinos()) {
                if (visitados.add(v)) cola.add(v);
            }
        }
        return visitados;
    }

    public boolean esConexo() {
        if (vertices.isEmpty()) return true;
        return recorrer(vertices.iterator().next()).size() == vertices.size();
    }

    private void verificarExiste(Vertice v) {
        if (v == null || !vertices.contains(v))
            throw new IllegalArgumentException("El vértice no existe en el grafo: " + v);
    }
}