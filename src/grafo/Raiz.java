package grafo;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Raiz {

    private HashSet<Vertice> vertices;
    private HashSet<Arista> aristas;
    public Raiz(){
        this.vertices=new HashSet<>();
    }

    public void ingresarVertice(Vertice vertice){
        vertices.add(vertice);
    }
}
