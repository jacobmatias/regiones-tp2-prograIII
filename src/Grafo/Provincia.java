package Grafo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

class Coordenadas{
    private final double x;
    private final double y;

    public Coordenadas(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordenadas)) return false;
        return  (Double.compare(x,((Coordenadas) o).getX())==0 && Double.compare(y, ((Coordenadas) o).getY())==0) ;
    }

    @Override
    public int hashCode() { return Objects.hash(x,y); }

}
public class Provincia {
    private final String nombre;
    private final Coordenadas coordenadas;
    private final Set<Provincia> vecinos = new LinkedHashSet<>();

    public Provincia(String nombre, double x, double y) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        this.nombre = nombre.trim();
        this.coordenadas= new Coordenadas(x,y);
    }

    public String getNombre() { return nombre; }
    public double getX() { return coordenadas.getX(); }
    public double getY() { return coordenadas.getY(); }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public Set<Provincia> getVecinos() {
        return Collections.unmodifiableSet(vecinos);
    }

    // visibilidad de paquete: solo Grafo puede llamarlo
    void agregarVecino(Provincia otro) { vecinos.add(otro); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincia)) return false;
        return  (Double.compare(coordenadas.getX(),((Provincia) o).getX())==0 && Double.compare(coordenadas.getY(), (((Provincia) o).getY()))==0) ;
    }

    @Override
    public int hashCode() { return Objects.hash(coordenadas.getX(),coordenadas.getY()); }

    @Override
    public String toString() { return nombre; }
}