package e2;

public interface PawnLogic {

    /**
     *
     * @param position The position to hit.
     * @return whether the Pawn has been hit or not.
     */
    boolean hit(Pair<Integer, Integer> position);

    /**
     * Get the position of the Pawn.
     *
     * @return the position of the Pawn.
     */
    Pair<Integer, Integer> getPosition();

}
