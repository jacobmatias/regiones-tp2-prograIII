package Controlador;

import java.util.*;

import Grafo.Grafo;
import Grafo.Arista;
import Grafo.Provincia;
import Agm.Kruskal;
import Regionalizador.Region;
import Regionalizador.Regionalizador;

public class Controlador {
    private final Grafo grafo;

    public Controlador() {
        this.grafo = new Grafo();
    }

    public void agregarProvincia(String nombre, double x, double y) {
        Provincia v = new Provincia(nombre, x, y);
        grafo.agregarProvincia(v);
    }

    public void agregarConexion(String prov1, String prov2, double similaridad) {
        Provincia v1 = buscarProvincia(prov1);
        Provincia v2 = buscarProvincia(prov2);
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Una o ambas provincias no existen en el sistema.");
        }
        grafo.agregarArista(v1, v2, similaridad);
    }
    
    public String regionalizar(int k) {
    	List<Region> regiones = Regionalizador.calcularRegiones(grafo, k); //regiones obtenidas
    	StringBuilder resultado = new StringBuilder(); // string para poder mostrar las regiones
    	int nroRegion = 1;
    	for(Region r: regiones) {
    		resultado.append("REGIÓN ").append(nroRegion++).append("\n");
    		if(r.getAristas().isEmpty()) {
    			for(Provincia p: r.getProvincias()) {
    				resultado.append(String.format(Locale.US,"%s (%.2f, %.2f) - Sin conexiones%n", p.getNombre(), p.getX(), p.getY()));
    			}
    		} else {
    			resultado.append(convertirAristas(r.getAristas()));
    		}
    		resultado.append("\n");
    	}
    	return resultado.toString();
    }	

    public Grafo getGrafo() {
        return this.grafo;
    }

    private Provincia buscarProvincia(String nombre) {

        for (Provincia v : grafo.getProvincias().values()) {
            if (v.getNombre().equalsIgnoreCase(nombre.trim())) {
                return v;
            }
        }
        return null;
    }
    
    public String obtenerSimilaridades() {
    	return convertirAristas(grafo.getAristas());
    }
    
    public String obtenerAGM() {
    	return convertirAristas(Kruskal.arbolGeneradorMinimo(grafo));
    }
    
    private String convertirAristas (List<Arista> aristas) {
    	StringBuilder aristasString = new StringBuilder();
    	for (Arista a: aristas) {
    		Provincia p1 = a.getProvincia1();
            Provincia p2 = a.getProvincia2();
            aristasString.append(String.format(Locale.US, "%s (%.2f, %.2f) ---- (%.2f) ---- %s (%.2f, %.2f)%n",
            		p1.getNombre(), p1.getX(), p1.getY(), a.getSimilaridad(), p2.getNombre(), p2.getX(), p2.getY()));
    	}
    	return aristasString.toString();
    }
    
    
    
}