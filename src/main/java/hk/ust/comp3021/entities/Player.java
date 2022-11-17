package hk.ust.comp3021.entities;

import java.util.Objects;

/**
 * Denotes a player.
 */
public final class Player extends Entity {

    /**
     * Converts a player id to its char representation.
     *
     * @param id The player ID.
     * @return The char representation.
     */
    public static char idToChar(int id) {
        return (char) ('A' + id);
    }

    private final int id;

    /**
     * Initiate a player object with an id.
     *
     * @param id id of the player.
     */
    public Player(int id) {
        this.id = id;
    }

    /**
     * Get the player id.
     *
     * @return player id.
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
