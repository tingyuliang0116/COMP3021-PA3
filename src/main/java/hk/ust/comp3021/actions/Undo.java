package hk.ust.comp3021.actions;


/**
 * Denotes an undo action.
 */
public final class Undo extends Action {
    /**
     * @param initiator The id of the player who give the invalid input.
     */
    public Undo(int initiator) {
        super(initiator);
    }

    /**
     * Default constructor.
     */
    public Undo() {
        this(-1);
    }

    @Override
    public String toString() {
        return "Undo";
    }
}
