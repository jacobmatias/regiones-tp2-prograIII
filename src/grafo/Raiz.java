package grafo;



import java.util.HashSet;
import java.util.Iterator;


public class Raiz {

    private HashSet<Vertice> vertices;
    private HashSet<Arista> aristas;
    private Vertice actual;
    private HashSet marcados;

    public Raiz(){
        this.vertices=new HashSet<>();
    }

    public void ingresarVertice(Vertice vertice){
        vertices.add(vertice);
    }

    public void ingresarArista(Vertice v1,Vertice v2,double similaridad){
            v1.vecinos.add(v2);//esto lo voy a cambiar despues(es para probar)
            v2.vecinos.add(v1);
            aristas.add(new Arista(v1,v2,similaridad));

    }
    public void recorrerGrafo() {
        HashSet<Vertice> marcados=new HashSet<>();
        recorrerAuxiliar(vertices.iterator().next(),marcados);

    }

    public void recorrerAuxiliar(Vertice actual, HashSet Vmarcados) {
        Vmarcados.add(actual);
        System.out.println(actual.toString());

        if (!actual.vecinos.isEmpty()) {
            for (int i = 0; i < actual.vecinos.size()-1; i++) {
                Vertice siguiente = actual.vecinos.iterator().next();
                if (!Vmarcados.contains(siguiente))
                    recorrerAuxiliar(siguiente, Vmarcados);
                break;
            }
        }


    }}
    //para verificar que sea conexo tengo que poder recorrer el grafo desde un vertice



