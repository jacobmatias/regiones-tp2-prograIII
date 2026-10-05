package grafo;

import com.sun.jdi.IntegerValue;

import java.util.*;


public class Vertice{

    private final String nombreProvincia;
    private final double x;
    private final double y;
    Set <Vertice> vecinos;

    public Vertice(String nombreProvincia, double x, double y){
        this.nombreProvincia=nombreProvincia;
        vecinos=new HashSet<>();
        this.x=x;
        this.y=y;
    }

    public

    @Override
     int hashCode() {
        return Objects.hash(nombreProvincia,x,y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Double.compare(x, vertice.x) == 0 && Double.compare(y, vertice.y) == 0;
    }
}
