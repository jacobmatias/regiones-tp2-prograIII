package grafo;

public class Arista {

    private Vertice vertice1;
    private Vertice vertice2;
    double similaridad;

    public Arista(Vertice v1,Vertice v2,double similaridad){
        this.vertice1=v1;
        this.vertice2=v2;
        this.similaridad=similaridad;
    }

    @Override
    public boolean equals(Object obj) {
        
    }
}
