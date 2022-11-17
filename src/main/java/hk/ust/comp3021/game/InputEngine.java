package hk.ust.comp3021.game;

import hk.ust.comp3021.actions.Action;
import org.jetbrains.annotations.NotNull;

/**
 * An engine for getting inputs from players.
 */
public interface InputEngine {

    /**
     * Fetches an unprocessed action performed by the players.
     * <p>
     * If the next action is not available yet, this method will block until it is.
     *
     * @return the action to process.
     */
    @NotNull
    Action fetchAction();
}
