package hk.ust.comp3021.entities;

import java.util.Objects;

/**
 * Denote an empty cell.
 */
public final class Empty extends Entity {

    @Override
    public boolean equals(Object o) {
        return o instanceof Empty;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Empty");
    }
}
