package GrafoTest;

import grafo.Arista;
import grafo.Raiz;
import grafo.Vertice;
import org.junit.Test;

import static org.junit.Assert.*;

public class Raiztest {


public void inicializer(){
        Raiz raiz=new Raiz();
        for(int i=0; i<10;i++){


        }
    }
@Test
public void ingresarVertice(){};

@Test
public void eliminarVertice(){};

@Test
public void ingresarRelacion(){}

@Test
public void recorrerVertices(){}

@Test
public void compararVertices(){

    Vertice vertice1=new Vertice("santiago",10,98);
    Vertice vertice2=new Vertice("santiago",98,10);

    assertFalse(vertice1.equals(vertice2));
}

@Test
public void comparacion_AristasDistintas(){

    Vertice vertice1=new Vertice("santiago",10,98);
    Vertice vertice2=new Vertice("santiago",98,10);

    Arista arista= new Arista(vertice1,vertice2,300);
    Arista arista2= new Arista(vertice2,vertice1,200);

    assertFalse(arista.equals(arista2));

}
@Test
public void comparacion_AristasIguales(){

    Vertice vertice1=new Vertice("santiago",98,10);
    Vertice vertice2=new Vertice("santiago",98,10);

    Arista arista= new Arista(vertice1,vertice2,300);
    Arista arista2= new Arista(vertice2,vertice1,300);

    assertTrue(arista.equals(arista2));

}


}
