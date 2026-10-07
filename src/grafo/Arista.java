package grafo;

public class Arista {
    private final Vertice vertice1;
    private final Vertice vertice2;
    private final double similaridad;

    public Arista(Vertice v1, Vertice v2, double similaridad) {
        this.vertice1 = v1;
        this.vertice2 = v2;
        this.similaridad = similaridad;
    }

    public Vertice getVertice1() 
    { 
    	return vertice1;
    }
    
    public Vertice getVertice2() 
    {
    	return vertice2; 
    }
    
    public double getSimilaridad() 
    { 
    	return similaridad; 
    }
}