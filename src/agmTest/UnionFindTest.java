package agmTest;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

import agm.UnionFind;

public class UnionFindTest {

    @Test
    public void inicialmenteCadaUnoEstaSolo() {
        UnionFind<String> uf = new UnionFind<>(Arrays.asList("a", "b", "c"));
        assertFalse(uf.mismoConjunto("a", "b"));
    }

    @Test
    public void unionUneConjuntos() {
        UnionFind<String> uf = new UnionFind<>(Arrays.asList("a", "b", "c"));
        assertTrue(uf.union("a", "b"));
        assertTrue(uf.mismoConjunto("a", "b"));
        assertFalse(uf.mismoConjunto("a", "c"));
    }

    @Test
    public void unirDosVecesDevuelveFalse() {
        UnionFind<String> uf = new UnionFind<>(Arrays.asList("a", "b"));
        uf.union("a", "b");
        assertFalse(uf.union("a", "b"));
    }

    @Test
    public void transitividad() {
        UnionFind<String> uf = new UnionFind<>(Arrays.asList("a", "b", "c", "d"));
        uf.union("a", "b");
        uf.union("b", "c");
        assertTrue(uf.mismoConjunto("a", "c"));
        assertFalse(uf.mismoConjunto("a", "d"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void elementoDesconocido() {
        new UnionFind<>(Arrays.asList("a")).find("z");
    }
}