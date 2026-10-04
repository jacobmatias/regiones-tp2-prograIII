package grafo;

import com.sun.jdi.IntegerValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Vertice{

    private final String nombreProvincia;
    private final double x;
    private final double y;
    List<Set<Vertice>> vecinos;

    public Vertice(String nombreProvincia, double x, double y){
        this.nombreProvincia=nombreProvincia;
        vecinos=new ArrayList<>();
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
        return Double.compare(x, vertice.x) == 0 && Double.compare(y, vertice.y) == 0 && Objects.equals(nombreProvincia, vertice.nombreProvincia);
    }
}
