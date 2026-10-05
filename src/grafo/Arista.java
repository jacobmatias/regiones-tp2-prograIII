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
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista arista = (Arista) obj;
        return (arista.vertice1.equals(this.vertice1) && arista.vertice2.equals(this.vertice2)
                    ||
                arista.vertice2.equals(this.vertice1) && arista.vertice1.equals(this.vertice2)
                    &&
                arista.similaridad==this.similaridad
                    );
    }
}
