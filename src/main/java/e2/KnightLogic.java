package e2;

public interface KnightLogic {

    /**
     * Attempt to move the Knight on position (destinationRow, destinationColumn) if possible.
     *
     * @param destinationRow The destination row of the movement.
     * @param destinationColumn The destination column of the movement.
     * @return whether the movement is legal or not.
     */
    boolean move(int destinationRow, int destinationColumn);

    /**
     *
     * @param destinationRow The destination row of the movement.
     * @param destinationColumn The destination column of the movement.
     * @return whether the movement is legal or not.
     */
    boolean isMovementLegal(int destinationRow, int destinationColumn);

    /**
     * Get the position of the Knight.
     *
     * @return the position of the Knight.
     */
    Pair<Integer, Integer> getPosition();
}
