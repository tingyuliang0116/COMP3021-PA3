package hk.ust.comp3021.entities;

import java.util.Objects;

/**
 * Denotes a box.
 */
public final class Box extends Entity {
    private final int playerId;

    /**
     * Create a Box entity.
     *
     * @param playerId the id of the player that can move this box.
     */
    public Box(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Get the id of the player that is allowed to move this box.
     *
     * @return player id.
     */
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box box)) return false;
        return playerId == box.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
