package Grafo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Provincia {
    private final String nombre;
    private final double x;
    private final double y;
    private final Set<Provincia> vecinos = new LinkedHashSet<>();

    public Provincia(String nombre, double x, double y) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        this.nombre = nombre.trim();
        this.x = x;
        this.y = y;
    }

    public String getNombre() { return nombre; }
    public double getX() { return x; }
    public double getY() { return y; }

    public Set<Provincia> getVecinos() {
        return Collections.unmodifiableSet(vecinos);
    }

    // visibilidad de paquete: solo Grafo puede llamarlo
    void agregarVecino(Provincia otro) { vecinos.add(otro); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincia)) return false;
        return nombre.equals(((Provincia) o).nombre);
    }

    @Override
    public int hashCode() { return Objects.hash(nombre); }

    @Override
    public String toString() { return nombre; }
}