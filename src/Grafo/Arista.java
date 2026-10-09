package Grafo;

public class Arista {
    private final Provincia provincia1;
    private final Provincia provincia2;
    private final double similaridad;

    public Arista(Provincia v1, Provincia v2, double similaridad) {
        this.provincia1 = v1;
        this.provincia2 = v2;
        this.similaridad = similaridad;
    }

    public Provincia getProvincia1() 
    { 
    	return provincia1;
    }
    
    public Provincia getProvincia2() 
    {
    	return provincia2; 
    }
    
    public double getSimilaridad() 
    { 
    	return similaridad; 
    }
}