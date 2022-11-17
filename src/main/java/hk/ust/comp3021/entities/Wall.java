package hk.ust.comp3021.entities;

import java.util.Objects;

/**
 * Denotes a wall.
 */
public final class Wall extends Entity {
    @Override
    public boolean equals(Object o) {
        return o instanceof Wall;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Wall");
    }
}
