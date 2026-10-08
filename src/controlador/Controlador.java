package controlador;

import java.util.*;
import grafo.Grafo;
import grafo.Vertice;
import negocio.Region;
import negocio.Regionalizador;

public class Controlador {
    private final Grafo grafo;

    public Controlador() {
        this.grafo = new Grafo();
    }

    public void agregarProvincia(String nombre, double x, double y) {
        Vertice v = new Vertice(nombre, x, y);
        grafo.agregarVertice(v);
    }

    public void agregarConexion(String prov1, String prov2, double similaridad) {
        Vertice v1 = buscarVertice(prov1);
        Vertice v2 = buscarVertice(prov2);
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Una o ambas provincias no existen en el sistema.");
        }
        grafo.agregarArista(v1, v2, similaridad);
    }

    public List<Region> regionalizar(int k) {
        return Regionalizador.calcularRegiones(this.grafo, k);
    }

    public Grafo getGrafo() {
        return this.grafo;
    }

    private Vertice buscarVertice(String nombre) {
        for (Vertice v : grafo.getVertices()) {
            if (v.getNombre().equalsIgnoreCase(nombre.trim())) {
                return v;
            }
        }
        return null;
    }
}