package Agm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {
    private final Map<T, T> padre = new HashMap<>();
    private final Map<T, Integer> rango = new HashMap<>();

    public UnionFind(Collection<T> elementos) {
        for (T e : elementos) {
            padre.put(e, e);
            rango.put(e, 0);
        }
    }

    public T find(T x) {
        if (!padre.containsKey(x))
            throw new IllegalArgumentException("Elemento desconocido: " + x);
        if (!padre.get(x).equals(x))
            padre.put(x, find(padre.get(x))); // compresión de caminos
        return padre.get(x);
    }

    // true si estaban en conjuntos distintos (y los une)
    public boolean union(T a, T b) {
        T ra = find(a), rb = find(b);
        if (ra.equals(rb)) return false;

        int rangoA = rango.get(ra), rangoB = rango.get(rb);
        if (rangoA < rangoB) {
            padre.put(ra, rb);
        } else if (rangoA > rangoB) {
            padre.put(rb, ra);
        } else {
            padre.put(rb, ra);
            rango.put(ra, rangoA + 1);
        }
        return true;
    }

    public boolean mismoConjunto(T a, T b) {
        return find(a).equals(find(b));
    }
}