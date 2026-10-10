package Grafo;

import java.util.*;

public class Grafo {
    private final HashMap<Coordenadas,Provincia> vertices = new LinkedHashMap<>();
    private final List<Arista> aristas = new ArrayList<>();

    public void agregarProvincia(Provincia v) {
        if (v == null) throw new IllegalArgumentException("La provincia no puede ser null");
        if(vertices.containsKey(v.getCoordenadas())) throw new IllegalArgumentException("las coordenadas ya estan ocupadas");
        vertices.put(v.getCoordenadas(),v);
    }

    public void agregarArista(Provincia a, Provincia b, double similaridad) {
        verificarExiste(a);
        verificarExiste(b);
        if (a.equals(b))
            throw new IllegalArgumentException("No se permiten aristas de una provincia consigo misma");
        if (a.getVecinos().contains(b))
            throw new IllegalArgumentException("La arista " + a + "-" + b + " ya existe");

        a.agregarVecino(b);
        b.agregarVecino(a);
        aristas.add(new Arista(a, b, similaridad));
    }

    public Map<Coordenadas,Provincia> getProvincias() { return Collections.unmodifiableMap(vertices); }
    public List<Arista> getAristas() { return Collections.unmodifiableList(aristas); }

    public Set<Provincia> getVecinos(Provincia v) {
        verificarExiste(v);
        return v.getVecinos();
    }

    public Set<Provincia> recorrer(Provincia inicio) {
        verificarExiste(inicio);
        Set<Provincia> visitados = new LinkedHashSet<>();
        Queue<Provincia> cola = new LinkedList<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            for (Provincia v : cola.remove().getVecinos()) {
                if (visitados.add(v)) cola.add(v);
            }
        }
        return visitados;
    }

    public boolean esConexo() {
        if (vertices.isEmpty()) return true;
        return recorrer(vertices.values().iterator().next()).size() == vertices.size();
    }

    private void verificarExiste(Provincia v) {
        if (v == null || !vertices.containsKey(v.getCoordenadas()))
            throw new IllegalArgumentException("La provincia no existe en el grafo: " + v);
    }
}